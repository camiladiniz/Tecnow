package br.com.tecnow.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.tecnow.dao.jdbc.UsuarioDao;
import br.com.tecnow.models.Usuario;
import br.com.tecnow.utils.SessionUtils;
/**
 * Classe faz o interm�dio entre as classes model, o dao e as views relacionadas ao usu�rio.
 * @author Camila Diniz
 *
 */
@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private SessionUtils sessionUtils;
	
	/**
	 * M�todo abre o formul�rio de cadastro de usu�rios
	 * @return - p�gina de cadastro
	 */
	@GetMapping("/usuario/novo")
	public String abreForm() {
		return "usuarios/cadastro";
	}
	
	/**
	 * M�todo abre o formul�rio de edi��o das informa��es do usu�rio
	 * @return - p�gina de editar cadastro
	 */
	@GetMapping("app/usuario/alterar")
	public String abreFormAlteracao(Model model) {
		Usuario user = usuarioDao.buscar(sessionUtils.getUsuarioLogado().getId());
		model.addAttribute("usuario", user);
		return "usuarios/cadastro";
	}
	
	/**
	 * M�todo abre o formul�rio de cadastrar uma nova senha
	 * @return - p�gina de cadastrar nova senha
	 */	
	@GetMapping("app/alterar/senha")
	public String abreNovaSenhaForm (Model model) {
		Usuario logado = usuarioDao.buscar(sessionUtils.getUsuarioLogado().getId());
		model.addAttribute("usuario", logado);
		return "usuarios/formNovaSenha";
	}
	
	/**
	 * Realiza troca de senha se a senha atual for digitada corretamente
	 * @param usuario
	 * @param model
	 * @return
	 */
	@PostMapping("/app/usuario/senha/nova")
	public String novaSenha(Usuario usuario, Model model) {
		
		try {
			List<String> erros = new ArrayList<>(6);
			
			if(usuario.getSenha() == null) {
				erros.add("A senha n�o pode ser nula!");
			}else if(usuario.getNovaSenha().length() < 2 || usuario.getNovaSenha().length() > 20) {
				erros.add("A nova senha deve conter entre 2 e 20 caracteres!");
			}
						
			Usuario usuarioCadastrado = usuarioDao.buscar(sessionUtils.getUsuarioLogado().getId());
			usuario.hashearSenha();
			if(usuario.getSenha().equals(usuarioCadastrado.getSenha())) {
				//Se a senha atual foi digitada corretamente
				usuario.setSenha(usuario.getNovaSenha());
				usuario.hashearSenha();
				usuarioDao.alterarSenha(usuario);
			}else {
				//Se a senha foi digitada de forma incorreta
				erros.add("Senha atual inv�lida!");
			}
			
			if(! erros.isEmpty()) {
				model.addAttribute("erros", erros);
				return "usuarios/formNovaSenha";
			}
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return "redirect:/app";
	}
	
	/**
	 * Se os campos estiverem preenchidos corretamente realiza a inser��o dos dados no banco de
	 * @param usuario
	 * @param model
	 * @return
	 */
	@PostMapping("/usuario/salvar")
	public String salvar(Usuario usuario, Model model) {
		
		try {
			List<String> erros = new ArrayList<>(6);
		
			if(usuario.getId() == null) {
					
				if(usuario.getSenha() == null) {
					erros.add("Digite uma senha");
				}else if(usuario.getSenha().length() < 2 || usuario.getSenha().length() > 20) {
					erros.add("A senha deve conter entre 2 e 20 caracteres!");
				}
					
				if(usuario.getNome() == null ) {
					erros.add("O campo nome � obrigat�rio!");
				}else if(usuario.getNome().length() < 2 || usuario.getNome().length() > 60) {
					erros.add("O campo nome deve conter entre 2 e 60 caracteres!");
				}
					
				Date data = new Date();
				if(usuario.getDataNascimento() == null) {
					erros.add("O campo Data de Nascimento � obrigat�rio!");
				}else if(usuario.getDataNascimento().getTime() > data.getTime()) {
					erros.add("Insira uma data v�lida!");
				}
						
				if(usuario.getSexo() == null) {
					erros.add("Selecione um sexo!");
				}
				
				if(usuario.getEmail().isEmpty()) {
					erros.add("O campo e-mail � obrigat�rio");
				}else if(usuario.getEmail().length() < 1 || usuario.getEmail().length() > 120) {
					erros.add("O campo email deve estar entre 1 e 120 caracteres");
				}else if(! usuarioDao.validarEmail(usuario.getEmail())) {
					erros.add("Este email j� foi cadastrado, insira um e-mail v�lido!");
				}
						
				if(! erros.isEmpty()) {
					model.addAttribute("erros", erros);
					return "usuarios/cadastro";
				}
						
				usuario.hashearSenha();
				usuarioDao.persistir(usuario);
				return "usuarios/login";
				
			}else {
				
				if(usuario.getNome() == null ) {
					erros.add("O campo nome � obrigat�rio!");
				}else if(usuario.getNome().length() < 2 || usuario.getNome().length() > 60) {
					erros.add("O campo nome deve conter entre 2 e 60 caracteres!");
				}
				
				Date data = new Date();
				if(usuario.getDataNascimento() == null) {
					erros.add("O campo Data de Nascimento � obrigat�rio!");
				}else if(usuario.getDataNascimento().getTime() > data.getTime()) {
					erros.add("Insira uma data v�lida!");
				}
				
				if(! erros.isEmpty()) {
					model.addAttribute("erros", erros);
					return "usuarios/cadastro";
				}
				usuarioDao.alterar(usuario);
				return "redirect:/app/";
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * Abre a p�gina de login
	 * @return
	 */
	@GetMapping("/entrar")
	public String abreLogin() {
		return "usuarios/login";
	}
	
	/**
	 * Abre a p�gina inicial do usu�rio logado
	 * @return
	 */
	@GetMapping("/app")
	public String indexUsuarioLogado() {
		return "usuarios/usuarioLogado";
	}
	
	/**
	 * Remove o usu�rio da sess�o
	 * @param session
	 * @return
	 */
	@GetMapping("/sair")
	public String logout(HttpSession session) {
		sessionUtils.removerUsuariologado();
		return "index";
	}
	
	/**
	 * Realiza a valida��o dos campos de login e a autentica��o do usu�rio 
	 * @param usuario
	 * @param model
	 * @return
	 */
	@PostMapping("/autenticar")
	public String autenticar(Usuario usuario, Model model) {
		
		List<String> erros = new ArrayList<>(6);
		
		//valida��es
		if(usuario.getEmail() == null) {
			erros.add("O campo e-mail � obrigat�rio");
		}else if(usuario.getEmail().length() < 1 || usuario.getEmail().length() > 120) {
			erros.add("O campo email deve estar entre 1 e 120 caracteres");
		}
		
		if(usuario.getSenha() == null) {
			erros.add("O campo nome � obrigat�rio");
		}else if(usuario.getSenha().length() < 1 || usuario.getSenha().length() > 30) {
			erros.add("O campo senha deve estar entre 1 e 30 caracteres");
		}
		
		//Caso algum erro tenha ocorrido reabre a p�gina de login com os respectivos erros
		if(! erros.isEmpty()) {
			model.addAttribute("erros", erros);
			return "usuarios/login";
		}
		
		usuario.hashearSenha();
		
		Usuario usuarioAutenticado = usuarioDao.autenticar(usuario);
		
		if(usuarioAutenticado == null) {
			erros.add("E-mail ou senha inv�lidos!");
			model.addAttribute("erros", erros);
			//Usu�rio inv�lido
			return "usuarios/login";
		}
		
		sessionUtils.setUsuarioLogado(usuarioAutenticado);
		return "redirect:app/";
	}
	
	
}
