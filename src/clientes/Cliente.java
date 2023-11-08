package clientes;

public class Cliente {

	public int id;
	public String nome;
	public String cpf;
	public String email;
	public String celular;
	
	public Cliente(int id, String nome, String cpf, String email, String celular) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.celular = celular;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", celular=" + celular
				+ "]";
	}

}
