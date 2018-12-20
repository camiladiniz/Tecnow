package br.com.tecnow.utils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tecnow.models.Usuario;

/**
 * Gerenciados de sessões.
 * Armazena o usuário na sessão.
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
	 * Armazena um objeto usuário na sessão para realizar controle de sessão
	 * @param usuario
	 */
	public void setUsuarioLogado(Usuario usuario) {
		session.setAttribute(USUARIO_CHAVE, usuario);
	}
	
	/**
	 * Verifica se o usuário está na sessão
	 * @return
	 */
	public boolean isUsuarioLogado() {
		return session.getAttribute(USUARIO_CHAVE) != null;
	}
	
	/**
	 * Remove o usuário da sessão matando a sessão.
	 */
	public void removerUsuariologado() {
		session.invalidate();
	}
	
	/**
	 * Retorna o usuário que está logado na sessão
	 * @return
	 */
	public Usuario getUsuarioLogado() {
		return (Usuario) session.getAttribute(USUARIO_CHAVE);
	}
}
