package sistema;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class main {

	public static void main(String[] args) {
		
		Cliente[] clientes = new Cliente[50];
		Quarto[] quartos = new Quarto[50];
		Reserva[] reservas = new Reserva[50];

		Cliente cliente = new Cliente(1, "Kaio", "11111111111", "email@email.com", "11111111111");
		clientes[0] = cliente;
		Quarto quarto = new Quarto(1, "VIP", 02, "NORMAL", 59.90, "DESOCUPADO");
		quartos[0] = quarto;
		salvarClientes(clientes);
		salvarQuartos(quartos);
		exibirOpcoes(clientes, reservas, quartos);
	}

	
	//metodo que exibe o joptionpane para exibir o menu com as opçoes
	//ele recebe 3 parametros que sao os vetores que armazena os dados de cliente, reserva e quartos
	public static void exibirOpcoes(Cliente[] clientes, Reserva[] reservas, Quarto[] quartos) {
		//vetor de opçoes do menu
		String[] opcoes = { "Cadastrar cliente", "Cadastrar quarto", "Efetuar reserva", "Listar clientes",
				"Listar quartos", "Listar reservas", "Ver dados", "Cancelar reserva", "Sair" };

		//foi usado while(true) para manter o menu aberto
		while (true) {
			int escolha = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Opções",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

			//aqui é identificado qual a opçao foi selecionada pelo usuario e chama o metodo equivalente
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
					//ver dados
					else if (opcoes[escolha].equals("Ver dados")) {
						verDados(clientes, reservas, quartos);
					}
					//cancelar reserva
					else if (opcoes[escolha].equals("Cancelar reserva")) {
						String id = JOptionPane.showInputDialog(null, "Insira o ID da reserva:", "Buscar reserva",
								JOptionPane.PLAIN_MESSAGE);
						
						Reserva reserva = buscarReserva(reservas, Integer.parseInt(id));
						
						while (reserva == null) {
							id = (String) JOptionPane.showInputDialog(null, "Insira o ID da reserva:", "RESERVA NÃO ENCONTRADA",
									JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null, null);
						}
						cancelarReserva(reservas, reserva.id);
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
	
	//submenu para ver dados unicos
	public static void verDados(Cliente[] clientes, Reserva[] reservas, Quarto[] quartos) {
		//vetor de opçoes do menu
		String[] opcoes = { "Ver cliente", "Ver quarto", "Ver reserva", "Ver reservas por cliente", "Voltar" };

		//foi usado while(true) para manter o menu aberto
		while (true) {
			int escolha = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Opções",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

			//aqui é identificado qual a opçao foi selecionada pelo usuario e chama o metodo equivalente
			if (escolha >= 0) {
				if (opcoes[escolha].equals("Voltar")) {
					return;
				} else {
					if (opcoes[escolha].equals("Ver cliente")) {
						String cpf = JOptionPane.showInputDialog(null, "Insira o CPF do cliente:", "Buscar cliente",
								JOptionPane.PLAIN_MESSAGE);
						Cliente cliente = buscarCliente(clientes, cpf);
						
						while (cliente == null) {
							cpf = (String) JOptionPane.showInputDialog(null, "Insira o CPF do cliente:", "CLIENTE NÃO ENCONTRADO",
									JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null, null);
						}
						
						System.out.println(cliente.imprimir());
					} 
					else if (opcoes[escolha].equals("Ver quarto")) {
						String id = JOptionPane.showInputDialog(null, "Insira o ID do quarto:", "Buscar quarto",
								JOptionPane.PLAIN_MESSAGE);
						
						Quarto quarto = lerQuarto(quartos, Integer.parseInt(id));
						
						while (quarto == null) {
							id = (String) JOptionPane.showInputDialog(null, "Insira o ID do quarto:", "QUARTO NÃO ENCONTRADO OU ESTÁ OCUPADO",
									JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null, null);
						}
						
						System.out.println(quarto.imprimir());
					} 
					else if (opcoes[escolha].equals("Ver reserva")) {
						String id = JOptionPane.showInputDialog(null, "Insira o ID da reserva:", "Buscar reserva",
								JOptionPane.PLAIN_MESSAGE);
						
						Reserva reserva = buscarReserva(reservas, Integer.parseInt(id));
						
						while (reserva == null) {
							id = (String) JOptionPane.showInputDialog(null, "Insira o ID da reserva:", "RESERVA NÃO ENCONTRADA",
									JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null, null);
						}
						
						System.out.println(reserva.imprimir());
					}
					else if (opcoes[escolha].equals("Ver reservas por cliente")) {
						String cpf = JOptionPane.showInputDialog(null, "Insira o CPF do cliente:", "Buscar cliente",
								JOptionPane.PLAIN_MESSAGE);
						Cliente cliente = buscarCliente(clientes, cpf);
						
						while (cliente == null) {
							cpf = (String) JOptionPane.showInputDialog(null, "Insira o CPF do cliente:", "CLIENTE NÃO ENCONTRADO",
									JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), null, null);
						}
						
						verReservasCliente(reservas, cpf);
					}
					else {
						return;
					}
				}
			} else {
				return;
			}
		}
	}
	
	public static void cancelarReserva(Reserva[] reservas, int id) {
		reservas[id].quarto.status = "DESOCUPADO";
		reservas[id] = null;
	}
	
	//ver reservas de um cliente
	public static void verReservasCliente(Reserva[] reservas, String cpf) {
		for (int i = 0; i < reservas.length; i++) {
			if (reservas[i] != null && reservas[i].cliente.cpf.equals(cpf)) {
				System.out.println(reservas[i].imprimir());
			}
		}
	}
	
	//o metodo busca no vetor de clientes um cliente com cpf especifico
	public static Cliente buscarCliente(Cliente[] clientes, String cpf) {
		Cliente cliente = null;
		
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] != null && clientes[i].cpf.equals(cpf)) {
				cliente = clientes[i];
			}
		}
		
		return cliente;
	}
	
	public static Quarto lerQuarto(Quarto[] quartos, int id) {
		Quarto quarto = null;
		
		if (id > quartos.length) {
			return null;
		} else {
			quarto = quartos[id];
		}
		
		return quarto;
	}
	
	//o metodo busca um quarto no vetor de quartos pelo id
	public static Quarto buscarQuarto(Quarto[] quartos, int id) {
		Quarto quarto = null;
		
		if (id > quartos.length) {
			return null;
		} else if (quartos[id].status.equals("OCUPADO")) {
			return null;
		} else {
			quarto = quartos[id];
		}
		
		return quarto;
	}
	
	//o metodo busca uma reserva no vetor de reservas pelo id
	public static Reserva buscarReserva(Reserva[] reservas, int id) {
		Reserva reserva = null;
		if (id > reservas.length) {
			return null;
		} else {
			reserva = reservas[id];
		}
		
		return reserva;
	}

	//metodo para cadastrar um cliente, recebe os dados, salva o cliente e adiciona no vetor clientes
	public static void cadastrarCliente(Cliente[] clientes) {

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

		int p = verificaPosicaoVetor(clientes);
		if(p == -1) {
			JOptionPane.showConfirmDialog(null, "Nosso hotel encontra-se lotado.");
		} else {
			Cliente cliente = new Cliente(p, nome, cpf, email, celular);
			clientes[p] = cliente;
		}
		salvarClientes(clientes);
	}

	//metodo para cadastrar quarto, salvar e adicionar no vetor de quartos
	public static void cadastrarQuarto(Quarto[] quartos) {
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

		int p = verificaPosicaoVetor(quartos);
		if (p == -1) {
			JOptionPane.showConfirmDialog(null, "Nosso hotel já esta com a capacidade máxima de quartos.");
		} else {
			Quarto quarto = new Quarto(p, tipo, Integer.parseInt(camas), descricao, Double.parseDouble(preco), status);
			quartos[p] = quarto;
		}
		salvarQuartos(quartos);
	}

	//metodo para efetuar reserva, salvar e adicionar no vetor de reservas
	public static void efetuarReserva(Reserva[] reservas, Cliente cliente, Quarto quarto) {

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

		
		int p = verificaPosicaoVetor(reservas);
		if (p == -1) {
			JOptionPane.showConfirmDialog(null, "Nosso hotel encontra-se lotado, sem vagas para reservas.");
		} else {
			Reserva reserva = new Reserva(p, cliente, quarto, Integer.parseInt(diarias), total);
			quarto.status = "OCUPADO";
			reservas[p] = reserva;
		}
		salvarReservas(reservas);
	}

	//o metodo lista todos os clientes do vetor de clientes
	public static void listarClientes(Cliente[] clientes) {
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
	}

	//o metodo vai listar todos os quartos do vetor quartos
	public static void listarQuartos(Quarto[] quartos) {
		for (Quarto quarto : quartos) {
			System.out.println(quarto);
		}
	}

	//o metodo vai listar todas as reservas do vetor reservas
	public static void listarReservas(Reserva[] reservas) {
		for (Reserva reserva : reservas) {
			System.out.println(reserva);
		}
	}
	
	//metodo recebe o vetor de clientes e salva em um arquivo Clientes.txt
	public static void salvarClientes(Cliente[] clientes) {
		//cria um arquivo com nome Clientes.txt para verificar se ele ja existe na pasta
		//se exister deve ser apagado
		File file = new File("Clientes.txt");
        if (file.exists()) {
            file.delete();
        }
        
        //salva um novo arquivo Clientes.txt com os dados atualizados do vetor clientes
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Clientes.txt"))) {
			for (Cliente c : clientes) {
				if (c != null) {
					writer.write(c.imprimir()); 
					writer.newLine();
				} else {
					writer.newLine();
				}
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//metodo recebe o vetor de reservas e salva em um arquivo Reservas.txt
	public static void salvarReservas(Reserva[] reservas) {
		File file = new File("Reservas.txt");
        if (file.exists()) {
            file.delete();
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Reservas.txt"))) {
			for (Reserva r : reservas) {
				if (r != null) {
					writer.write(r.imprimir()); 
					writer.newLine();
				} else {
					writer.newLine();
				}
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//metodo recebe o vetor de quartos e salva em um arquivo Quartos.txt
	public static void salvarQuartos(Quarto[] quartos) {
		File file = new File("Quartos.txt");
        if (file.exists()) {
            file.delete();
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Quartos.txt"))) {
			for (Quarto q : quartos) {
				if (q != null) {
					writer.write(q.imprimir()); 
					writer.newLine();
				} else {
					writer.newLine();
				}
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//esse metodo verifica uma posição nula no vetor e o retorna
	//é utilizado para saber se é possivel adicionar mais dados em um vetor
	public static int verificaPosicaoVetor(Object[] vetor) {
		int posicao = -1;
		int i = 0;
		while (posicao == -1 && i > vetor.length) {
			if (vetor[i] == null) {
				posicao = i;
			} else {
				i++;
			}
		}
		
		return posicao;
	}
}
