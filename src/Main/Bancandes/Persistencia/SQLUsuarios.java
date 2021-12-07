package Main.Bancandes.Persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import Main.Bancandes.negocio.Usuarios;
@SuppressWarnings("unused")
public class SQLUsuarios {
	private final static String SQL = PersistenciaBancandes.SQL;
	private PersistenciaBancandes pp;
	public SQLUsuarios (PersistenciaBancandes pp)
	{
		this.pp = pp;
	}
	@SuppressWarnings("rawtypes")
	public long adicionarUsuario (PersistenceManager pm, int Tipo_Id, long Numero_Id, String Login, String Palabra_Clave, int Rol, String Nombre, String Nacionalidad, String Direccion, String Correo, int Telefono, String Ciudad, String Departamento, int CodigoPostal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuarios () + "(Tipo_Id, Numero_Id, Login, Palabra_Clave, Rol, Nombre, Nacionalidad, Direccion, Correo, Telefono, Ciudad, Departamento, CodigoPostal) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(Tipo_Id, Numero_Id, Login, Palabra_Clave, Rol, Nombre, Nacionalidad, Direccion, Correo, Telefono, Ciudad, Departamento, CodigoPostal);
        return (long) q.executeUnique();
	}
	public long eliminarUsuarioPorId (PersistenceManager pm, long Numero_Id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuarios () + " WHERE Numero_Id = ?");
        q.setParameters(Numero_Id);
        return (long) q.executeUnique();
	}
	public Usuarios darUsuarioPorId (PersistenceManager pm, long Numero_Id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuarios () + " WHERE Numero_Id = ?");
		q.setResultClass(Usuarios.class);
		q.setParameters(Numero_Id);
		return (Usuarios) q.executeUnique();
	}
	public Usuarios darUsuarioPorLogin (PersistenceManager pm, String Login) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuarios () + " WHERE Login = ?");
		q.setResultClass(Usuarios.class);
		q.setParameters(Login);
		return (Usuarios) q.executeUnique();
	}
	public List<Usuarios> darUsuarios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuarios ());
		q.setResultClass(Usuarios.class);
		return (List<Usuarios>) q.executeList();
	}
	public void darUsuarioMasActivo()
	{
		
	}
} 