package br.com.tecnow.dao;

import java.util.List;
/**
 * A interface possui as funcionalidades básicas dos DAO
 * @author Camila
 *
 * @param <T>
 */
public interface DAO<T> {

	public T buscar(Long id);
	public List<T> buscarTodos();
	public void alterar(T obj);
	public void deletar(T obj);
	public void persistir(T obj);
	
}
