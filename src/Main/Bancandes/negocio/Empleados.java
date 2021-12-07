package Main.Bancandes.negocio;

public class Empleados {
	private int Cedula;
	private String Nombre;
	private int Tipo;
	
	public Empleados() {
		this.Cedula=0;
		this.Nombre="";
		this.Tipo=0;
	}
	public Empleados(int Cedula, String Nombre, int Tipo) 
	{
		this.Cedula=Cedula;
		this.Nombre=Nombre;
		this.Tipo=Tipo;
	}
	public int getCedula()
	{
		return this.Cedula;
	}
	public void setCedula(int Cedula)
	{
		this.Cedula=Cedula;
	}
	public String getNombre()
	{
		return this.Nombre;
	}
	public void setNombre(String Nombre)
	{
		this.Nombre=Nombre;
	}
	public int getTipo()
	{
		return this.Tipo;
	}
	public void setTipo(int Tipo)
	{
		this.Tipo=Tipo;
	}
	public String toString()
	{
		return "Empleados [Cedula="+this.Cedula+", String="+this.Nombre+", Tipo="+this.Tipo+"]";
	}
}