package br.com.tecnow.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Atributos e getters & setters do anime
 * @author Camila
 *
 */
public class Anime {

	//Atributos
	private Long id;
	
	private String nome;
	
	private Date dataCadastro;
	
	private Usuario usuario;

	
	//Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
