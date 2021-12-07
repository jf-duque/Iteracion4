package Main.Bancandes.Persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import Main.Bancandes.negocio.Cuentas;
import Main.Bancandes.negocio.Oficina;
public class SQLCuentas {
	private final static String SQL = PersistenciaBancandes.SQL;
	private PersistenciaBancandes pp;
	public SQLCuentas (PersistenciaBancandes pp)
	{
		this.pp = pp;
	}
	public long RegistrarCuenta(PersistenceManager pm, int Tipo_Id, long Numero_Id, long Numero_Cuenta, int Tipo_Cuenta, int Estado, String fecha_creacion)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCuentas () + "(Tipo_Id, Numero_Id, Numero_Cuenta, Tipo_Cuenta, Estado, Saldo, fecha_creacion) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(Tipo_Id, Numero_Id, Numero_Cuenta, Tipo_Cuenta, Estado, fecha_creacion);
        return (long) q.executeUnique();
	}
	public long eliminarCuentaPorNumero_Cuenta (PersistenceManager pm, long Numero_Cuenta)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCuentas () + " WHERE Numero_Cuenta = ?");
        q.setParameters(Numero_Cuenta);
        return (long) q.executeUnique();
	}
	public Cuentas darCuentaPorNumero_Cuenta (PersistenceManager pm, long Numero_Cuenta) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCuentas () + " WHERE Numero_Cuenta = ?");
		q.setResultClass(Cuentas.class);
		q.setParameters(Numero_Cuenta);
		return (Cuentas) q.executeUnique();
	}
	public List<Cuentas> darCuentas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCuentas ());
		q.setResultClass(Cuentas.class);
		return (List<Cuentas>) q.executeList();
	}
	public long CerrarCuenta(PersistenceManager pm, long Numero_Cuenta)
	{
		Query q = pm.newQuery(SQL, "UPDATE "+pp.darTablaCuentas ()+" SET Estado=?, Saldo=? WHERE Numero_Cuenta=?");
		q.setParameters(0,0,Numero_Cuenta);
		return 0;
	}
	public long ConsultarSaldo(PersistenceManager pm, long Numero_Cuenta)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+pp.darTablaCuentas ()+" WHERE Numero_Cuenta=?");
		q.setParameters(Numero_Cuenta);
		q.setResultClass(Cuentas.class);
		Cuentas Cu = (Cuentas) q.executeUnique();
		return Cu.getSaldo();
	}
	public long ActualizarSaldoCuenta(PersistenceManager pm, long Numero_Cuenta, int valor)
	{
		long Nuevo_Saldo = ConsultarSaldo(pm, Numero_Cuenta) + valor;
		Query q = pm.newQuery(SQL, "UPDATE "+pp.darTablaCuentas ()+" SET Saldo=? WHERE Numero_Cuenta=?");
		q.setParameters(Nuevo_Saldo,Numero_Cuenta);
		return (long) q.executeUnique();
	}
	public Cuentas ConsultarCuentasporCriterio(PersistenceManager pm, int Tipo_cuenta, String order, String group)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCuentas () + " WHERE TIPO_CUENTA  = ? ORDER BY ? GROUP BY ?");
		q.setResultClass(Cuentas.class);
		q.setParameters(Tipo_cuenta, order, group);
		return (Cuentas) q.executeUnique();
	}
	public Cuentas ConsultarCuentasPorSaldo(PersistenceManager pm, int saldo1, int saldo2, String order, String group){
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCuentas () + " WHERE SALDO  BETWEEN ? AND ? ORDER BY ? GROUP BY ?");
			q.setResultClass(Cuentas.class);
			q.setParameters(saldo1, saldo2, order, group);
			return (Cuentas) q.executeUnique();
		}
	}
	public void darCuentaPorId_Usuario()
	{
		
	}
}
