package sistema;

public class Quarto {

	int id;
	String tipo;
	int camas;
	String descricao;
	double preco;
	String status;
	
	Quarto(int id, String tipo, int camas, String descricao, double preco, String status) {
		this.id = id;
		this.tipo = tipo;
		this.camas = camas;
		this.descricao = descricao;
		this.preco = preco;
		this.status = status;
	}
	
	String imprimir() {
		return "Quarto [id=" + id + ", tipo=" + tipo + ", camas=" + camas + ", descricao=" + descricao + ", preco="
				+ preco + ", status=" + status + "]";
	}
}
