package br.com.tecnow.models;
/**
 * Enum do g�nero dos usu�rios.
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
