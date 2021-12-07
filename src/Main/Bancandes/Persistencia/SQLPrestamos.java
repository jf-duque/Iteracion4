package Main.Bancandes.Persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import Main.Bancandes.negocio.Prestamos;
public class SQLPrestamos {
	private final static String SQL = PersistenciaBancandes.SQL;
	private PersistenciaBancandes pp;
	public SQLPrestamos (PersistenciaBancandes pp)
	{
		this.pp = pp;
	}
	public long RegistrarPrestamo (PersistenceManager pm, long Id_Prestamo, int Tipo_Id, long Numero_Id, int Tipo_Prestamo, int Monto, int Estado, int Saldo, int tipo_Id2) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuarios () + "(Id_Prestamo, Tipo_Id, Numero_Id, Tipo_Prestamo, Monto, Estado, Saldo,tipo_Id2) values (?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(Id_Prestamo, Tipo_Id, Numero_Id, Tipo_Prestamo, Monto, Estado, Saldo, tipo_Id2);
        return (long) q.executeUnique();
	}
	public long eliminarPrestamoPorId_Prestamo (PersistenceManager pm, long Id_Prestamo)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPrestamos () + " WHERE Id_Prestamo = ?");
        q.setParameters(Id_Prestamo);
        return (long) q.executeUnique();
	}
	public Prestamos darPrestamoPorId_Prestamo (PersistenceManager pm, long Id_Prestamo) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPrestamos () + " WHERE Id_Prestamo = ?");
		q.setResultClass(Prestamos.class);
		q.setParameters(Id_Prestamo);
		return (Prestamos) q.executeUnique();
	}
	public List<Prestamos> darUsuarios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPrestamos ());
		q.setResultClass(Prestamos.class);
		return (List<Prestamos>) q.executeList();
	}
	public long SolicitarPrestamo(PersistenceManager pm, long Id_Prestamo, long Numero_Id, int Monto, int Tipo_Prestamo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPrestamos () + "(Id_Prestamo, Numero_Id, Tipo_Prestamo, Monto, Intereses, Cuotas, Dia, Valor_Cuota, Saldo, Estado) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(Id_Prestamo, Numero_Id, Tipo_Prestamo, Monto, 0, 0, 0, 0, 0, 2);
        return (long) q.executeUnique();
	}
	public long NegarPrestamo(PersistenceManager pm, long Id_Prestamo)
	{
		Query q = pm.newQuery(SQL, "UPDATE "+pp.darTablaPrestamos ()+" SET Estado=? WHERE Id_Prestamo=?");
		q.setParameters(3,Id_Prestamo);
		return (long) q.executeUnique();
	}
	public long AprobarPrestamo(PersistenceManager pm, long Id_Prestamo, int Monto, int Intereses, int Cuotas, int Dia, int Valor_Cuota)
	{
		Query q = pm.newQuery(SQL, "UPDATE "+pp.darTablaPrestamos ()+" SET Estado=?, Saldo=?, Intereses=?, Cuotas=?, Dia=?, Valor_Cuota=? WHERE Id_Prestamo=?");
		q.setParameters(1,Monto, Intereses, Cuotas, Dia, Valor_Cuota, Id_Prestamo);
		return (long) q.executeUnique();
	}
	public int ObtenerSaldo(PersistenceManager pm, long Id_Prestamo)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPrestamos () + "WHERE Id_Prestamo=?");
		q.setParameters(Id_Prestamo);
		Prestamos p = (Prestamos) q.executeUnique();
		return p.getSaldo();
	}
	public long ActualizarPrestamo(PersistenceManager pm, long Id_Prestamo, int pago)
	{
		int NSaldo=ObtenerSaldo(pm,Id_Prestamo)-pago;
		int Estado=1;
		if (NSaldo==0)
				{
				Estado=0;
				}
		Query q = pm.newQuery(SQL, "UPDATE "+pp.darTablaPrestamos ()+" SET Saldo=?, Estado=? WHERE Id_Prestamo=?");
		q.setParameters(NSaldo, Estado, Id_Prestamo);
		return (long) q.executeUnique();
	}
}
