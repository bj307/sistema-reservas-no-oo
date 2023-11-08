package quartos;

public class Quarto {

	public int id;
	public String tipo;
	public int camas;
	public String descricao;
	public double preco;
	public String status;
	
	public Quarto(int id, String tipo, int camas, String descricao, double preco, String status) {
		this.id = id;
		this.tipo = tipo;
		this.camas = camas;
		this.descricao = descricao;
		this.preco = preco;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Quarto [id=" + id + ", tipo=" + tipo + ", camas=" + camas + ", descricao=" + descricao + ", preco="
				+ preco + ", status=" + status + "]";
	}
	
}
