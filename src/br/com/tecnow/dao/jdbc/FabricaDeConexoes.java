package br.com.tecnow.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe controla a conexão com o banco de dados.
 * @author Camila Diniz
 *
 */
public class FabricaDeConexoes {

	Connection conexao;
	
	/**
	 * Abre conexão com o banco de dados
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void abrir() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		final String USUARIO = "root";
		final String SENHA = "root132";
		String sql = "jdbc:mysql://localhost:3306/tecnow?serverTimezone=UTC";
		conexao = DriverManager.getConnection(sql, USUARIO, SENHA);
				
	}
	
	/**
	 * Fecha a conexão com o banco de dados
	 */
	public void fechar() {
		if(conexao != null) {
			try {
				conexao.close();
			}catch (Exception e) {
				
			}
		}
	}
	
	public Connection getConexao() {
		return conexao;
	}
	
}
