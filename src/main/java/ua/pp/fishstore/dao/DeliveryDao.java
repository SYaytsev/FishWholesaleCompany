package ua.pp.fishstore.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ua.pp.fishstore.entity.Delivery;

@Repository
public class DeliveryDao extends AbstractDao<Delivery, Integer> {

	@Override
	public List<Delivery> getAll() throws SQLException {
		TypedQuery<Delivery> namedQuery = em.createNamedQuery(
				"Delivery.getAll", Delivery.class);
		return namedQuery.getResultList();
	}
}