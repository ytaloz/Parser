package br.com.redes.model;

public class Termo {
	private String termo;
	private int peso;
	private int nOcorrencias;
	
	public Termo(int peso, String termo) {
		this.peso = peso;
		this.termo = termo;
		nOcorrencias = 1;
	}
	
	public String getTermo() {
		return termo;
	}
	public void setTermo(String termo) {
		this.termo = termo;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getnOcorrencias() {
		return nOcorrencias;
	}
	public void addOcorrencias() {
		this.nOcorrencias ++;
	}
}
