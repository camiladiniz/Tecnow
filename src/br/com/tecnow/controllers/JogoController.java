package br.com.tecnow.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.tecnow.dao.jdbc.JogoDao;
import br.com.tecnow.models.Jogo;
import br.com.tecnow.models.Usuario;
import br.com.tecnow.utils.SessionUtils;
/**
 * Classe é responsável pelo mapeamento da funções do jogo.
 * @author Camila Diniz
 *
 */
@Controller
@RequestMapping("/app/jogo")
public class JogoController {
	
	@Autowired
	private JogoDao jogoDao;
	
	@Autowired
	private SessionUtils sessionUtils;
	
	/**
	 * Abre o formulário de cadastro de jogos
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping(value = {"/cadastro"})
	public String abrirForm(@RequestParam(name = "id", required = false)Long id, Model model) {
		
		if(id != null) {
			Jogo j = jogoDao.buscar(id);
			model.addAttribute("jogo", j);
		}
		return "jogos/form";
	}
	
	/**
	 * Exclui um jogo do banco através do id
	 * @param id
	 * @return
	 */
	@GetMapping("/excluir")
	public String excluir(@RequestParam(name = "id", required=true) Long id) {
		
		Jogo jogo = new Jogo();
		jogo.setId(id);
		
		jogoDao.deletar(jogo);
		
		return "redirect:/app/jogo/";
		
	}
	
	/**
	 * Retorna a lista de jogos cadastrados pelo usuário logado
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value= {"/", ""})
	public String listaJogos(Model model, HttpSession session) {
		Usuario logado = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("jogos", jogoDao.buscarTodos(logado));
		
		return "jogos/jogosCadastrados";
	}
	
	/**
	 * Salva ou altera os dados do jogo
	 * @param jogo
	 * @param model
	 * @return
	 */
	@PostMapping("/salvar")
	public String salvar(Jogo jogo, Model model) {
		
		try {
			jogo.setUsuario(sessionUtils.getUsuarioLogado());
			
			List<String> erros = new ArrayList<>(6);
			
			if(jogo.getNome() == null) {
				erros.add("Campo nome é obrigatório!");
			}else if(jogo.getNome().length() < 1 || jogo.getNome().length() > 40) {
				erros.add("Insira um jogo com caracteres entre 1 e 40!");
			}
			
			if(! erros.isEmpty()) {
				model.addAttribute("erros", erros);
				return "jogos/form";
			}
			
			if(jogo.getId() == null) {
				jogoDao.persistir(jogo);
			}else {
				jogoDao.alterar(jogo);
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return "redirect:/app/jogo/";
		
	}
}
