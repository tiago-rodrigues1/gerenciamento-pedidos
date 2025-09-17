package br.edu.ufr.bti.lp.projeto1;


public class Item {
	private String nome;
	private double preco;
	
	public Item() {}
	
	public Item(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}	
}
