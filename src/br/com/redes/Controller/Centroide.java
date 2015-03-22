package br.com.redes.Controller;

import java.util.ArrayList;

import br.com.redes.model.Termo;

public class Centroide {
	private ArrayList<Termo> termos = new ArrayList<Termo>();;
	private String titulo;
	
	public ArrayList<Termo> getTermos() {
		return termos;
	}
	
	public void addTermo(Termo termo) {
		this.termos.add(termo);
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void exibeTermos(){
		for(Termo auxTerm : termos) {
			System.out.println("(" + auxTerm.getTermo() + ", " + auxTerm.getPeso() + ", " + auxTerm.getnOcorrencias() + "), ");
		}
	}
	
}
