package Main.Bancandes.Persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


class SQLUtil
{

	private final static String SQL = PersistenciaBancandes.SQL;

	private PersistenciaBancandes pp;

	public SQLUtil (PersistenciaBancandes pp)
	{
		this.pp = pp;
	}

	@SuppressWarnings("rawtypes")
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqBancandes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}


	@SuppressWarnings("rawtypes")
	public long [] limpiarParranderos (PersistenceManager pm)
	{
        Query qCuentas = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCuentas ());          
        Query qEmpleados = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpleados ());
        Query qOperaciones = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOperaciones ());
        Query qPrestamos = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPrestamos ());
        Query qPuestos_Atencion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPuestos_Atencion ());
        Query qUsuarios = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuarios ());

        long CuentasEliminadas = (long) qCuentas.executeUnique ();
        long EmpleadosEliminados = (long) qEmpleados.executeUnique ();
        long OperacionesEliminadas = (long) qOperaciones.executeUnique ();
        long PrestamosEliminados = (long) qPrestamos.executeUnique ();
        long Puestos_AtencionEliminados = (long) qPuestos_Atencion.executeUnique ();
        long UsuariosEliminados = (long) qUsuarios.executeUnique ();
        return new long[] {CuentasEliminadas, EmpleadosEliminados, OperacionesEliminadas, PrestamosEliminados, 
        		Puestos_AtencionEliminados, UsuariosEliminados};
	}

}