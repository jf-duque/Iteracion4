package Main.Bancandes.Persistencia;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import Main.Bancandes.negocio.Cuentas;
import Main.Bancandes.negocio.Empleados;
import Main.Bancandes.negocio.Oficina;
import Main.Bancandes.negocio.Operaciones;
import Main.Bancandes.negocio.Prestamos;
import Main.Bancandes.negocio.Puestos_Atencion;
import Main.Bancandes.negocio.Usuarios;

public class PersistenciaBancandes {
	private static Logger log = Logger.getLogger(PersistenciaBancandes.class.getName());
	public final static String SQL = "javax.jdo.query.SQL";
	private static PersistenciaBancandes instance;
	private PersistenceManagerFactory pmf;
	private List <String> tablas;
	private SQLUtil sqlUtil;
	private SQLCuentas sqlCuentas;
	private SQLEmpleados sqlEmpleados;
	private SQLOperaciones sqlOperaciones;
	private SQLPrestamos sqlPrestamos;
	private SQLPuestos_Atencion sqlPuestos_Atencion;
	private SQLUsuarios sqlUsuarios;
	private SQLOficina sqlOficina;
	
	private PersistenciaBancandes()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Bancandes");		
		crearClasesSQL ();
		tablas = new LinkedList<String> ();
		tablas.add ("Bancandes_sequence");
		tablas.add ("USUARIOS");
		tablas.add ("EMPLEADOS");
		tablas.add ("CUENTAS");
		tablas.add ("PUESTOS_ATENCION");
		tablas.add ("PRESTAMOS");
		tablas.add ("OPERACIONES");
		tablas.add ("OFICINA");

	}
	private PersistenciaBancandes(JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}
	public static PersistenciaBancandes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaBancandes ();
		}
		return instance;
	}
	public static PersistenciaBancandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaBancandes (tableConfig);
		}
		return instance;
	}
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	private void crearClasesSQL ()
	{
		sqlCuentas = new SQLCuentas(this);
		sqlEmpleados = new SQLEmpleados(this);
		sqlOperaciones = new SQLOperaciones(this);
		sqlPrestamos = new SQLPrestamos(this);
		sqlPuestos_Atencion = new SQLPuestos_Atencion(this);
		sqlUsuarios = new SQLUsuarios (this);	
		sqlOficina = new SQLOficina (this);
		sqlUtil = new SQLUtil(this);
	}
	public String darSeqBancandes ()
	{
		return tablas.get (0);
	}
	public String darTablaUsuarios ()
	{
		return tablas.get (1);
	}
	public String darTablaEmpleados ()
	{
		return tablas.get (2);
	}
	public String darTablaCuentas ()
	{
		return tablas.get (3);
	}
	public String darTablaPuestos_Atencion ()
	{
		return tablas.get (4);
	}
	public String darTablaPrestamos ()
	{
		return tablas.get (5);
	}
	public String darTablaOperaciones ()
	{
		return tablas.get (6);
	}
	public String darTablaOficina ()
	{
		return tablas.get(7);
	}
	public boolean TienePermiso(int tipo, ArrayList<Integer> aprobados)
	{
		return aprobados.contains(tipo);
	}
	public int verificarTipoUsuario(String Login)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            Usuarios usu = sqlUsuarios.darUsuarioPorLogin(pm, Login);
            tx.commit();
            int tipo=usu.getRol();
            
            return tipo;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public Usuarios RegistrarUsuario(String loginU,long Numero_Id, int Tipo_Id, String Login, String Palabra_Clave, int Rol, String Nombre, String Nacionalidad, String Direccion, String Correo, int Telefono, String Ciudad, String Departamento, int CodigoPostal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
        	ArrayList<Integer> PermisoRequerido = new ArrayList<Integer>();
            tx.begin();
            int tipo = verificarTipoUsuario(loginU);
            if (Rol==1 | Rol==6)
            {
            	PermisoRequerido.add(3);
            }
            else
            {
            	PermisoRequerido.add(5);
            }

            if (TienePermiso(tipo,PermisoRequerido)) {
                long tuplasInsertadas = sqlUsuarios.adicionarUsuario(pm, Tipo_Id, Numero_Id, Login, Palabra_Clave, Rol, Nombre, Nacionalidad, Direccion, Correo, Telefono, Ciudad, Departamento, CodigoPostal);
                tx.commit();
                
                log.trace ("Inserción de Usuario: " + Nombre + ": " + tuplasInsertadas + " tuplas insertadas");
                
                return new Usuarios (Tipo_Id, Numero_Id, Login, Palabra_Clave, Rol, Nombre, Nacionalidad, Direccion, Correo, Telefono, Ciudad, Departamento, CodigoPostal);
            }
            else {
            log.trace ("No tiene Permisos");
            return null;
            }
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	public Oficina RegistrarOficina(String loginU, String Nombre, String Direccion, long Id_Gerente, ArrayList<Integer> PuntosAtencion, int tipo_Id_Gerente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            int tipo = verificarTipoUsuario(loginU);
            ArrayList<Integer> Aprobados = new ArrayList<Integer>();
            Aprobados.add(5);
            if (TienePermiso(tipo,Aprobados)) {
	            long Id_Oficina = nextval ();
	            for (int i = 0; i<PuntosAtencion.size(); i++)
	            {
	            	int tipo1=PuntosAtencion.get(i);
	            	this.RegistrarPuntoDeAtencion(loginU, tipo1, Id_Oficina);
	            }
	            long tuplasInsertadas = sqlOficina.RegistrarOficina(pm, Nombre, Direccion, PuntosAtencion.size(), Id_Gerente, Id_Oficina, tipo_Id_Gerente);
	            tx.commit();
	            
	            log.trace ("Inserción de Oficina: " + Id_Oficina + ": " + tuplasInsertadas + " tuplas insertadas");
	            
	            return new Oficina (Nombre, Direccion, PuntosAtencion.size(), Id_Gerente, Id_Oficina, tipo_Id_Gerente);
            }
            else {
            log.trace ("No tiene Permisos");
            return null;
            }
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public Puestos_Atencion RegistrarPuntoDeAtencion(String loginU, int Tipo_Punto, long IdOficina)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            int tipo = verificarTipoUsuario(loginU);
            ArrayList<Integer> Aprobados = new ArrayList<Integer>();
            Aprobados.add(5);
            if (TienePermiso(tipo,Aprobados)) {
	            long Id_Punto = nextval ();
	            long tuplasInsertadas = sqlPuestos_Atencion.RegistrarPuestos_Atencion(pm, Id_Punto, Tipo_Punto, IdOficina);
	            tx.commit();
	            
	            log.trace ("Inserción de Punto de Atencion: " + Id_Punto + ": " + tuplasInsertadas + " tuplas insertadas");
	            
	            return new Puestos_Atencion (Id_Punto, Tipo_Punto, IdOficina);
            }
            else {
            log.trace ("No tiene Permisos");
            return null;
            }
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public Cuentas RegistrarCuenta(String loginU, int Tipo_Id, long Numero_Id, int Tipo_Cuenta, String fecha_creacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            int tipo = verificarTipoUsuario(loginU);
            ArrayList<Integer> Aprobados = new ArrayList<Integer>();
            Aprobados.add(3);   
            if (TienePermiso(tipo,Aprobados)) {
		        long Numero_Cuenta = nextval ();
		        long tuplasInsertadas = sqlCuentas.RegistrarCuenta(pm, Tipo_Id, Numero_Id, Numero_Cuenta, Tipo_Cuenta, 1, fecha_creacion);
		        tx.commit();
		        
		        log.trace ("Inserción de Punto de Atencion: " + Numero_Cuenta + ": " + tuplasInsertadas + " tuplas insertadas");
		        
		        return new Cuentas (Tipo_Id, Numero_Id, Numero_Cuenta, Tipo_Cuenta, 1, 0, fecha_creacion);
            }
            else {
            log.trace ("No tiene Permisos");
            return null;
            }
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public long CerrarCuenta(String loginU, long Numero_Cuenta)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            int tipo = verificarTipoUsuario(loginU);
            ArrayList<Integer> Aprobados = new ArrayList<Integer>();
            Aprobados.add(3);
            if (TienePermiso(tipo,Aprobados)) {
	            long resp = sqlCuentas.CerrarCuenta(pm, Numero_Cuenta); 
	            tx.commit();
	            return resp;
            }
            else {
            log.trace ("No tiene Permisos");
            return -1;
            }

        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	public Operaciones RegistrarOperacionSobreCuenta(String loginU, int Tipo_Id, long Numero_Id, long Numero_Cuenta, int valor, long Puesto_Atencion, int Tipo_Operacion, Date Fecha) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            int tipo = verificarTipoUsuario(loginU);
            ArrayList<Integer> Aprobados = new ArrayList<Integer>();
            Aprobados.add(1);
            Aprobados.add(4);
            Aprobados.add(6);
            if (TienePermiso(tipo,Aprobados)) {
	            long Id_Operacion = nextval ();
	            sqlCuentas.ActualizarSaldoCuenta(pm, Numero_Cuenta, valor);
	            sqlOperaciones.adicionarOperacion(pm, Id_Operacion, Tipo_Id, Numero_Id, Numero_Cuenta, valor, Puesto_Atencion, Tipo_Operacion, Fecha);
	            tx.commit();
	            
	            log.trace ("Inserción de Operacion: " + Id_Operacion + "en cuenta: " + Numero_Cuenta );
	            
	            return new Operaciones (Id_Operacion, Tipo_Id, Numero_Id, Numero_Cuenta, valor, Puesto_Atencion, Tipo_Operacion, Fecha);
            }
            else {
            	log.trace ("No tiene Permisos");
                return null;
            }
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public Prestamos SolicitarPrestamo(String loginU, int Monto, int TipoPrestamo, long Numero_Id, int tipo_Id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            int tipo = verificarTipoUsuario(loginU);
            ArrayList<Integer> Aprobados = new ArrayList<Integer>();
            Aprobados.add(3);
            if (TienePermiso(tipo,Aprobados)) {
	            long Id_Prestamo = nextval ();
	            sqlPrestamos.SolicitarPrestamo(pm, Id_Prestamo, Id_Prestamo, Monto, TipoPrestamo);
	            tx.commit();
	            
	            log.trace ("Inserción de Prestamo: " + Id_Prestamo + "en usuario: " + Numero_Id );
	            
	            return new Prestamos (Id_Prestamo, Numero_Id, TipoPrestamo, Monto, 0, 0, 0, 0, 2, 0, tipo_Id);
            }
            else {
            	log.trace ("No tiene Permisos");
                return null;
            }
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public Prestamos RegistrarPrestamo(String loginU, int Tipo_Id, long Numero_Id, int Tipo_Prestamo, int Monto, int Estado, int Saldo, long Numero_Cuenta, float Intereses, int Cuotas, int Dia, int Valor_Cuota, int tipo_Id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            int tipo = verificarTipoUsuario(loginU);
            ArrayList<Integer> Aprobados = new ArrayList<Integer>();
            Aprobados.add(3);
            if (TienePermiso(tipo,Aprobados)) {
	            long Id_Prestamo = nextval ();
	            sqlCuentas.ActualizarSaldoCuenta(pm, Numero_Cuenta, Monto);
	            sqlPrestamos.RegistrarPrestamo(pm, Id_Prestamo, Tipo_Id, Numero_Id, Tipo_Prestamo, Monto, Estado, Saldo, tipo_Id);
	            tx.commit();
	            
	            log.trace ("Inserción de Prestamo: " + Id_Prestamo + "en usuario: " + Numero_Id );
	            
	            return new Prestamos (Id_Prestamo, Numero_Id, Tipo_Prestamo, Monto, Intereses, Cuotas, Dia, Valor_Cuota, Estado, Saldo, tipo_Id);
            }
            else {
            	log.trace ("No tiene Permisos");
                return null;
            }
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public Operaciones RegistrarOperacionSobrePrestamo(String loginU, long IdPrestamo, int Tipo_Id, long Numero_Id, long Numero_Cuenta, int valor, long Puesto_Atencion, int Tipo_Operacion, Date Fecha)
	{
		{
			PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            int tipo = verificarTipoUsuario(loginU);
	            ArrayList<Integer> Aprobados = new ArrayList<Integer>();
	            Aprobados.add(2);
	            if (TienePermiso(tipo,Aprobados)) {
	            long Id_Operacion = nextval ();
		            sqlPrestamos.ActualizarPrestamo(pm, IdPrestamo, valor);
		            sqlOperaciones.adicionarOperacion(pm, Id_Operacion, Tipo_Id, Numero_Id, Numero_Cuenta, valor, Puesto_Atencion, Tipo_Operacion, Fecha);
		            tx.commit();
		            
		            log.trace ("Inserción de Operacion: " + Id_Operacion + "en prestamo: " + IdPrestamo );
		            
		            return new Operaciones (Id_Operacion, Tipo_Id, Numero_Id, Numero_Cuenta, valor, Puesto_Atencion, Tipo_Operacion, Fecha);
	            }
	            else {
	            	log.trace ("No tiene Permisos");
	                return null;
	            }
            }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return null;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
		}
	}
	public long CerrarPrestamo(long Id_Prestamo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPrestamos.eliminarPrestamoPorId_Prestamo(pm, Id_Prestamo); 
            tx.commit();
            return resp;

        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public void ConsultarLasCuentasEnBancandes(int Tipo_Cuenta, int RangoSaldo1, int RangoSaldo2, Date Fecha_Creacion, Date FechaUltimoMovimiento, long Numero_Id)
	{
		//ya esta hecho
	}
	public void ConsultarCliente(long Numero_Id)
	{
		
	}
	public void ConsultarTop10OperacionesDeMayorMovimientoEnRangoFechas(int RangoFecha1, int RangoFecha2)
	{
		
	}
	public void ObtenerLosDatosDelUsuarioMasActivo(int TipoIdOperacion, int ValorMin) 
	{
		
	}
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	public long [] limpiarParranderos ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarParranderos (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	

	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
}


