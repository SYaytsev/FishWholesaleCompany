package ua.pp.fishstore.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.pp.fishstore.dao.CustomerDao;
import ua.pp.fishstore.dao.PaymentConditionDao;
import ua.pp.fishstore.entity.Customer;
import ua.pp.fishstore.entity.PaymentCondition;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private PaymentConditionDao paymentConditionDao;

	// Getters and Setters
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public PaymentConditionDao getPaymentConditionDao() {
		return paymentConditionDao;
	}

	// Methods
	public void persistCustomer(Customer customer) throws SQLException {
		customerDao.persist(customer);
	}
	
	public void merge(Customer customer) throws SQLException {
		customerDao.merge(customer);
	}

	public Customer findByCRScode(String CRScode) throws SQLException {
		return customerDao.findByCRScode(CRScode);
	}

	public Customer findByLoginPass(String login, String pass)
			throws SQLException {
		return customerDao.findByLoginPass(login, pass);
	}

	public void persistPaymentCondition(PaymentCondition paymentCondition)
			throws SQLException {
		paymentConditionDao.persist(paymentCondition);
	}

	public List<Customer> findByRegisterStatus(Boolean register) throws SQLException {
		return customerDao.findByRegisterStatus(register);
	}

}
