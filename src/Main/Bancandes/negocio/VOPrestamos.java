package Main.Bancandes.negocio;

public interface VOPrestamos {
	public long getId_Prestamo();
	public int getTipo_Id();
	public long getNumero_Id();
	public int getTipo_Prestamo();
	public int getMonto();
	public int getEstado();
	public int getSaldo();
	public String toString();
}
