package Main.Bancandes.negocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Operaciones {
	private long Id_Operacion;
	private int Tipo_Id;
	private long Numero_Id;
	private long Numero_Cuenta;
	private int Valor;
	private long Puesto_Atencion;
	private Date Fecha;
	private int Tipo_Operacion;
	
	@SuppressWarnings("deprecation")
	public Operaciones()
	{
		this.Id_Operacion=0;
		this.Tipo_Id=0;
		this.Numero_Id=0;
		this.Numero_Cuenta=0;
		this.Valor=0;
		this.Puesto_Atencion=0;
		this.Tipo_Operacion=0;
		this.Fecha=new Date(1900,0,1);
	}
	public Operaciones(long Id_Operacion, int Tipo_Id, long Numero_Id, long Numero_Cuenta, int Valor, long Puesto_Atencion, int Tipo_Operacion, Date Fecha)
	{
		this.Id_Operacion=Id_Operacion;
		this.Tipo_Id=Tipo_Id;
		this.Numero_Id=Numero_Id;
		this.Numero_Cuenta=Numero_Cuenta;
		this.Valor=Valor;
		this.Puesto_Atencion=Puesto_Atencion;
		this.Tipo_Operacion=Tipo_Operacion;
		this.Fecha=Fecha;
	}
	public long getId_Operacion()
	{
		return this.Id_Operacion;
	}
	public void setId_Operacion(long Id_Operacion)
	{
		this.Id_Operacion=Id_Operacion;
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
	public void setNumero_Id(long Numero_Id)
	{
		this.Numero_Id=Numero_Id;
	}
	public long getNumero_Cuenta()
	{
		return this.Numero_Cuenta;
	}
	public void setNumero_Cuenta(long Numero_Cuenta)
	{
		this.Numero_Cuenta=Numero_Cuenta;
	}
	public int getValor()
	{
		return this.Valor;
	}
	public void setValor(int Valor)
	{
		this.Valor=Valor;
	}
	public long getPuesto_Atencion()
	{
		return this.Puesto_Atencion;
	}
	public void setPuesto_Atencion(long Puesto_Atencion)
	{
		this.Puesto_Atencion=Puesto_Atencion;
	}
	public long getTipo_Operacion()
	{
		return this.Tipo_Operacion;
	}
	public void setTipo_Operacion(int Tipo_Operacion)
	{
		this.Tipo_Operacion=Tipo_Operacion;
	}
	public Date getFecha()
	{
		return this.Fecha;
	}
	public void setFecha(Date Fecha)
	{
		this.Fecha=Fecha;
	}
	public String toString()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return "Operaciones [Id_Operacion="+this.Id_Operacion+", Tipo_Id="+this.Tipo_Id+", Numero_Id"+this.Numero_Id+", Numero_Cuenta="+this.Numero_Cuenta+", Valor"+this.Valor+", Puesto_Atencio="+this.Puesto_Atencion+", Tipo_Operacion="+this.Tipo_Operacion+", Fecha="+df.format(this.Fecha)+"]";
	}
}
