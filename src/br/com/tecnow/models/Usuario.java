package br.com.tecnow.models;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.DigestUtils;
/**
 * Classe com atributos e métodos Getters & Setters do Usuario
 * @author Camila
 *
 */
public class Usuario {
	
	//Atributos
	private Long id;

	private String nome;
	
	private Date dataNascimento;
	
	private Sexo sexo;
	
	private String email;
	
	private String senha;

	private String novaSenha;
	
	//Métodos de hashear senha para maior segurança
	public void hashearSenha() {
		try {
			this.senha = DigestUtils.md5DigestAsHex(this.senha.getBytes("UTF-8"));
		}catch(UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void hashearSenha(String senha) {
		try {
			this.senha = DigestUtils.md5DigestAsHex(this.senha.getBytes("UTF-8"));
		}catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
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
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNovaSenha() {
		return novaSenha;
	}


	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", sexo=" + sexo
				+ ", email=" + email + ", senha=" + senha + "]";
	}
	
	
}
