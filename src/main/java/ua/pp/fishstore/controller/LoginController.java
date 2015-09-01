package ua.pp.fishstore.controller;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import ua.pp.fishstore.entity.Customer;
import ua.pp.fishstore.entity.SystemUser;
import ua.pp.fishstore.service.CustomerService;
import ua.pp.fishstore.service.SystemUserService;

@Named
@Scope("session")
public class LoginController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private SystemUserService systemUserService;
	@Inject
	private CustomerService customerService;

	private Customer currentCustomer;
	private SystemUser currentSystemUser;

	private String login;
	private String password;

	public String doLogin() {
		findUser();

		if (currentCustomer == null && currentSystemUser == null) {
			return "";
		} else if (currentCustomer != null) {
			return "buyGoods?faces-redirect=true";
		} else {
			switch (currentSystemUser.getRole()) {
			case GENERAL_MANAGER:
				return "customerManager?faces-redirect=true";
			case ACCOUNTANT:
				return "paymentManager?faces-redirect=true";
			case COLD_STORE_MANAGER:
				return "storeManager?faces-redirect=true";
			case SECURITY_OFFICER:
				return "staffManager?faces-redirect=true";
			}
		}

		return "index?faces-redirect=true";
	}

	public void findUser() {
		if (currentCustomer == null) {
			try {
				currentCustomer = customerService.findByLoginPass(
						login.toLowerCase(), password);
			} catch (Exception e) {
				currentCustomer = null;
			}
		}
		if (currentSystemUser == null) {

			try {
				currentSystemUser = systemUserService.findByLoginPass(
						login.toLowerCase(), password);
			} catch (Exception e) {
				currentSystemUser = null;
			}
		}
	}

	public void defaultData() {
		currentCustomer = null;
		currentSystemUser = null;
		password = "";
		login = "";
	}

	public boolean isSomebodyLoged() {
		if (currentCustomer == null && currentSystemUser == null) {
			return true;
		} else {
			return false;
		}
	}

	public String doLogOut() {
		defaultData();
		return "index.xhml";
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
