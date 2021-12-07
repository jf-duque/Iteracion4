package Main.Bancandes.Persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import Main.Bancandes.negocio.Puestos_Atencion;
@SuppressWarnings("unused")
public class SQLPuestos_Atencion {
	private final static String SQL = PersistenciaBancandes.SQL;
	private PersistenciaBancandes pp;
	public SQLPuestos_Atencion (PersistenciaBancandes pp)
	{
		this.pp = pp;
	}
	@SuppressWarnings("rawtypes")
	public long RegistrarPuestos_Atencion (PersistenceManager pm, long Id_Punto, int Tipo_Punto, long Id_Oficina) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPuestos_Atencion () + "(Id_Punto, Empleado, Tipo_Punto, Id_Oficina) values (?, ?, ?)");
        q.setParameters(Id_Punto, Tipo_Punto, Id_Oficina);
        return (long) q.executeUnique();
	}
	public long eliminarPuestos_AtencionPorId_Punto (PersistenceManager pm, long Id_Punto)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPuestos_Atencion () + " WHERE Id_Punto = ?");
        q.setParameters(Id_Punto);
        return (long) q.executeUnique();
	}
	public Puestos_Atencion darPuestos_AtencionPorId_Punto (PersistenceManager pm, long Id_Punto) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPuestos_Atencion () + " WHERE Id_Punto = ?");
		q.setResultClass(Puestos_Atencion.class);
		q.setParameters(Id_Punto);
		return (Puestos_Atencion) q.executeUnique();
	}
	public List<Puestos_Atencion> darPuestos_Atencion (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPuestos_Atencion ());
		q.setResultClass(Puestos_Atencion.class);
		return (List<Puestos_Atencion>) q.executeList();
	}
}