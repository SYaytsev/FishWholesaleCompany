package ua.pp.fishstore.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ua.pp.fishstore.entity.Payment;

@Repository
public class PaymentDao extends AbstractDao<Payment, Integer> {

	@Override
	public List<Payment> getAll() throws SQLException {
		TypedQuery<Payment> namedQuery = em.createNamedQuery("Payment.getAll",
				Payment.class);
		return namedQuery.getResultList();
	}
}