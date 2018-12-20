package br.com.tecnow.dao.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.tecnow.dao.DAO;
import br.com.tecnow.models.Categoria;
import br.com.tecnow.models.Jogo;
import br.com.tecnow.models.Usuario;
/**
 * Classe responsável pela conexão com a tabela jogo do banco de dados.
 * @author Camila
 *
 */
@Repository
public class JogoDao implements DAO<Jogo> {

	private FabricaDeConexoes fabricaDeConexoes = new FabricaDeConexoes();
	
	/**
	 * Busca um jogo através do id.
	 */
	@Override
	public Jogo buscar(Long id) {
		String sql = "SELECT id, nome, categoria FROM jogo WHERE id = ?";
		
		try {
			this.fabricaDeConexoes.abrir();
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			//Passa o id do usuário para filtrar a busca
			stmt.setLong(1, id);
			
			ResultSet resultados = stmt.executeQuery();
			Jogo jogo = null;
			
			if(resultados.next()) {
				jogo = new Jogo();
				jogo.setId(resultados.getLong("id"));
				jogo.setNome(resultados.getString("nome"));
				Categoria categoria = Categoria.valueOf(resultados.getString("categoria"));
				jogo.setCategoria(categoria);
			}
		resultados.close();
		return jogo;
		
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			this.fabricaDeConexoes.fechar();
		}
	}

	@Override
	public List<Jogo> buscarTodos() {
		return null;
	}
	
	/**
	 * Retorna todos os jogos cadastrados por um determinado usuário.
	 */
	public List<Jogo> buscarTodos(Usuario usuario) {
		String sql = "SELECT id, nome, categoria, data_cadastro FROM jogo WHERE id_usuario = ?";
		List<Jogo> listaJogos = new ArrayList<>(100);
		
		try {
			this.fabricaDeConexoes.abrir();
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setLong(1, usuario.getId());
			
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Jogo jogo = new Jogo();
				jogo.setId(results.getLong("id"));
				jogo.setNome(results.getString("nome"));
				Categoria categoria = Categoria.valueOf(results.getString("categoria"));
				jogo.setCategoria(categoria);
				jogo.setDataCadastro(results.getDate("data_cadastro"));
				
				listaJogos.add(jogo);
			}
			results.close();
			return listaJogos;
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			this.fabricaDeConexoes.fechar();
		}
	}

	/**
	 * Altera um jogo.
	 */
	@Override
	public void alterar(Jogo obj) {
		String sql = "UPDATE jogo SET nome = ?, categoria = ? WHERE id = ?";
		try {
			fabricaDeConexoes.abrir();
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getCategoria().categ);
			stmt.setLong(3, obj.getId());
			stmt.execute();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			fabricaDeConexoes.fechar();
		}
	}

	/**
	 * Exclui um jogo
	 */
	@Override
	public void deletar(Jogo obj) {
		String sql = "DELETE FROM jogo WHERE id = ?";
		
		try {
			fabricaDeConexoes.abrir();
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			
			stmt.setLong(1, obj.getId());
			stmt.execute();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			fabricaDeConexoes.fechar();
		}
		
	}

	/**
	 * Salva um novo jogo no banco de dados.
	 */
	@Override
	public void persistir(Jogo obj) {
		obj.setDataCadastro(new java.util.Date());
		String sql = "INSERT INTO jogo SET nome = ?, categoria = ?, data_cadastro = ?, id_usuario = ?";
		try {
			fabricaDeConexoes.abrir();
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getCategoria().categ);
			stmt.setDate(3, new Date(obj.getDataCadastro().getTime()));
			stmt.setLong(4, obj.getUsuario().getId());
			stmt.execute();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			fabricaDeConexoes.fechar();
		}
		
	}

	
	
}
