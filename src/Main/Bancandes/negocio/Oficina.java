package Main.Bancandes.negocio;

public class Oficina {
	private String Nombre;
	private String Direccion;
	private int PuntosAtencion;
	private long Id_Gerente;
	private long Id_Oficina;
	private int tipo_Id_Gerente;
	
	public Oficina()
	{
		this.Nombre="";
		this.Direccion="";
		this.PuntosAtencion=0;
		this.Id_Gerente=0;
		this.Id_Oficina=0;
	}
	public Oficina(String Nombre, String Direccion, int PuntosAtencion, long Id_Gerente, long Id_Oficina, int tipo_Id_Gerente)
	{
		this.Nombre=Nombre;
		this.Direccion=Direccion;
		this.PuntosAtencion=PuntosAtencion;
		this.Id_Gerente=Id_Gerente;
		this.Id_Oficina=Id_Oficina;
		this.tipo_Id_Gerente=tipo_Id_Gerente;
	}
	public String getNombre()
	{
		return this.Nombre;
	}
	public void setNombre(String Nombre)
	{
		this.Nombre=Nombre;
	} 
	public String getDireccion()
	{
		return this.Direccion;
	}
	public void setDireccion(String Direccion)
	{
		this.Direccion=Direccion;
	}
	public int getPuntosAtencion()
	{
		return this.PuntosAtencion;
	}
	public void setPuntosAtencion(int PuntosAtencion)
	{
		this.PuntosAtencion=PuntosAtencion;
	}
	public long getId_Gerente()
	{
		return this.Id_Gerente;
	}
	public void setId_Gerente(long Id_Gerente)
	{
		this.Id_Gerente=Id_Gerente;
	}
	public long getId_Ofician()
	{
		return this.Id_Oficina;
	}
	public void setId_Oficina(long Id_Oficina)
	{
		this.Id_Oficina=Id_Oficina;
	}
	public String toString()
	{
		return "Oficina [Nombre="+this.Nombre+", Direccion="+this.Direccion+", PuntosAtencion="+this.PuntosAtencion+", Id_Gerente="+this.Id_Gerente+"]";	
	}
}
