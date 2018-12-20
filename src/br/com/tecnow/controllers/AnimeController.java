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

import br.com.tecnow.dao.jdbc.AnimeDao;
import br.com.tecnow.models.Anime;
import br.com.tecnow.models.Usuario;
import br.com.tecnow.utils.SessionUtils;
/**
 * Classe é responsável pelo mapeamento da funções do anime.
 * @author Camila Diniz
 */
@Controller
@RequestMapping("/app/anime")
public class AnimeController {
	
	@Autowired
	private AnimeDao animeDao;
	
	@Autowired
	private SessionUtils sessionUtils;
	
	/**
	 * Abre o formulário de cadastro de anime
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping(value = {"/cadastro"})
	public String abrirForm(@RequestParam(name = "id", required = false) Long id, Model model) {
		
		//Se o id for informado é uma alteração. Logo, buscaremos os valores no banco de dados.
		if(id != null) {
			Anime anime = animeDao.buscar(id);
			model.addAttribute("anime", anime);
		}
		return "animes/form";
	}
	
	/**
	 * Abre a lista de animes e suas funcionalidades (atualizar/excluir)
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(value= {"/", ""})
	public String listarAnimes(Model model, HttpSession session) {
		
		Usuario logado = (Usuario) session.getAttribute("usuarioLogado");
		
		model.addAttribute("animes", animeDao.buscarTodos(logado));
		
		return "animes/animesCadastrados";
	}
	
	/**
	 * Realiza a exclusão do anime.
	 * @param id
	 * @return - lista de animes atualizada
	 */
	@GetMapping(value = {"/excluir"})
	public String excluir(@RequestParam(name="id", required = true) Long id) {
		
		Anime anime = new Anime();
		anime.setId(id);
		animeDao.deletar(anime);
		
		return "redirect:/app/anime/";
	}
	
	/**
	 * Realiza validações e se os campos estiverem inseridos de forma correta salva ou altera o anime.
	 * @param anime
	 * @param model
	 * @return - lista de animes
	 */
	@PostMapping("/salvar")
	public String salvar(Anime anime, Model model){
		
		anime.setUsuario(sessionUtils.getUsuarioLogado());
		List<String> erros = new ArrayList<>(6);
		
		if(anime.getNome() == null) {
			erros.add("Campo nome é obrigatório!");
		}else if(anime.getNome().length() < 1 || anime.getNome().length() > 40) {
			erros.add("Insira um anime com caracteres entre 1 e 40!");
		}
		
		if(! erros.isEmpty()) {
			model.addAttribute("erros", erros);
			return "animes/form";
		}
		
		try {
			anime.setUsuario(sessionUtils.getUsuarioLogado());
			
			if(anime.getId() == null) {
				animeDao.persistir(anime);
			}else {
				animeDao.alterar(anime);
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "redirect:/app/anime/";
	}
}
