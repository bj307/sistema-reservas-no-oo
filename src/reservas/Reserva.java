package reservas;
import quartos.*;
import clientes.*;

public class Reserva {

	public int id;
	public Cliente cliente;
	public Quarto quarto;
	public int diarias;
	public double total;
	
	public Reserva(int id, Cliente cliente, Quarto quarto, int diarias, double total) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.quarto = quarto;
		this.diarias = diarias;
		this.total = quarto.preco * diarias;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", cliente=" + cliente + ", quarto=" + quarto + ", diarias=" + diarias + ", total="
				+ total + "]";
	}
	
}
