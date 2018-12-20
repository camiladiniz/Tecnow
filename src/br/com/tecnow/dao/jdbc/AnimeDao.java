package br.com.tecnow.dao.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.tecnow.dao.DAO;
import br.com.tecnow.models.Anime;
import br.com.tecnow.models.Usuario;
/**
 * Classe responsável pela conexão com a tabela anime do banco de dados.
 * @author Camila
 *
 */
@Repository
public class AnimeDao implements DAO<Anime>{

	private FabricaDeConexoes fabricaDeConexoes = new FabricaDeConexoes();
	
	/**
	 * Busca um anime através do id.
	 */
	@Override
	public Anime buscar(Long id) {
		String sql = "SELECT id, nome, data_cadastro FROM anime WHERE id = ?";
		
		try {
			this.fabricaDeConexoes.abrir();
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			stmt.setLong(1, id);
			
			ResultSet resultados = stmt.executeQuery();
			Anime anime = null;
			
			if(resultados.next()) {
				anime = new Anime();
				anime.setId(resultados.getLong("id"));
				anime.setNome(resultados.getString("nome"));
				anime.setDataCadastro(resultados.getDate("data_cadastro"));
			}
			resultados.close();
			return anime;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			this.fabricaDeConexoes.fechar();
		}
		
	}
	
	@Override
	public List<Anime> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Retorna todos os animes cadastrados por um determinado usuário.
	 * @return
	 */
	public List<Anime> buscarTodos(Usuario usuario){
		String sql = "SELECT id, nome, data_cadastro FROM anime WHERE id_usuario = ?";
		List<Anime> listaAnimes = new ArrayList<>(100);
		
		try {
			this.fabricaDeConexoes.abrir();
			
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			
			//Pega o id do usuário para realizar a busca personalizada
			stmt.setLong(1, usuario.getId());
			
			ResultSet resultados = stmt.executeQuery();
			while(resultados.next()) {
				Anime anime = new Anime();
				anime.setId(resultados.getLong("id"));
				anime.setNome(resultados.getString("nome"));
				anime.setDataCadastro(resultados.getDate("data_cadastro"));
				
				listaAnimes.add(anime);
			}
			resultados.close();
			return listaAnimes;
			
			
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			this.fabricaDeConexoes.fechar();
		}
	}

	/**
	 * Realiza a alteração do anime
	 */
	@Override
	public void alterar(Anime obj) {
		String sql = "UPDATE anime SET nome = ? WHERE id = ?";
		
		try {
			fabricaDeConexoes.abrir();
			
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			
			stmt.setString(1, obj.getNome());
			stmt.setLong(2, obj.getId());
			stmt.execute();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			fabricaDeConexoes.fechar();
		}
	}

	/**
	 * Exclui um anime
	 */
	@Override
	public void deletar(Anime obj) {
		String sql = "DELETE FROM anime WHERE id = ?";
		
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
	 * Insere um novo anime no banco.
	 */
	@Override
	public void persistir(Anime obj) {
		
		obj.setDataCadastro(new java.util.Date());
		String sql = "INSERT INTO anime SET nome = ?, data_cadastro = ?, id_usuario = ?";
		
		try {
			fabricaDeConexoes.abrir();
			System.out.println(obj.getUsuario().getId());
			PreparedStatement stmt = fabricaDeConexoes.getConexao().prepareStatement(sql);
			
			stmt.setString(1, obj.getNome());
			stmt.setDate(2, new Date(obj.getDataCadastro().getTime()));
			stmt.setLong(3, obj.getUsuario().getId());
			stmt.execute();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			fabricaDeConexoes.fechar();
		}
	}
}