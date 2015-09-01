package ua.pp.fishstore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ua.pp.fishstore.other.SystemUserRole;

@Entity
@Table(name = "SystemUser")
@NamedQuery(name = "SystemUser.getAll", query = "SELECT su FROM SystemUser su, Employee e WHERE su.id=e.id")
public class SystemUser extends BasicEntity {

	@JoinColumn(name = "idEmployee", nullable = false)
	private Employee employee;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private SystemUserRole role;

	@Column(name = "login", nullable = false, length = 20)
	private String login;

	@Column(name = "pass", nullable = false, length = 20)
	private String pass;

	@Transient
	private boolean loggedIn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startDate", nullable = false)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiryDate", nullable = false)
	private Date expiryDate;

	public SystemUser() {
	}

	public SystemUser(Employee employee, SystemUserRole role, Date startDate,
			Date expiryDate) {
		super();
		this.employee = employee;
		this.role = role;
		this.startDate = startDate;
		this.expiryDate = expiryDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public SystemUserRole getRole() {
		return role;
	}

	public void setRole(SystemUserRole role) {
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "SystemUser [getId()=" + getId() + ", getEmployee()="
				+ getEmployee() + ", getRole()=" + getRole() + ", getLogin()="
				+ getLogin() + ", getPass()=" + getPass() + ", isLoggedIn()="
				+ isLoggedIn() + ", getStartDate()=" + getStartDate()
				+ ", getExpiryDate()=" + getExpiryDate() + "]";
	}

}