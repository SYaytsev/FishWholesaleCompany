package ua.pp.fishstore.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import ua.pp.fishstore.entity.Customer;
import ua.pp.fishstore.entity.PaymentCondition;
import ua.pp.fishstore.service.CustomerService;

@Named
@Scope("request")
public class CustomerRegisterController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CustomerService customerService;

	@Inject
	private LoginController loginController;

	private Customer customer;
//	private Long customerCRScode;

	@PostConstruct
	private void init() {
		setDefaultCustomer();
	}

	private void setDefaultCustomer() {
		customer = new Customer();
	}

	public void viewRegisterForm() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentWidth", 350);

		RequestContext.getCurrentInstance().openDialog("register", options,
				null);
	}

	public void createNewCustomer() throws SQLException {
		if (isCustomerRegistered(customer.getCRScode())) {
		} else {
			createCustomer();
			setDefaultCustomer();

			closeRegisterDialog();
		}
	}

	private void createCustomer() {
		try {
			customer.setRegisterDate(new Date());
			customer.setConfirmRegistration(false);
			customerService.persistCustomer(customer);

			loginController.setCurrentCustomer(customer);

			PaymentCondition paymentCondition = getDefaultPaymentCondition();
			paymentCondition.setCustomer(customer);
			customerService.persistPaymentCondition(paymentCondition);
		} catch (SQLException sqlE) {
			
		}
	}

	private boolean isCustomerRegistered(String CRScode) {
		try {
			if (customerService.findByCRScode(CRScode) != null) {
				return true;
			}
		} catch (NullPointerException npE) {
			return false;
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	private PaymentCondition getDefaultPaymentCondition() {
		PaymentCondition paymentCondition = new PaymentCondition();

		Date startDate = new Date(Calendar.getInstance().getTimeInMillis());
		Instant instant = (LocalDateTime.now().plusYears(20)).atZone(
				ZoneId.systemDefault()).toInstant();
		Date expiryDate = Date.from(instant);

		paymentCondition.setStartDate(startDate);
		paymentCondition.setExpiryDate(expiryDate);
		paymentCondition.setPrepaymentValue(100.00);
		paymentCondition.setFullPaymentWithIn((short) 3);
		return paymentCondition;
	}

	public void closeRegisterDialog() {
		RequestContext.getCurrentInstance().closeDialog("register");
	}

	// Getters and Setters
	public CustomerService getCustomerService() {
		return customerService;
	}
//
//	public long getCustomerCRScode() {
//		return customerCRScode;
//	}
//
//	public void setCustomerCRScode(long customerCRScode) {
//		this.customerCRScode = customerCRScode;
//	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
