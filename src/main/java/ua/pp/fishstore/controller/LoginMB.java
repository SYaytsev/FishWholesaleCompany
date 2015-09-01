package ua.pp.fishstore.controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.springframework.context.annotation.Scope;

import ua.pp.fishstore.entity.Customer;
import ua.pp.fishstore.entity.SystemUser;
import ua.pp.fishstore.other.SystemUserRole;
import ua.pp.fishstore.service.CustomerService;
import ua.pp.fishstore.service.SystemUserService;

@Named
@Scope("session")
public class LoginMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private SystemUserService systemUserService;

	@Inject
	private CustomerService customerService;

	public void setUsrService(SystemUserService systemUserService) {
		this.systemUserService = systemUserService;
	}

	private Customer currentCustomer;
	private SystemUser currentSystemUser;

	private String login;
	private String password;

	public LoginMB() {
	}

	// TODO
	public String doLogin() throws SQLException {

		if (currentCustomer == null) {
			try {
				currentCustomer = customerService.findByLoginPass(login,
						password);
			} catch (NoResultException e) {
				currentCustomer = null;
			}
		}

		if (currentSystemUser == null) {

			try {
				currentSystemUser = systemUserService.findByLoginPass(login,
						password);
			} catch (NoResultException e) {
				currentSystemUser = null;
			}
		}

		if (currentCustomer == null && currentSystemUser == null) {
			return "";
		} else if (currentCustomer != null) {
			return "booking";
		} else {
			switch (currentSystemUser.getRole()) {
			case GENERAL_MANAGER:
				return "customerManager?faces-redirect=true";
			case ACCOUNTANT:
				return "accountant?faces-redirect=true";
			case COLD_STORE_MANAGER:
				return "goodsManager?faces-redirect=true";
			}
			return "security";
		}
	}

	public boolean isSomebodyLoged() {
		if (currentCustomer == null && currentSystemUser == null) {
			return true;
		} else {
			return false;
		}
	}

	public String doLoginOut() {
		currentCustomer = null;
		currentSystemUser = null;
		password = "";
		login = "";
		return "index";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer(Customer currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

	public SystemUser getCurrentSystemUser() {
		return currentSystemUser;
	}

	public void setCurrentSystemUser(SystemUser currentSystemUser) {
		this.currentSystemUser = currentSystemUser;
	}

}
