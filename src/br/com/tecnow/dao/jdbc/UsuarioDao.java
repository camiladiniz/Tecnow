package br.com.tecnow.dao.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.tecnow.dao.DAO;
import br.com.tecnow.models.Sexo;
import br.com.tecnow.models.Usuario;

@Repository
/**
 * Classe responsável pela conexão com o banco de dados (tabela usuario)
 * @author Camila Diniz
 *
 */
public class UsuarioDao implements DAO<Usuario>{
	
	private FabricaDeConexoes fabricaDeConexoes = new FabricaDeConexoes();

	/**
	 * Realiza a busca dos dados do usuário através do id
	 */
	@Override
	public Usuario buscar(Long id) {
		String sql = "SELECT id, nome, data_nascimento, sexo, email, senha FROM usuario WHERE id = ?";
		
		try {
			this.fabricaDeConexoes.abrir();
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setLong(1, id);
			
			ResultSet resultados = stmt.executeQuery();
			Usuario usuario = null;
			
			if(resultados.next()) {
				usuario = new Usuario();
				usuario.setId(resultados.getLong("id"));
				usuario.setNome(resultados.getString("nome"));
				usuario.setDataNascimento(resultados.getDate("data_nascimento"));
				Sexo sexo = Sexo.valueOf(resultados.getString("sexo"));
				usuario.setSexo(sexo);
				usuario.setEmail(resultados.getString("email"));
				usuario.setSenha(resultados.getString("senha"));
			}
			resultados.close();
			return usuario;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			this.fabricaDeConexoes.fechar();
		}
	}

	@Override
	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Altera o nome e a data de nascimento do usuário através do id
	 */
	@Override
	public void alterar(Usuario obj) {
		String sql = "UPDATE usuario SET nome = ?, data_nascimento = ? WHERE id = ?";
		try {
			fabricaDeConexoes.abrir();
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setDate(2, new Date(obj.getDataNascimento().getTime()));
			stmt.setLong(3, obj.getId());
			stmt.execute();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			fabricaDeConexoes.fechar();
		}
	}
	
	@Override
	public void deletar(Usuario obj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Insere um novo usuário no banco de dados.
	 */
	@Override
	public void persistir(Usuario obj) {
		String sql = "INSERT INTO usuario SET nome = ?, data_nascimento = ?, sexo = ?, email = ?, senha = ?";
		
		try {
			fabricaDeConexoes.abrir();
			
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			
			stmt.setString(1, obj.getNome());
			stmt.setDate(2, new Date(obj.getDataNascimento().getTime()));
			stmt.setString(3, obj.getSexo().genero);
			stmt.setString(4, obj.getEmail());
			stmt.setString(5, obj.getSenha());
			stmt.execute();
			
			fabricaDeConexoes.fechar();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			
		}
	}
	
	/**
	 * Faz a autenticação do login (email e senha) cadastrado pelo usuário
	 * @param usuario
	 * @return
	 */
	public Usuario autenticar(Usuario usuario) {
		
		String sql = "SELECT id, nome, data_nascimento, sexo, email, senha FROM usuario WHERE email = ? AND senha = ?";
		
		try {
			fabricaDeConexoes.abrir();
			
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			
			ResultSet resultados = stmt.executeQuery();
			Usuario usuarioAutenticado = null;
			if(resultados.next()) {
				usuarioAutenticado = new Usuario();
				usuarioAutenticado.setId(resultados.getLong("id"));
				usuarioAutenticado.setNome(resultados.getString("nome"));
				usuarioAutenticado.setDataNascimento(resultados.getDate("data_nascimento"));
				Sexo sexo = Sexo.valueOf(resultados.getString("sexo"));
				usuarioAutenticado.setSexo(sexo);
				
				usuarioAutenticado.setEmail(resultados.getString("email"));
				usuarioAutenticado.setSenha(resultados.getString("senha"));
			}
			resultados.close();
			return usuarioAutenticado;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			fabricaDeConexoes.fechar();
		}
	}
	
	/**
	 * Altera a senha do usuário
	 * @param usuario
	 */
	public void alterarSenha(Usuario usuario){
		
		String sql = "UPDATE usuario SET senha = ? WHERE id = ?";
		try {
			fabricaDeConexoes.abrir();
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setString(1, usuario.getSenha());
			stmt.setLong(2, usuario.getId());
			stmt.execute();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			fabricaDeConexoes.fechar();
		}
	
		
	}
	
	/**
	 * Busca se o email passado já existe no banco
	 * @param email
	 * @return
	 */
	public boolean validarEmail(String email) {
		
		String sqlBuscaEmail = "SELECT email FROM usuario WHERE email = ?";
		try {
			fabricaDeConexoes.abrir();
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sqlBuscaEmail);
			
			stmt.setString(1, email);
			System.out.println(email);
			ResultSet resultados = stmt.executeQuery();
			Usuario usuario = null;
			if(resultados.next()) {
				usuario = new Usuario();
				usuario.setEmail(resultados.getString("email"));
				if(usuario.getEmail().equals(email)) {
					return false;
				}
			}
			
			return true;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			fabricaDeConexoes.fechar();
		}
		
	}
	
}
