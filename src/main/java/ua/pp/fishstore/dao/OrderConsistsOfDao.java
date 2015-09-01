package ua.pp.fishstore.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ua.pp.fishstore.entity.OrderConsistsOf;

@Repository
public class OrderConsistsOfDao extends AbstractDao<OrderConsistsOf, Integer> {

	@Override
	public List<OrderConsistsOf> getAll() throws SQLException {
		TypedQuery<OrderConsistsOf> namedQuery = em.createNamedQuery(
				"OrderConsistsOf.getAll", OrderConsistsOf.class);
		return namedQuery.getResultList();
	}
}