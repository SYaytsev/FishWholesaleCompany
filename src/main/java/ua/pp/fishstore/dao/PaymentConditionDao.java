package ua.pp.fishstore.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ua.pp.fishstore.entity.PaymentCondition;

@Repository
public class PaymentConditionDao extends AbstractDao<PaymentCondition, Integer> {

	@Override
	public List<PaymentCondition> getAll() throws SQLException {
		TypedQuery<PaymentCondition> namedQuery = em.createNamedQuery(
				"PaymentCondition.getAll", PaymentCondition.class);
		return namedQuery.getResultList();
	}
}