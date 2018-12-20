package br.com.tecnow.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * Atributos e getters & setters dos jogos
 * @author Camila
 *
 */
public class Jogo {

	//Atributos
	private Long id;
	
	private String nome;
	
	private Categoria categoria;
	
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
