package br.edu.ufr.bti.lp.projeto1;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
	private int operacao;
	private int n_pedido = 0;
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

	public int getOperacao() {
		return operacao;
	}

	public void setOperacao(int op) {
		this.operacao = op;
	}

	public boolean podeEncerrar() {
		return this.getOperacao() == 4;
	}

	private Pedido fazerPedido() {
		Scanner scanner = new Scanner(System.in);
		int prox_id = this.n_pedido + 1;

		System.out.println("========================================");
		System.out.printf("               PEDIDO Nº %d             \n", prox_id);
		System.out.println("========================================");

		System.out.println("> Nome do cliente [-c para voltar]:");
		String nome = scanner.nextLine();

		if (nome.isEmpty()) {
			System.out.println(">>> ERRO: Nome não pode ser vazio");
			return null;
		} else if (nome.equalsIgnoreCase("-c")) {
			return null;
		}

		Pedido p = new Pedido(prox_id, nome);

		System.out.println("> Itens:");

		boolean adicionarItem = true;

		while (adicionarItem) {
			p.listarItens();
			System.out.println();
			p.adicionarItem();

			System.out.print("> Adicionar item [S/N]: ");
			String resp = scanner.next().trim();

			adicionarItem = resp.equalsIgnoreCase("S");
		}
		
		if (p.getItens().size() == 0) {
			System.out.println(">>> ERRO: Nenhum item adicionado, pedido não realizado");
			return null;
		}

		this.n_pedido = prox_id;
		return p;
	}
	
	public int buscaPedido(int targetId) {
		int minIdx = 0, maxIdx = this.pedidos.size() - 1;
		
		while (minIdx <= maxIdx) {
			int midIdx = (minIdx + maxIdx) / 2;
			
			Pedido atual = this.pedidos.get(midIdx);
			int id = atual.getId();
			
			if (id == targetId) {
				return midIdx;
			} else if (id > targetId) {
				maxIdx = midIdx - 1;
			} else {
				minIdx = midIdx + 1;
			}
		}
		
		return -1;
	}
	
	public boolean removerPedido() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("> Nº do pedido para remover:");
		int id = scanner.nextInt();
		
		int pedidoIdx = this.buscaPedido(id);
		
		if (pedidoIdx == -1) {
			System.out.printf(">>> ERRO: pedido com id = %d não encontrado\n", id);
			return false;
		}
		
		Pedido p = this.pedidos.get(pedidoIdx);
		p.mostrar();
		
		System.out.print("> Confirma remoção do pedido [S/N]: ");
		String resp = scanner.next().trim();
		
		if (resp.equalsIgnoreCase("n")) {
			return false;
		}
		
		this.pedidos.remove(pedidoIdx);
		return true;
		
	}
	
	public void listarPedidos() {
		if (this.pedidos.size() == 0) {
			System.out.println(">>> Nenhum pedido cadastrado ainda");
			return;
		}
		
		for (Pedido p : this.pedidos) {
			p.mostrar();
			System.out.println();
		}
	}

	public void menu() {
		System.out.println("\n========================================");
		System.out.println("             La Ratatouille             ");
		System.out.println("========================================");

		System.out.println("[1] Registrar pedido");
		System.out.println("[2] Remover pedido");
		System.out.println("[3] Listar pedidos");
		System.out.println("[4] Sair");

		System.out.print("\n> Escolha uma opção: ");

		Scanner scanner = new Scanner(System.in);
		int op = scanner.nextInt();

		setOperacao(op);
	}

	public void processar() {
		switch (getOperacao()) {
			case 1: {
				Pedido p = this.fazerPedido();
				
				if (p != null) {
					System.out.println("Pedido realizado com sucesso!");
					this.pedidos.add(p);
					p.mostrar();
				}
				
				break;
			}
			
			case 2: {
				if (this.removerPedido()) {
					System.out.println("Pedido removido com sucesso!");
				}
				
				break;
			}
			
			case 3: {
				this.listarPedidos();
				break;
			}
			
			case 4: {
				System.out.println("Encerrando programa. Obrigado :)");
				break;
			}
			
			default: {
				System.out.println(">>> ERRO: Comando inválido");
				break;
			}

		}
	}
}
