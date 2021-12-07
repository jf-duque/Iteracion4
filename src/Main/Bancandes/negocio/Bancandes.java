package Main.Bancandes.negocio;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;
import Main.Bancandes.Persistencia.PersistenciaBancandes;




public class Bancandes {
	private static Logger log = Logger.getLogger(Bancandes.class.getName());
	private PersistenciaBancandes pp;
	public Bancandes ()
	{
		pp = PersistenciaBancandes.getInstance ();
	}
	public Bancandes (JsonObject tableConfig)
	{
		pp = PersistenciaBancandes.getInstance (tableConfig);
	}
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	public void RegistrarUsuario(int Tipo_Id, long Numero_Id, String Login, String Palabra_Clave, int Rol, String Nombre, String Nacionalidad, String Direccion, String Correo, int Telefono, String Ciudad, String Departamento, int CodigoPostal)
	{
		
	}
	public void RegistrarOficina()
	{
		
	}
	public void RegistrarPuntoDeAtencion(long Id_Punto, long Empleado, int Tipo_Punto, String Lugar)
	{
		
	}
	public void RegistrarCuenta(int Tipo_Id, long Numero_Id, long Numero_Cuenta, int Tipo_Cuenta, int Estado)
	{
		
	}
	public void CerrarCuenta(long Numero_Cuenta)
	{
		
	}
	public void RegistrarOperacionSobreCuenta(long Id_Operacion, int Tipo_Id, long Numero_Id, long Numero_Cuenta, int valor, long Puesto_Atencion, long Empleado, Date Fecha) 
	{
		
	}
	public void RegistrarPrestamo(long Id_Prestamo, int Tipo_Id, long Numero_Id, int Tipo_Prestamo, int Monto, int Estado, int Saldo)
	{
		
	}
	public void RegistrarOperacionSobrePrestamo(long IdPrestamo, long Id_Operacion, int Tipo_Id, long Numero_Id, long Numero_Cuenta, int valor, long Puesto_Atencion, long Empleado, Date Fecha)
	{
		
	}
	public void CerrarPrestamo()
	{
		
	}
	public void ConsultarLasCuentasEnBancandes(int Tipo_Cuenta, int RangoSaldo1, int RangoSaldo2, Date Fecha_Creacion, Date FechaUltimoMovimiento, long Numero_Id)
	{
		
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
	public long [] limpiarParranderos ()
	{
        log.info ("Limpiando la BD de Parranderos");
        long [] borrrados = pp.limpiarParranderos();	
        log.info ("Limpiando la BD de Parranderos: Listo!");
        return borrrados;
	}
}
