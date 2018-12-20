package br.com.tecnow.utils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tecnow.models.Usuario;

/**
 * Gerenciados de sess�es.
 * Armazena o usu�rio na sess�o.
 * @author Camila Diniz
 *
 */
@Component
public class SessionUtils {
	
	/*
	 * Chave para identificar usuarios logado
	 */
	public static final String USUARIO_CHAVE = "usuarioLogado";
	
	@Autowired
	private HttpSession session;
	
	/**
	 * Armazena um objeto usu�rio na sess�o para realizar controle de sess�o
	 * @param usuario
	 */
	public void setUsuarioLogado(Usuario usuario) {
		session.setAttribute(USUARIO_CHAVE, usuario);
	}
	
	/**
	 * Verifica se o usu�rio est� na sess�o
	 * @return
	 */
	public boolean isUsuarioLogado() {
		return session.getAttribute(USUARIO_CHAVE) != null;
	}
	
	/**
	 * Remove o usu�rio da sess�o matando a sess�o.
	 */
	public void removerUsuariologado() {
		session.invalidate();
	}
	
	/**
	 * Retorna o usu�rio que est� logado na sess�o
	 * @return
	 */
	public Usuario getUsuarioLogado() {
		return (Usuario) session.getAttribute(USUARIO_CHAVE);
	}
}
