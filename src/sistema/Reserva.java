package sistema;

public class Reserva {

	int id;
	Cliente cliente;
	Quarto quarto;
	int diarias;
	double total;
	
	public Reserva(int id, Cliente cliente, Quarto quarto, int diarias, double total) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.quarto = quarto;
		this.diarias = diarias;
		this.total = quarto.preco * diarias;
	}
	
	public String imprimir() {
		return "Reserva [id=" + id + ", cliente=" + cliente + ", quarto=" + quarto + ", diarias=" + diarias + ", total="
				+ total + "]";
	}
	
}
