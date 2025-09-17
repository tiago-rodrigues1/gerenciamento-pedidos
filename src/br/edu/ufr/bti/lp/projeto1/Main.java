package br.edu.ufr.bti.lp.projeto1;

public class Main {

	public static void main(String[] args) {
		Sistema app = new Sistema();
		
		while (!app.podeEncerrar()) {
			app.menu();
			app.processar();
		}

	}

}
