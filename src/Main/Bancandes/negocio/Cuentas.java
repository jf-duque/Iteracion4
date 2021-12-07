package Main.Bancandes.negocio;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;   
public class Cuentas {
	private int Tipo_Id;
	private long Numero_Id;
	private long Numero_Cuenta;
	private int Tipo_Cuenta;
	private int Estado;
	private int Saldo;
	private Date fecha_creacion;
	
	public Cuentas()
	{
		this.Tipo_Id=0;
		this.Numero_Id=0;
		this.Numero_Cuenta=0;
		this.Tipo_Cuenta=0;
		this.Estado=0;
		this.Saldo=0;
		this.fecha_creacion=null;
	}
	public Cuentas(int Tipo_Id, long Numero_Id, long Numero_Cuenta, int Tipo_Cuenta, int Estado, int Saldo, String fecha_creacion) throws ParseException
	{
		this.Tipo_Id=Tipo_Id;
		this.Numero_Id=Numero_Id;
		this.Numero_Cuenta=Numero_Cuenta;
		this.Tipo_Cuenta=Tipo_Cuenta;
		this.Estado=Estado;
		this.Saldo=Saldo;
		this.fecha_creacion=new SimpleDateFormat("dd/MM/yyyy").parse(fecha_creacion); 
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
	public int getTipo_Cuenta()
	{
		return this.Tipo_Cuenta;
	}
	public void setTipo_Cuenta(int Tipo_Cuenta)
	{
		this.Tipo_Cuenta=Tipo_Cuenta;
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
	public Date getfecha_creacion()
	{
		return this.fecha_creacion;
	}
	public void setfecha_creacion(Date fecha_creacion)
	{
		this.fecha_creacion=fecha_creacion;
	}
	public String toString()
	{
		return "Cuentas [Tipo_Id="+this.Tipo_Id+", Numero_Id="+this.Numero_Id+", Numero_Cuenta="+this.Numero_Cuenta+", Tipo_Cuenta="+this.Tipo_Cuenta+"]";	
	}
}
