package br.com.tecnow.models;
/**
 * Enum das categorias disponíveis para os jogos
 * @author Camila
 *
 */
public enum Categoria {

	TIRO("Tiro"), RPG("RPG"), PLATAFORMA("Plataforma"), ESPORTE("Esporte"), HACK_AND_SLASH("Hack_and_slash"), OUTRO("Outro");
	
	public String categ;
	
	private Categoria(String categ) {
		this.categ = categ;
	}
	
}
