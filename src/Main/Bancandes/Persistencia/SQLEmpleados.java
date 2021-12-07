package Main.Bancandes.Persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import Main.Bancandes.negocio.Empleados;
import Main.Bancandes.negocio.Usuarios;
public class SQLEmpleados {
	private final static String SQL = PersistenciaBancandes.SQL;
	private PersistenciaBancandes pp;
	public SQLEmpleados(PersistenciaBancandes pp)
	{
		this.pp=pp;
	}
	public long adicionarEmpleado (PersistenceManager pm, int Cedula, String Nombre, int Tipo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEmpleados () + "( Cedula,  Nombre,  Tipo) values (?, ?, ?)");
        q.setParameters( Cedula,  Nombre,  Tipo);
        return (long) q.executeUnique();
	}
	public long eliminarEmpleadoPorCedula (PersistenceManager pm, int Cedula)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpleados () + " WHERE Cedula = ?");
        q.setParameters(Cedula);
        return (long) q.executeUnique();
	}
	public Empleados darEmpleadoPorCedula (PersistenceManager pm, int Cedula) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpleados () + " WHERE Cedula = ?");
		q.setResultClass(Empleados.class);
		q.setParameters(Cedula);
		return (Empleados) q.executeUnique();
	}
	public List<Empleados> darEmpleados (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpleados ());
		q.setResultClass(Empleados.class);
		return (List<Empleados>) q.executeList();
	}
}
