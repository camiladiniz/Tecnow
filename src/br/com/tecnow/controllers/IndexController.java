package br.com.tecnow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Controller da página index da aplicação
 * @author Camila
 *
 */
@Controller
public class IndexController {

	/**
	 * Abre a página publica do sistema TecNow
	 * @return
	 */
	@RequestMapping("/")
	public String abreIndex() {
		return "index";
	}
	
}
