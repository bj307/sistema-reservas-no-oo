package sistema;

public class Cliente {

	int id;
	String nome;
	String cpf;
	String email;
	String celular;
	
	public Cliente(int id, String nome, String cpf, String email, String celular) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.celular = celular;
	}
	
	public String imprimir() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", celular=" + celular
				+ "]";
	}

}
