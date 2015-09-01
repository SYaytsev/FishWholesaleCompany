package ua.pp.fishstore.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao<T, PK extends Serializable> implements
		GenericDao<T, PK> {

	@PersistenceContext
	protected EntityManager em;

	protected final Class<T> daoClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		daoClass = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public void persist(T entity) throws SQLException {
		em.persist(entity);
	}

	public T find(PK primaryKey) throws SQLException {
		return em.find(daoClass, primaryKey);
	}

	public void merge(T entity) throws SQLException {
		em.merge(entity);
	}

	public void remove(T entity) throws SQLException {
		em.remove(em.getReference(daoClass, entity));
	}

	//
	public void print() {
		System.out.println(daoClass.getSimpleName());
	}

//	@SuppressWarnings("unchecked")
//	public List<T> newGetAll() {
//		return em.createQuery("from " + daoClass.getSimpleName())
//				.getResultList();
//	}
}
