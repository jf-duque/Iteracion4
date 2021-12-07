package Main.Bancandes.Persistencia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import Main.Bancandes.negocio.Operaciones;
import Main.Bancandes.negocio.Usuarios;

import java.text.DateFormat;
public class SQLOperaciones {
	private final static String SQL = PersistenciaBancandes.SQL;
	private PersistenciaBancandes pp;
	public SQLOperaciones (PersistenciaBancandes pp)
	{
		this.pp = pp;
	}
	@SuppressWarnings("rawtypes")
	public long adicionarOperacion (PersistenceManager pm, long Id_Operacion, int Tipo_Id, long Numero_Id, long Numero_Cuenta, int valor, long Puesto_Atencion, int Tipo_Operacion, Date Fecha) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOperaciones () + "(Id_Operacion, Tipo_Id, Numero_Id, Numero_Cuenta, valor, Puesto_Atencion, Tipo_Operacion, Fecha) values (?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(Id_Operacion, Tipo_Id, Numero_Id, Numero_Cuenta, valor, Puesto_Atencion, Tipo_Operacion, Fecha);
        return (long) q.executeUnique();
	}
	public long eliminarOperacionPorId_Operacion (PersistenceManager pm, long Id_Operacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOperaciones () + " WHERE Id_Operacion = ?");
        q.setParameters(Id_Operacion);
        return (long) q.executeUnique();
	}
	public Operaciones darOperacionPorId_Operacion (PersistenceManager pm, long Id_Operacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperaciones () + " WHERE Id_Operacion = ?");
		q.setResultClass(Operaciones.class);
		q.setParameters(Id_Operacion);
		return (Operaciones) q.executeUnique();
	}
	public List<Operaciones> darOperaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperaciones ());
		q.setResultClass(Operaciones.class);
		return (List<Operaciones>) q.executeList();
	}
	
	public List<Operaciones> darOperacionesV2(PersistenceManager pm, Usuarios pUsuario, int pTipoId, int pNumId, int pNumCuenta, Double pIValor, Double pFValor,
			Date pFechaI, Date pFechaF, int pTipo, String pGroup, String pOrder){
		
		List<Operaciones> op = new ArrayList();

		if(pUsuario.getRol() == 2){
		
			if(pTipoId != -1 && pNumId != -1){
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperaciones () + " WHERE FECHA BETWEEN ? AND ? AND FECHA BETWEEN ? AND ? AND TIPO_ID  = ? AND NUMERO_ID = ? GROUP BY ? ORDER BY ?");
			q.setResultClass(Operaciones.class);
			q.setParameters(pFechaI, pFechaF, pTipoId, pNumId, pGroup, pOrder);
			return (List<Operaciones>) q.executeList();
			}
			else if(pNumCuenta != -1){
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperaciones () + " WHERE FECHA BETWEEN ? AND ? AND NUMERO_CUENTA = ? GROUP BY ? ORDER BY ?");
			q.setResultClass(Operaciones.class);
			q.setParameters(pNumCuenta, pGroup, pOrder);
			return (List<Operaciones>) q.executeList();
			}
			else if(pIValor != -1 && pIValor != -1){
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperaciones () + " WHERE FECHA BETWEEN ? AND ? AND VALOR BETWEEN ? AND ? GROUP BY ? ORDER BY ?");
			q.setResultClass(Operaciones.class);
			q.setParameters(pIValor, pIValor, pGroup, pOrder);
			return (List<Operaciones>) q.executeList();
			}
			else if(pTipo != -1){
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperaciones () + " WHERE FECHA BETWEEN ? AND ? AND TIPO_OPERACION = ? GROUP BY ? ORDER BY ?");
			q.setResultClass(Operaciones.class);
			q.setParameters(pTipo, pGroup, pOrder);
			return (List<Operaciones>) q.executeList();
			}
		
		}
		else{
		
		return null;
		}
		return null;
		
		}
	
	public List<Operaciones> darOperacionesV3(PersistenceManager pm, Usuarios pUsuario, int pTipoId, int pNumId, int pNumCuenta, Double pIValor, Double pFValor,
			Date pFechaI, Date pFechaF, int pTipo,String pGroup, String pOrder){

		if(pUsuario.getRol() == 2){
		
			if(pTipoId != -1 && pNumId != -1){
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperaciones () + " WHERE FECHA BETWEEN ? AND ? AND FECHA BETWEEN ? AND ? AND TIPO_ID  <> ? AND NUMERO_ID <> ? GROUP BY ? ORDER BY ?");
			q.setResultClass(Operaciones.class);
			q.setParameters(pFechaI, pFechaF, pTipoId, pNumId, pGroup, pOrder);
			return (List<Operaciones>) q.executeList();
			}
			else if(pNumCuenta != -1){
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperaciones () + " WHERE FECHA BETWEEN ? AND ? AND NUMERO_CUENTA <> ? GROUP BY ? ORDER BY ?");
			q.setResultClass(Operaciones.class);
			q.setParameters(pNumCuenta, pGroup, pOrder);
			return (List<Operaciones>) q.executeList();
			}
			else if(pIValor != -1 && pIValor != -1){
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperaciones () + " WHERE FECHA BETWEEN ? AND ? AND VALOR NOT BETWEEN ? AND ? GROUP BY ? ORDER BY ?");
			q.setResultClass(Operaciones.class);
			q.setParameters(pIValor, pIValor, pGroup, pOrder);
			return (List<Operaciones>) q.executeList();
			}
			else if(pTipo != -1){
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperaciones () + " WHERE FECHA BETWEEN ? AND ? AND TIPO_OPERACION <> ? GROUP BY ? ORDER BY ?");
			q.setResultClass(Operaciones.class);
			q.setParameters(pTipo, pGroup, pOrder);
			return (List<Operaciones>) q.executeList();
			}
		
		}
		else{
		
		return null;
		}
		return null;

	}
	
	public List<Operaciones> darConsignaciones(PersistenceManager pm, Usuarios pUsuario, Double pValor, int pTipo){

		if(pUsuario.getRol() == 2){

				Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperaciones () + "O, "+ pp.darTablaCuentas() + "C "  
					+ "WHERE O.TIPO_OPERACION = 1 AND O.VALOR > ? AND O.TIPO_ID  = C.TIPO_ID AND O.NUMERO_ID = C.NUMERO_ID " 
					+ "AND C.TIPO_CUENTA = ?");
				q.setResultClass(Operaciones.class);
				q.setParameters(pValor, pTipo);
				return (List<Operaciones>) q.executeList();
		}
		else{

			return null;
		}
			
	}
	
	public List<Operaciones> darClientesPorPuntos(PersistenceManager pm, Usuarios pUsuario, int pIdPuntoUno, int pIdPuntoDos){

		if(pUsuario.getRol() == 2){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuarios () + "U, "+ pp.darTablaOperaciones() + "O "  
		+ "(SELECT * FROM (SELECT TIPO_ID, NUMERO_ID FROM OPERACIONES WHERE ID_OFICINA = ?) NATURAL INNER JOIN " 
		+ "(SELECT TIPO_ID, NUMERO_ID FROM OPERACIONES WHERE ID_OFICINA = ?)) J"
		+ "WHERE U.TIPO_ID = J.TIPO_ID AND U.NUMERO_ID = J.NUMERO_ID AND O.TIPO_ID = J.TIPO_ID AND O.NUMERO_ID = J.NUMERO_ID ");
		q.setResultClass(Operaciones.class);
		q.setParameters(pIdPuntoUno, pIdPuntoDos);
		return (List<Operaciones>) q.executeList();
		}
		else{
		return null;
		}
		
	}
}
