package br.com.tecnow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Controller da p�gina index da aplica��o
 * @author Camila
 *
 */
@Controller
public class IndexController {

	/**
	 * Abre a p�gina publica do sistema TecNow
	 * @return
	 */
	@RequestMapping("/")
	public String abreIndex() {
		return "index";
	}
	
}
