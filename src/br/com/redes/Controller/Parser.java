package br.com.redes.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.redes.model.Pagina;
import br.com.redes.model.Termo;

public class Parser {
	public static String[] stopListPortugues = new String[]{"a", "abaixo", "acaso", "acima", "afinal", "afora", "agora", "ah", "ai", "ainda", "alem", "algo", "alguem", "algum", "alguma", "algumas", "alguém", "ali", "alias", "aliás", "alta", "alto", "além", "amanha", "amanhã", "ante", "antes", "ao", "aos", "apenas", "apos", "após", "aquela", "aquelas", "aquele", "aqueles", "aquem", "aqui","aquilo", "aquém", "as", "assim", "ate" , "atencao", "atenção", "atras", "atrás", "até", "aí", "baixa", "baixas", "baixo", "baixos", "bastante", "bem", "bilhao", "bilhão", "bom", "ca", "cada", "caso", "catorze", "cedo", "cem", "centena", "centesimo", "centésimo", "certo", "cinco", "cinquenta", "cinqüenta", "click", "clique", "com", "comigo", "como", "conforme", "conosco", "consigo", "consoante", "contanto", "contigo", "contra", "contudo", "convosco", "cuidado", "cuja", "cujas", "cujas", "cujo", "cá", "da", "daquela", "daquelas", "daquele", "daqueles", "daquilo", "das", "de", "debaixo", "decimo", "dela", "delas", "dele", "deles", "demais", "dentro", "depois", "desde", "dessa", "dessas", "desse", "desses", "desta", "destas", "deste", "destes", "determinado", "deve", "dez", "dezena", "dezenove", "dezesseis", "dezessete", "dezoito", "disso", "do", "dobro", "dois", "dos", "doze", "duas", "duplo", "durante", "duzentas", "duzentos", "duzia", "dúzia", "e", "e-mail", "eis", "ela", "elas", "ele", "eles", "em", "em", "email", "embora", "enfim", "enquanto", "entao", "entre", "entretanto", "então", "era", "essa", "essas", "esse", "esses", "esta", "estamos", "estas", "este", "estes", "está", "estão", "eu", "exceto", "exclusiva", "exclusivo", "exemplo", "exemplos", "faz", "foi", "fora", "forte", "fortes", "fraca", "fracas", "fraco", "fracos", "frente", "grande", "ha", "hoje", "hum", "há", "inclusive", "isso", "isto", "ja", "jamais", "já", "la", "lhe", "lhes", "logo", "longe", "mais", "mal", "maneira", "maneiras", "mas", "mau", "me", "medida", "meio", "meios", "melhor", "menos", "mesma", "mesmas", "mesmo", "mesmos", "metade", "meu", "meus", "mil", "milesimo", "milesimos", "milhao", "milhar", "milhoes", "milhão", "milhões", "milésimo", "milésimos", "mim", "minha", "minhas", "modo", "modos", "muitas", "muito", "muitos", "na", "nada", "nao", "nas", "nem", "nenhum", "nenhuma", "nesta", "ninguem", "ninguém", "no", "nono", "nos", "nossa", "nossas", "nosso", "nossos", "nove", "novecentas", "novecentos", "noventa", "numa", "nunca", "não", "nós", "o", "oba", "oh", "oitavo", "oitenta", "oito", "oitocentas", "oitocentos", "olha", "onde", "ontem", "onze", "opa", "ora", "os", "ou", "outra", "outro", "outrora", "outros", "para", "pela", "pelas", "pelo", "pelos", "pequena", "pequeno", "perante", "perto", "pior", "pode", "pois", "por", "porem", "porquanto", "porque", "portanto", "porventura", "porém", "poucas", "pouco", "pra", "primeira", "primeiras", "primeiro", "primeiros", "proporcao", "proporcoes", "proporcões", "proporção", "propria", "proprias", "proprio", "proprios", "própria", "próprias", "próprio", "próprios", "puxa", "pôr", "quais", "quaisquer", "qual", "qualquer", "quando", "quanta", "quantas", "quanto", "quantos", "quao", "quarenta", "quarto", "quase", "quatorze", "quatro", "quatrocentas", "quatrocentos", "que", "quem", "quer", "quica", "quinhentas", "quinhentos", "quinta", "quinto", "quinze", "quão", "saber", "salve", "salvo", "sao", "se", "segunda", "segundo", "seis", "seiscentas", "seiscentos", "seja", "sem", "semelhante", "sempre", "senao", "senão", "sequer", "ser", "sera", "será", "serão", "sessenta", "sete", "setecentas", "setecentos", "setenta", "setimo", "seu", "seus", "sexto", "si", "sim", "so", "sob", "sobre", "sobretudo", "somente", "sua", "suas", "são", "sétimo", "só", "tal", "talvez", "tambem", "também", "tampouco", "tanto", "tao", "tarde", "te", "tem", "terca", "terceira", "terceiro", "terco", "terça", "terço", "teu", "teus", "ti", "todavia", "todos", "tres", "treze", "trezentas", "trezentos", "trigesimo", "trigésimo", "trilhao", "trilhão", "trinta", "triplice", "triplo", "três", "tríplice", "tu", "tua", "tuas", "tudo", "tão", "ui", "um", "uma", "umas", "unica", "unicas", "unico", "unicos", "uns", "varias", "varios", "vez", "vezes", "vigesimo", "vigésimo", "vinte", "viva", "voce", "voces", "você", "vocês", "vos", "vossa", "vossas", "vosso", "vossos", "vái", "várias", "vários", "vós", "à", "às", "é", "única", "únicas", "único", "únicos"
	};
	public static String stopListEnglish[] = {"a", "about", "above", "after", "again", "against", "age", "all", "almost", "also", "always", "am", "among", "an", "and", "any", "anybody", "anyone", "anything", "anywhere", "are", "as", "asked", "at", "away", "back", "backwards", "be", "because", "been", "before", "being", "below", "beside", "between", "beyond", "both", "but", "by", "called", "came", "can", "case", "come", "could", "day", "days", "did", "do", "doing", "done", "down", "downwards", "during", "e-mail", "e-mails", "each", "eight", "else", "email", "emails", "end", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "far", "few", "find", "first", "first", "five", "footnote", "for", "found", "four", "from", "gave", "general", "get", "give", "go", "good", "got", "great", "had", "has", "have", "having", "he", "heard", "her", "here", "hers", "herself", "him", "himself", "his", "house", "how", "however", "hundred", "i", "if", "in", "inside", "into", "is", "it", "its", "just", "know", "last", "left", "less", "let", "life", "light", "little", "long", "look", "looked", "made", "make", "many", "may", "me", "mine", "more", "most", "mr", "much", "must", "my", "myself", "never", "new", "next", "night", "nine", "no", "none", "nor", "not", "nothing", "now", "of", "off", "ok", "old", "on", "once", "one", "only", "onto", "or", "other", "others", "ought", "our", "ours", "ourselves", "out", "outside", "outwards", "over", "own", "part", "people", "perhaps", "place", "put", "right", "said", "same", "saw", "say", "see", "seemed", "seven", "shall", "she", "should", "side", "since", "six", "so", "some", "somebody", "someone", "something", "sometimes", "somewhere", "soon", "still", "such", "take", "tell", "ten", "than", "that", "the", "their", "theirs", "theirselves", "them", "then", "there", "these", "they", "things", "think", "this", "those", "though", "thought", "thousand", "three", "through", "thus", "time", "to", "today", "tomorrow", "too", "took", "towards", "twenty", "two", "under", "underneath", "until", "up", "upon", "upwards", "us", "very", "wanna", "war", "was", "way", "we", "well", "went", "were", "what", "whatever", "when", "whenever", "whether", "which", "while", "who", "whoever", "whom", "whose", "why", "will", "with", "without", "words", "work", "would", "years", "yes", "yesterday", "yet", "you", "your", "yours", "yourself", "yourselves"
	};
	private List<String> listaPt = Arrays.asList(stopListPortugues);
	private List<String> listaEn = Arrays.asList(stopListPortugues);
	
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
		codigo = codigo.replaceAll("[/./,/:/;/-/'/&/%/?/!/*/+/#]", " ");
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

				if(!listaPt.contains(term) && !listaEn.contains(term)){
					nTermo = new Termo(atualizaPeso(auxTag), term);
					if(!term.equals("")){
						contem = false;
						for (Termo auxTerm : centroide.getTermos()){
							if(auxTerm.getTermo().equals(term)){
								int indice = centroide.getTermos().indexOf(auxTerm);
//								System.out.println(auxTag);
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