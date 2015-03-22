package br.com.redes;

import java.io.IOException;

import br.com.redes.Controller.Parser;

public class main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		Parser parser = new Parser("http://localhost/mirror-fashion/");
		parser.filtrarTags();
	}

}
