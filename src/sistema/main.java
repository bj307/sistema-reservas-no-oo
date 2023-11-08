package sistema;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import quartos.*;
import clientes.*;
import reservas.*;

public class main {

	public static void main(String[] args) {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		ArrayList<Quarto> quartos = new ArrayList<Quarto>();
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();

		Cliente cliente = new Cliente(1, "Kaio", "11111111111", "email@email.com", "11111111111");
		clientes.add(cliente);
		Quarto quarto = new Quarto(1, "VIP", 02, "NORMAL", 59.90, "DESOCUPADO");
		quartos.add(quarto);
		salvarEmArquivo(clientes, "Clientes");
		salvarEmArquivo(quartos, "Quartos");
		exibirOpcoes(clientes, reservas, quartos);
	}

	public static void exibirOpcoes(ArrayList<Cliente> clientes, ArrayList<Reserva> reservas,
			ArrayList<Quarto> quartos) {
		String[] opcoes = { "Cadastrar cliente", "Cadastrar quarto", "Efetuar reserva", "Listar clientes",
				"Listar quartos", "Listar reservas", "Sair" };

		while (true) {
			int escolha = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Opções",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

			if (escolha >= 0) {
				if (opcoes[escolha].equals("Sair")) {
					System.exit(0);
				} else {
					//cadastrar cliente
					if (opcoes[escolha].equals("Cadastrar cliente")) {
						cadastrarCliente(clientes);
					} 
					//cadastrar quarto
					else if (opcoes[escolha].equals("Cadastrar quarto")) {
						cadastrarQuarto(quartos);
					} 
					//fazer reserva
					else if (opcoes[escolha].equals("Efetuar reserva")) {
						String cpf = JOptionPane.showInputDialog(null, "Insira o CPF do cliente:", "Buscar cliente",
								JOptionPane.PLAIN_MESSAGE);
						Cliente cliente = buscarCliente(clientes, cpf);
						
						while (cliente == null) {
							cpf = (String) JOptionPane.showInputDialog(null, "Insira o CPF do cliente:", "CLIENTE NÃO ENCONTRADO",
									JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null, null);
						}
						
						String id = JOptionPane.showInputDialog(null, "Insira o ID do quarto:", "Buscar quarto",
								JOptionPane.PLAIN_MESSAGE);
						
						Quarto quarto = buscarQuarto(quartos, Integer.parseInt(id));
						
						while (quarto == null) {
							id = (String) JOptionPane.showInputDialog(null, "Insira o ID do quarto:", "QUARTO NÃO ENCONTRADO OU ESTÁ OCUPADO",
									JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null, null);
						}
						efetuarReserva(reservas, cliente, quarto);
					} 
					//Listar clientes
					else if (opcoes[escolha].equals("Listar clientes")) {
						listarClientes(clientes);
					}
					//Listar quartos
					else if (opcoes[escolha].equals("Listar quartos")) {
						listarQuartos(quartos);
					}
					//Listar reservas
					else if (opcoes[escolha].equals("Listar reservas")) {
						listarReservas(reservas);
					}
					//sair
					else {
						System.exit(0);
					}
				}
			} else {
				System.exit(0);
			}
		}
	}

	public static void cadastrarCliente(ArrayList<Cliente> clientes) {

		// recebe nome
		String nome = JOptionPane.showInputDialog(null, "Insira o nome do cliente:", "Cadastro de cliente",
				JOptionPane.PLAIN_MESSAGE);
		if (nome == null) {
			return;
		}
		
		while (!nome.matches("^[a-zA-Z ]+$") || nome == null) {
			nome = (String) JOptionPane.showInputDialog(null, "Insira um nome válido:", "Cadastro de cliente",
					JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null, null);
			if (nome == null) {
				return;
			}
		}

		// recebe cpf
		String cpf = JOptionPane.showInputDialog(null, "Insira o CPF do cliente:", "Cadastro de cliente",
				JOptionPane.PLAIN_MESSAGE);
		if (cpf == null) {
			return;
		}
		Cliente clienteExiste = buscarCliente(clientes, cpf);
		
		while (!cpf.matches("\\d+") || cpf == null || cpf.length() != 11 || clienteExiste != null) {
			if (clienteExiste != null) {
				cpf = (String) JOptionPane.showInputDialog(null, "O CPF já está em uso:",
						"Cadastro de cliente", JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null,
						null);
			} else {
				cpf = (String) JOptionPane.showInputDialog(null, "Insira um CPF válido (11 dígitos):",
						"Cadastro de cliente", JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null,
						null);
			}
			
			if (cpf == null) {
				return;
			}
		}

		// cria regex pra valida o email
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		// recebe email
		String email = JOptionPane.showInputDialog(null, "Insira o email do cliente:", "Cadastro de cliente",
				JOptionPane.PLAIN_MESSAGE);
		if (email == null) {
			return;
		}
		while (!email.matches(regex) || email == null) {
			email = (String) JOptionPane.showInputDialog(null, "Insira um email válido:", "Cadastro de cliente",
					JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null, null);
			if (email == null) {
				return;
			}
		}

		// recebe celular
		String celular = JOptionPane.showInputDialog(null, "Insira o celular do cliente:", "Cadastro de cliente",
				JOptionPane.PLAIN_MESSAGE);
		if (celular == null) {
			return;
		}
		while (!celular.matches("\\d+") || celular == null || celular.length() != 11) {
			celular = (String) JOptionPane.showInputDialog(null, "Insira um celular válido (11 dígitos):",
					"Cadastro de cliente", JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null,
					null);
			if (celular == null) {
				return;
			}
		}

		int id = clientes.size() + 1;
		Cliente cliente = new Cliente(id, nome, cpf, email, celular);
		clientes.add(cliente);
		salvarEmArquivo(clientes, "Clientes");
	}

	public static void cadastrarQuarto(ArrayList<Quarto> quartos) {
		// recebe tipo
		String tipo = JOptionPane.showInputDialog(null, "Insira o tipo do quarto (VIP ou COMUM):", "Cadastro de quarto",
				JOptionPane.PLAIN_MESSAGE);
		if (tipo == null) {
			return;
		}
		while (!tipo.matches("^[a-zA-Z ]+$") || tipo == null || !tipo.equals("VIP") && !tipo.equals("COMUM")) {
			tipo = (String) JOptionPane.showInputDialog(null, "Insira um tipo válido (VIP ou COMUM):", "Cadastro de quarto",
					JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null, null);
			if (tipo == null) {
				return;
			}
		}

		// recebe camas
		String camas = JOptionPane.showInputDialog(null, "Insira o número de camas:", "Cadastro de quarto",
				JOptionPane.PLAIN_MESSAGE);
		if (camas == null) {
			return;
		}
		while (!camas.matches("\\d+") || camas == null || camas.length() != 2) {
			camas = (String) JOptionPane.showInputDialog(null, "Insira um número válido (2 dígitos):",
					"Cadastro de quarto", JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null,
					null);
			if (camas == null) {
				return;
			}
		}
		
		// recebe descricao
		String descricao = JOptionPane.showInputDialog(null, "Insira a descricao do quarto:", "Cadastro de quarto",
				JOptionPane.PLAIN_MESSAGE);
		if (descricao == null) {
			return;
		}
		while (!descricao.matches("^[a-zA-Z ]+$") || descricao == null) {
			descricao = (String) JOptionPane.showInputDialog(null, "Insira uma descricao válido:", "Cadastro de quarto",
					JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null, null);
			if (descricao == null) {
				return;
			}
		}

		// recebe preco
		String preco = JOptionPane.showInputDialog(null, "Insira o preço da diária Ex(59.90):", "Cadastro de quarto",
				JOptionPane.PLAIN_MESSAGE);
		if (preco == null) {
			return;
		}
		while (!preco.matches("^[-+]?[0-9]*\\.?[0-9]+$") || preco == null) {
			preco = (String) JOptionPane.showInputDialog(null, "Insira um preço válido Ex(59.90):",
					"Cadastro de quarto", JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null,
					null);
			if (preco == null) {
				return;
			}
		}
		
		//recebe status
		String status = JOptionPane.showInputDialog(null, "Insira o status do quarto (OCUPADO ou DESOCUPADO):", "Cadastro de quarto",
				JOptionPane.PLAIN_MESSAGE);
		if (status == null) {
			return;
		}
		while (!status.matches("^[a-zA-Z ]+$") || status == null || !status.equals("OCUPADO") && !status.equals("DESOCUPADO")) {
			status = (String) JOptionPane.showInputDialog(null, "Insira um tipo válido (OCUPADO ou DESOCUPADO):", "Cadastro de quarto",
					JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null, null);
			if (status == null) {
				return;
			}
		}

		int id = quartos.size() + 1;
		Quarto quarto = new Quarto(id, tipo, Integer.parseInt(camas), descricao, Double.parseDouble(preco), status);
		quartos.add(quarto);
		salvarEmArquivo(quartos, "Quartos");
	}

	public static void efetuarReserva(ArrayList<Reserva> reservas, Cliente cliente, Quarto quarto) {


		// recebe diarias
		String diarias = JOptionPane.showInputDialog(null, "Insira o número de diárias:", "Faça uma reserva",
				JOptionPane.PLAIN_MESSAGE);
		if (diarias == null) {
			return;
		}
		while (!diarias.matches("\\d+") || diarias == null || diarias.length() != 2) {
			diarias = (String) JOptionPane.showInputDialog(null, "Insira um número de diárias válido (2 dígitos):",
					"Faça uma reserva", JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null,
					null);
			if (diarias == null) {
				return;
			}
		}
		
		double total = Integer.parseInt(diarias) * quarto.preco;

		int id = reservas.size() + 1;
		Reserva reserva = new Reserva(id, cliente, quarto, Integer.parseInt(diarias), total);
		quarto.status = "OCUPADO";
		reservas.add(reserva);
		salvarEmArquivo(reservas, "Reservas");
	}

	public static void listarClientes(ArrayList<Cliente> clientes) {
		clientes.forEach(cliente -> System.out.println(cliente.toString()));
	}

	public static void listarQuartos(ArrayList<Quarto> quartos) {
		quartos.forEach(quarto -> System.out.println(quarto.toString()));
	}

	public static void listarReservas(ArrayList<Reserva> reservas) {
		reservas.forEach(reserva -> System.out.println(reserva.toString()));
	}

	public static <T> void salvarEmArquivo(ArrayList<T> array, String nomeArquivo) {
		
		File file = new File(nomeArquivo + ".txt");
        if (file.exists()) {
            file.delete();
        }
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo + ".txt"))) {
			for (T t : array) {
				writer.write(t.toString()); 
				writer.newLine();
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static Cliente buscarCliente(ArrayList<Cliente> clientes, String cpf) {
		Cliente cliente = null;
		
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).cpf.equals(cpf)) {
				cliente = clientes.get(i);
			}
		}
		
		return cliente;
	}
	
	public static Quarto buscarQuarto(ArrayList<Quarto> quartos, int id) {
		Quarto quarto = null;
		
		if (id > quartos.size()) {
			return null;
		} else if (quartos.get(id-1).status.equals("OCUPADO")) {
			return null;
		} else {
			quarto = quartos.get(id-1);
		}
		
		return quarto;
	}

}
