package ua.pp.fishstore.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import ua.pp.fishstore.entity.Constant;

@Repository
public class ConstantDao extends AbstractDao<Constant, Integer> {

	@Override
	public void persist(Constant entity) throws SQLException {
	}

	@Override
	public Constant find(Integer primaryKey) throws SQLException {
		return this.getConstants();
	}

	@Override
	public void merge(Constant entity) throws SQLException {
	}

	@Override
	public void remove(Constant entity) throws SQLException {
	}

	public List<Constant> getAll() throws SQLException {
		return null;
	}

	public Constant getConstants() throws SQLException {
		TypedQuery<Constant> namedQuery = em.createNamedQuery(
				"Constant.getConstant", Constant.class);
		return namedQuery.getSingleResult();
	}

}
