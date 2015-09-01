package ua.pp.fishstore.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import ua.pp.fishstore.entity.Customer;
import ua.pp.fishstore.entity.PaymentCondition;
import ua.pp.fishstore.service.CustomerService;

@Named
@SessionScoped
public class CustomerController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CustomerService customerService;

	private List<Customer> approvedCustomers;
	private List<Customer> newCustomers;

	private Customer selectedCustomer;
	private PaymentCondition paymentCondition;

	@PostConstruct
	private void init() {
		this.updateApprovedCustomers();
		this.updateNewCustomers();
		this.selectedCustomer = new Customer();
	}

	// Methods
	public void updateApprovedCustomers() {
		try {
			approvedCustomers = customerService.findByRegisterStatus(true);
		} catch (NullPointerException npE) {
		} catch (SQLException sqlE) {
		}
	}

	public void updateNewCustomers() {
		try {
			newCustomers = customerService.findByRegisterStatus(false);
		} catch (NullPointerException npE) {
		} catch (SQLException sqlE) {
		}
	}

	public void showCurrentPaymentCondition() {
		// PaymentCondition tmpPaymentCondition = new PaymentCondition();
		Date currentDate = new Date();

		for (PaymentCondition item : selectedCustomer.getPaymentConditions()) {
			if ((item.getStartDate().compareTo(currentDate)) < 0
					&& (item.getExpiryDate().compareTo(currentDate)) > 0) {
				paymentCondition = item;
			}
		}

	}

	public void approveNewCustomer(Customer customer) {
		customer.setConfirmRegistration(true);
		try {
			customerService.merge(customer);
		} catch (Exception e) {

		}
		this.updateApprovedCustomers();
		this.updateNewCustomers();
	}

	public void updateCustomer(RowEditEvent event) {
		Customer сustomer = (Customer) event.getObject();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			customerService.merge(сustomer);
		} catch (SQLException sqlE) {
			facesContext.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
							"Try add User some time later, because of "
									+ sqlE.getMessage()));
		}
	}

	// Getters and Setters
	public List<Customer> getApprovedCustomers() {
		return approvedCustomers;
	}

	public void setApprovedCustomers(List<Customer> approvedCustomers) {
		this.approvedCustomers = approvedCustomers;
	}

	public List<Customer> getNewCustomers() {
		return newCustomers;
	}

	public void setNewCustomers(List<Customer> newCustomers) {
		this.newCustomers = newCustomers;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	public PaymentCondition getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentCondition(PaymentCondition paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

}
