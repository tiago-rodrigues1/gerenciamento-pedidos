package br.edu.ufr.bti.lp.projeto1;

import java.util.ArrayList;
import java.util.Scanner;

public class Pedido {
	private int id;
	private String cliente;
	private double valorTotal;
	private ArrayList<Item> itens = new ArrayList<Item>();
	
	public Pedido() {}
	
	public Pedido(int id, String cliente) {
		this.id = id;
		this.cliente = cliente;
	}
	
	public Pedido(int id, String cliente, ArrayList<Item> itens) {
		this.id = id;
		this.cliente = cliente;
		this.itens = itens;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Item> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Item> itens) {
		this.itens = itens;
	}
	
	public double getValorTotal() {
		return this.valorTotal;
	}
	
	public Item adicionarItem() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println(">> Nome do item [-c para voltar]:");
		String nome = scanner.nextLine();
		
		if (nome.isEmpty()) {
			System.out.println(">>> ERRO: Nome não pode ser vazio");
			return null;
		} else if (nome.equalsIgnoreCase("-c")) {
			return null;
		}
		
		System.out.println(">> Preço do item:");
		double preco = scanner.nextDouble();
		
		if (preco <= 0) {
			System.out.println(">>> ERRO: Preço deve ser maior que 0");
			return null;
		}
		
		Item i = new Item(nome, preco);
		this.itens.add(i);
		this.valorTotal += preco;
		
		return i;
		
	}
	
	public void listarItens() {
		for (Item item : this.getItens()) {
			System.out.printf("  %s...R$ %.2f\n", item.getNome(), item.getPreco());
		}
	}
	
	public void mostrar() {
		System.out.println("========================================");
		System.out.printf("               PEDIDO Nº %d             \n", this.getId());
		System.out.println("========================================");
		System.out.printf("> Nome do cliente: %s\n", this.getCliente());
		System.out.printf("> Valor: R$ %.2f\n", this.getValorTotal());
		System.out.println("> Itens:");
		this.listarItens();
	}
	
	
}
