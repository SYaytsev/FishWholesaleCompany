package ua.pp.fishstore.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ua.pp.fishstore.entity.Customer;

@Repository
public class CustomerDao extends AbstractDao<Customer, Integer> {

	public Customer findByCRScode(String CRScode) throws SQLException {
		TypedQuery<Customer> query = em.createQuery(
				"SELECT c FROM Customer c WHERE c.CRScode = :inCRScode",
				Customer.class);
		query.setParameter("inCRScode", CRScode);
		return query.getSingleResult();
	}

	public Customer findByLoginPass(String login, String pass) throws SQLException {
		TypedQuery<Customer> query = em.createQuery(
				"SELECT c FROM Customer c WHERE c.login = :inLogin AND c.pass = :inPass",
				Customer.class);
		query.setParameter("inLogin", login);
		query.setParameter("inPass", pass);
		return query.getSingleResult();
	}
	
	public List<Customer> findByRegisterStatus(Boolean register) throws SQLException {
		TypedQuery<Customer> query = em.createQuery(
				"SELECT c FROM Customer c WHERE c.confirmRegistration = :isRegistered",
				Customer.class);
		query.setParameter("isRegistered", register);
		return query.getResultList();
	}

	public List<Customer> getAll() throws SQLException {
		TypedQuery<Customer> namedQuery = em.createNamedQuery(
				"Customer.getAll", Customer.class);
		return namedQuery.getResultList();
	}

	public List<Customer> getAllgetAllOrderByRegisterStatus()
			throws SQLException {
		TypedQuery<Customer> namedQuery = em.createNamedQuery(
				"Customer.getAllOrderByRegisterStatus", Customer.class);
		return namedQuery.getResultList();
	}

}