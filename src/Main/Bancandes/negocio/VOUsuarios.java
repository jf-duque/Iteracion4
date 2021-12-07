package Main.Bancandes.negocio;

public interface VOUsuarios {
	public int getTipo_Id();
	public long getNumero_Id();
	public String getLogin();
	public String getPalabra_Clave();
	public int getRol();
	public String getNombre();
	public String getNacionalidad();
	public String getDireccion();
	public String getCorreo();
	public int getTelefono();
	public String getCiudad();
	public String getDepartamento();
	public int getCodigoPostal();
	public String toString();
}
