package br.com.redes;

import java.io.IOException;

import br.com.redes.Controller.Parser;

public class main {
	public static void main(String[] args) throws IOException{
		Parser parser = new Parser("http://www.uefs.br/portal/noticias/2014/governo-vai-recontratar-professora-do-curso-de-psicologia");
		parser.filtrarCodigo();
	}

}
