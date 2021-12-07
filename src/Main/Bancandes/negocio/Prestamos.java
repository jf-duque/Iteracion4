package Main.Bancandes.negocio;

public class Prestamos {
	private long Id_Prestamo;
	private int tipo_Id;
	private long Numero_Id;
	private int Tipo_Prestamo; //1:Vivienda, 2: Estudio, 3:Automovil, 4:Calamidad, 5:Libre
	private int Monto;
	private float Interes;
	private int Cuotas;
	private int Dia;
	private int Valor_Cuota;
	private int Saldo;
	private int Estado; //0: Cerrado, 1:Abierto, 2:Solicitado, 3:Rechazado
	
	public Prestamos()
	{
		this.Id_Prestamo=0;
		this.tipo_Id=0;
		this.Numero_Id=0;
		this.Tipo_Prestamo=0;
		this.Monto=0;
		this.Estado=0;
		this.Saldo=0;
		this.Interes=0;
		this.Cuotas=0;
		this.Valor_Cuota=0;
		this.Dia=0;
	}
	public Prestamos(long Id_Prestamo, long Numero_Id, int Tipo_Prestamo, int Monto, float Interes, int Cuotas,int Dia, int Valor_Cuota, int Estado, int Saldo, int Tipo_Id)
	{
		this.Id_Prestamo=Id_Prestamo;
		this.tipo_Id=Tipo_Id;
		this.Numero_Id=Numero_Id;
		this.Tipo_Prestamo=Tipo_Prestamo;
		this.Monto=Monto;
		this.Estado=Estado;
		this.Saldo=Saldo;
		this.Interes=Interes;
		this.Cuotas=Cuotas;
		this.Valor_Cuota=Valor_Cuota;
		this.Dia=Dia;
	}
	public long getId_Prestamo()
	{
		return this.Id_Prestamo;
	}
	public void setId_Prestamo(long Id_Prestamo)
	{
		this.Id_Prestamo=Id_Prestamo;
	}
	public long getNumero_Id()
	{
		return this.Numero_Id;
	}
	public void setNumero_Id(long Numero_Id)
	{
		this.Numero_Id=Numero_Id;
	}
	public int getTipo_Prestamo()
	{
		return this.Tipo_Prestamo;
	}
	public void setTipo_Prestamo(int Tipo_Prestamo)
	{
		this.Tipo_Prestamo=Tipo_Prestamo;
	}
	public int getMonto()
	{
		return this.Monto;
	}
	public void setMonto(int Monto)
	{
		this.Monto=Monto;
	}
	public int getEstado()
	{
		return this.Estado;
	}
	public void setEstado(int Estado)
	{
		this.Estado=Estado;
	}
	public int getSaldo()
	{
		return this.Saldo;
	}
	public void setSaldo(int Saldo)
	{
		this.Saldo=Saldo;
	}
	public String toString()
	{
		return "Prestamos [Id_Prestamo="+this.Id_Prestamo+", Numero_Id="+this.Numero_Id+", Tipo_Prestamo="+this.Monto+", Monto="+this.Monto+", Estado="+this.Estado+"]";
	}
}
