package br.com.redes;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;

import br.com.redes.Controller.Parser;

public class main {
	public static void main(String[] args) throws IOException{
		Parser parser = new Parser("http://sites.ecomp.uefs.br/joao/home/courses/exa844");
		parser.filtrarCodigo();
		System.out.println("Titulo da pagina: " + parser.getTitle());
		System.out.println("Texto da pagina: " + parser.getPagina().getTexto());
		System.out.println("Numero de termos: " + parser.getCentroide().getNumTermos());
		System.out.println("Numero de termos Distitntos: " + parser.getCentroide().getNumTermosDistintos());
		System.out.println("Centroide:");
		parser.getCentroide().exibeTermos();
	}

}
