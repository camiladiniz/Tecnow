package br.com.tecnow.models;
/**
 * Enum do gênero dos usuários.
 * @author Camila
 *
 */
public enum Sexo {

	FEMININO("Feminino"), MASCULINO("Masculino");
	
	public String genero;
	
	private Sexo(String genero) {
		this.genero = genero;
	}
	
}
