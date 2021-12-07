package Main.Bancandes.negocio;

public class Puestos_Atencion {
	private long Id_Punto;
	private int Tipo_Punto;
	private long Oficina;
	
	public Puestos_Atencion()
	{
		this.Id_Punto=0;
		this.Tipo_Punto=0;
		this.Oficina=0;
	}
	public Puestos_Atencion(long Id_Punto, int Tipo_Punto, long Oficina)
	{
		this.Id_Punto=Id_Punto;
		this.Tipo_Punto=Tipo_Punto;
		this.Oficina=Oficina;
	}
	public long getId_Punto()
	{
		return this.Id_Punto;
	}
	public void setId_Punto(long Id_Punto)
	{
		this.Id_Punto=Id_Punto;
	}
	public int getTipo_Punto()
	{
		return this.Tipo_Punto;
	}
	public void setTipo_Punto(int Tipo_Punto)
	{
		this.Tipo_Punto=Tipo_Punto;
	}
	public long getOficina()
	{
		return this.Oficina;
	}
	public void setOficina(long Oficina)
	{
		this.Oficina=Oficina;
	}
	public String toString()
	{
		return "Puestos_Atencion [Id_Punto="+this.Id_Punto+", Tipo_Punto="+this.Tipo_Punto+", Oficina="+this.Oficina+"]";
	}
}
