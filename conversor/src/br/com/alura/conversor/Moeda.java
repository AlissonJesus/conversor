package br.com.alura.conversor;

public class Moeda {
	private String sigla;
	private String nome;
	private double valor;
	
	public Moeda(String nome, String sigla, Double valor) {
		this.nome = nome;
		this.sigla = sigla;
		this.valor = valor;
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

	public double getValor() {
		return valor;
	}

	
	
	
	
	
	
	
}
