package br.com.redes.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.StringTokenizer;

import br.com.redes.model.Pagina;
import br.com.redes.model.Termo;

public class Parser {
	private BufferedReader codigo;
	private Pagina pg = new Pagina();
	private Centroide centroide = new Centroide();;
	public Parser(String URL) throws IOException {
		lerURL(URL);
	}

	public void lerURL(String URL) throws IOException {
		// System.setProperty("http.proxyHost", "10.65.16.2");
		// System.setProperty("http.proxyPort", "3128");
		URL url = new URL(URL);
		URLConnection con = url.openConnection();
		codigo = new BufferedReader(new InputStreamReader(con.getInputStream()));
	}

	public void filtrarTags() throws IOException {
		String aux, texto ="";
		while ((aux = codigo.readLine()) != null) {
			texto = texto + aux;
		}
		pg.setTexto(texto);
		processaLinha(texto);
	}
	
	public void processaLinha(String codigo){
		String tag = "", auxTag = "", term = "";
		Termo nTermo;
		boolean contem = false;
		int peso = 0;
		for(int i= 0; i < codigo.length(); i++){
			if(codigo.charAt(i) == '<'){
				i++;
				if(codigo.charAt(i) == '/'){
					i++;
					while(codigo.charAt(i) != '>' && codigo.charAt(i) != ' ' ){
						if( i < codigo.length()){
							tag = tag + codigo.charAt(i);
						}
						i++;
					}
					if(!tag.equals("")){
						peso = peso - atualizaPeso(tag);
					}
				}else{
					while(codigo.charAt(i) != '>'){
						if( i < codigo.length()){
							tag = tag + codigo.charAt(i);
						}
						
						if(codigo.charAt(i) == ' '){
							while(codigo.charAt(i) != '>'){
								i++;
							}
							break;
						}	
						i++;
					}
					if(!tag.equals("")){
						peso = peso + atualizaPeso(tag);
					}
				}
				auxTag = tag;
				tag = "";
			}else{
				while(codigo.charAt(i) != ' '){
					term = term + codigo.charAt(i);			
					i++;
					if(codigo.charAt(i) == '<' || codigo.charAt(i) == '>'){
						i--;
						break;
					}
				}
				nTermo = new Termo(atualizaPeso(auxTag), term);
				if(!term.equals("")){
					contem = false;
					for (Termo auxTerm : centroide.getTermos()){
						if(auxTerm.getTermo().equals(term)){
							int indice = centroide.getTermos().indexOf(auxTerm);
//							System.out.println(auxTag);
							centroide.getTermos().get(indice).setPeso(centroide.getTermos().get(indice).getPeso() + atualizaPeso(auxTag));
							centroide.getTermos().get(indice).addOcorrencias();
							contem = true;
							break;
						}
					}
					if(!contem){
						centroide.addTermo(nTermo);
					}
				}
				
			}
			term = "";
		}
		for(int j = 0; j < centroide.getTermos().size(); j++) {
			System.out.print("(" +centroide.getTermos().get(j).getTermo() + ", " + centroide.getTermos().get(j).getPeso() + ", " + centroide.getTermos().get(j).getnOcorrencias() + "), ");
		}
	}


public int atualizaPeso(String key){
	int peso = 1;
	if(key.equals("title")){
		peso = 10;
	}else if(key.equals("h1")){
		peso = 7;
	}else if(key.equals("h2")){
		peso = 6;
	}else if(key.equals("h13") || key.equals("a")){
		peso = 5;
	}else if(key.equals("h4") || key.equals("h5") || key.equals("h6")){
		peso = 4;
	}else if(key.equals("big") || key.equals("b") || key.equals("em") || key.equals("i") || key.equals("u") || key.equals("strong") || key.equals("strike") || key.equals("center")){
		peso = 3;
	}else if(key.equals("small") || key.equals("sub") || key.equals("sup") || key.equals("font") || key.equals("address") || key.equals("meta")){
		peso = 2;
	}
	return peso;
}
}