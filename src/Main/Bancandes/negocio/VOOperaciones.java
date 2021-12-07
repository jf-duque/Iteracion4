package Main.Bancandes.negocio;

import java.util.Date;

public interface VOOperaciones {
	public long getId_Operacion();
	public int getTipo_Id();
	public long getNumero_Id();
	public long getNumero_Cuenta();
	public int getValor();
	public long getPuesto_Atencion();
	public long getEmpleado();
	public Date getFecha();
	public String toString();
}
