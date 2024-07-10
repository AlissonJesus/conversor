package br.com.alura.conversor;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner terminal = new Scanner(System.in);
			
		while(true) {
			ConversorDeMoeda conversor = new ConversorDeMoeda();
			System.out.println("************************************************");
			System.out.println("Seja bem vindo ao Conversor de moeda =]");
			
			System.out.println(" ");
			conversor.exibirMenu();
			System.out.println(" ");
			
			System.out.println("Escolha um opção válida");
			System.out.println("************************************************");
			
			
			int opcao = terminal.nextInt();
			
			if(opcao == 7) {
				return;
			}
			
			if(opcao ==0 || opcao > 7) {
				System.out.println(" ");
				System.out.println("Opção inválida");
				System.out.println(" ");
				continue;
			}
				
			
			conversor.setOpcao(opcao);
			
			System.out.println(" ");
			System.out.println("Digite o valor que deseja converter:");
			int valor = terminal.nextInt();
			conversor.setValor(valor);
			
			String mensagem = conversor.exibirMensagemMoedaConvertida();
			System.out.println(" ");
			System.out.println(mensagem);
			System.out.println(" ");
			
		}
		
	}
	
	

}
