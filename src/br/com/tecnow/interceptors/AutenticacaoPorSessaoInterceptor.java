package br.com.tecnow.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.tecnow.utils.SessionUtils;

@Component
public class AutenticacaoPorSessaoInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private SessionUtils sessionUtils;
	
	/**
	 * Libera ou restringe acesso as páginas.
	 * Se retornar true : libera acesso;
	 * Se retornar false: bloqueia acesso.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean necessitaAutenticacao = request.getRequestURI().contains("/app");
		
		if(necessitaAutenticacao && ! sessionUtils.isUsuarioLogado()) {
			response.setStatus(403);
			//restringe acesso
			return false;
		}else {
			//libera acesso
			return true;
		}
		
	}
	
}
