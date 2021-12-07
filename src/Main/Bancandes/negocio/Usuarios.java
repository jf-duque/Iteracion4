package Main.Bancandes.negocio;

public class Usuarios {
	private int Tipo_Id;
	private long Numero_Id;
	private String Login;
	private String Palabra_Clave;
	private int Rol;
	private String Nombre;
	private String Nacionalidad;
	private String Direccion;
	private String Correo;
	private int Telefono;
	private String Ciudad;
	private String Departamento;
	private int CodigoPostal;
	
	public Usuarios()
	{
		this.Tipo_Id=0;
		this.Numero_Id=0;
		this.Login="";
		this.Palabra_Clave="";
		this.Rol=0;
		this.Nombre="";
		this.Nacionalidad="";
		this.Direccion="";
		this.Correo="";
		this.Telefono=0;
		this.Ciudad="";
		this.Departamento="";
		this.CodigoPostal=0;
	}
	public Usuarios(int Tipo_Id,long Numero_Id,String Login, String Palabra_Clave, int Rol, String Nombre, String Nacionalidad, String Direccion, String Correo, int Telefono, String Ciudad, String Departamento, int CodigoPostal) 
	{
		this.Tipo_Id=Tipo_Id;
		this.Numero_Id=Numero_Id;
		this.Login=Login;
		this.Palabra_Clave=Palabra_Clave;
		this.Rol=Rol;
		this.Nombre=Nombre;
		this.Nacionalidad=Nacionalidad;
		this.Direccion=Direccion;
		this.Correo=Correo;
		this.Telefono=Telefono;
		this.Ciudad=Ciudad;
		this.Departamento=Departamento;
		this.CodigoPostal=CodigoPostal;
	}
	public int getTipo_Id() 
	{
		return this.Tipo_Id;
	}
	public void setTipo_Id(int Tipo_Id)
	{
		this.Tipo_Id=Tipo_Id;
	}
	public long getNumero_Id()
	{
		return this.Numero_Id;
	}
	public void setNumero_Id(int Numero_Id)
	{
		this.Numero_Id=Numero_Id;
	}
	public String getLogin()
	{
		return this.Login;
	}
	public void setLogin(String Login)
	{
		this.Login=Login;
	}
	public String getPalabra_Clave()
	{
		return this.Palabra_Clave;
	}
	public void setPalabra_Clave(String Palabra_Clave)
	{
		this.Palabra_Clave=Palabra_Clave;
	}
	public int getRol() 
	{
		return this.Rol;
	}
	public void setRol(int Rol)
	{
		this.Rol=Rol;
	}
	public String getNombre()
	{
		return this.Nombre;
	}
	public void setNombre(String Nombre)
	{
		this.Nombre=Nombre;
	}
	public String getNacionalidad()
	{
		return this.Nacionalidad;
	}
	public void setNacionalidad(String Nacionalidad)
	{
		this.Nacionalidad=Nacionalidad;
	}
	public String getDireccion()
	{
		return this.Direccion;
	}
	public void setDireccion(String Direccion)
	{
		this.Direccion=Direccion;
	}
	public String getCorreo()
	{
		return this.Correo;
	}
	public void setCorreo(String Correo)
	{
		this.Correo=Correo;
	}
	public int getTelefono() 
	{
		return this.Telefono;
	}
	public void setTelefono(int Telefono)
	{
		this.Telefono=Telefono;
	}
	public String getCiudad()
	{
		return this.Ciudad;
	}
	public void setCiudad(String Ciudad)
	{
		this.Ciudad=Ciudad;
	}
	public String getDepartamento()
	{
		return this.Departamento;
	}
	public void setDepartamento(String Departamento)
	{
		this.Departamento=Departamento;
	}
	public int getCodigoPostal() 
	{
		return this.CodigoPostal;
	}
	public void setCodigoPostal(int CodigoPostal)
	{
		this.CodigoPostal=CodigoPostal;
	}
	public String toString()
	{
		return "Usuarios [Tipo_Id="+ this.Tipo_Id + ", Numero_Id="+ this.Numero_Id+ ", Login=" + this.Login + ", Palabra_Clave=" + this.Palabra_Clave + ", Rol"+this.Rol+", Nombre="+this.Nombre+", Nacionalidad="+this.Nacionalidad+", Direccion="+this.Direccion+", Correo="+this.Correo+", Telefono="+this.Telefono+", Ciudad="+this.Ciudad+", Departamento="+this.Departamento+", Codigo_Postal="+this.CodigoPostal+"]";
	}
}
