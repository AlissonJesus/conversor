package br.com.alura.conversor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConversorDeMoeda {
	private List<String> siglas;
	private int opcao;
	private int valor;
	private final String URL_BASE = "https://v6.exchangerate-api.com/v6/3c1841076e4ed7c9b84cc59a/latest/";
	private String sigla2;
	private String sigla1;
	
	
	

	public ConversorDeMoeda() {	
		siglas = new ArrayList();		
		siglas.add("USD");
		siglas.add("EUR");
		siglas.add("BRL");
		siglas.add("ARS");
	}
	
	
	public void exibirMenu() {		
		int base = 0;
		int i = 1;
		int numeroOpcao = 0;
		
		while (i < siglas.size()) {			
			String moedaNome1 = obterNomeMoeda(siglas.get(base));
			String moedaNome2 = obterNomeMoeda(siglas.get(i));	
			++numeroOpcao;
			System.out.println(numeroOpcao +") " + moedaNome1 + " =>> " + moedaNome2);
			++numeroOpcao;
			System.out.println( numeroOpcao +") " + moedaNome2 + " =>> " + moedaNome1);
			++i;
		}
	}
	
	public String obterNomeMoeda(String sigla) {
		if(sigla == "USD") return "Dóllar";
		if(sigla == "EUR") return "Euro";
		if(sigla == "BRL") return "Real";
		return "Peso argentino";
	}
	
	public Double obterMoedaConvertida(int opcao) {
		ConversorRates cotacoes;
		sigla1 = "USD";
		sigla2 = "USD";
		if(opcao == 1) {
			sigla1 = "EUR";
			cotacoes = obterCotacoes("USD");
			return cotacoes.EUR();
		}
		if(opcao == 2) {
			sigla2 = "EUR";
			cotacoes = obterCotacoes("EUR");
			return cotacoes.USD();
			
		}
		if(opcao == 3) {
			sigla1 = "BRL";
			cotacoes = obterCotacoes("USD");
			return cotacoes.BRL();
		}
		if(opcao == 4) {
			sigla2 = "BRL";
			cotacoes = obterCotacoes("BRL");
			return cotacoes.USD();
		}
		if(opcao == 5) {
			sigla1 = "ARS";
			cotacoes = obterCotacoes("USD");
			return cotacoes.ARS();
		}
		if(opcao == 6) {
			sigla2 = "ARS";
			cotacoes = obterCotacoes("ARS");
			return cotacoes.USD();
		}
		return null;
	}
	
	
	public String exibirMensagemMoedaConvertida() {
		Double moedaConvertida = obterMoedaConvertida(opcao);
		
		
		
		return "Valor " + valor + ".0 ["+ sigla2 + "]" + " corresponde ao valor final de =>>> " 
		+ (moedaConvertida * valor)  + "["+ sigla1 + "]";
	}
	
	private ConversorRates obterCotacoes(String sigla) {
		JsonObject dadosApi = consumirApi(sigla);
		return converterDados(dadosApi);
	}
	
	private JsonObject consumirApi(String sigla) {
		String endereco =  URL_BASE + sigla;
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
		try {
			HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
			String json = response.body();
			
			JsonObject rootObject = JsonParser.parseString(json).getAsJsonObject();
			return rootObject.getAsJsonObject("conversion_rates");		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private ConversorRates converterDados(JsonObject ConversionRatesJson) {
		Gson gson = new Gson();
		return  gson.fromJson(ConversionRatesJson, ConversorRates.class);
	}
	

	

	public void setOpcao(int opcao) {
		this.opcao = opcao;
	}

	public void setValor(int valor) {
		this.valor = valor;
		
	}
	
	

}
