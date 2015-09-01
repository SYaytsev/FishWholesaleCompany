package ua.pp.fishstore.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {

	public void persist(T entity) throws SQLException;

	public T find(PK primaryKey) throws SQLException;

	public void merge(T entity) throws SQLException;

	public void remove(T entity) throws SQLException;

	public List<T> getAll() throws SQLException;
}