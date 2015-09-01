package ua.pp.fishstore.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Customer")
@NamedQueries({
		@NamedQuery(name = "Customer.getAll", query = "SELECT c FROM Customer c"),
		@NamedQuery(name = "Customer.getAllOrderByRegisterStatus", query = "SELECT c FROM Customer c ORDER BY c.confirmRegistration") })
public class Customer extends BasicEntity {

	@Column(name = "name", nullable = false, length = 50)
	private String name;
	@Column(name = "CRScode", columnDefinition = "VARCHAR(8) DEFAULT '11111111'", nullable = false, length = 8)
	private String CRScode;
	@Column(name = "address", nullable = false, length = 70)
	private String address;
	@Column(name = "contactPerson", nullable = false, length = 40)
	private String contactPerson;
	@Column(name = "phoneNumber", nullable = false)
	private String phoneNumber;
	// @mail
	@Column(name = "login", nullable = false, length = 30)
	private String login;
	@Column(name = "pass", nullable = false, length = 20)
	private String pass;

	@Transient
	private boolean loggedIn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registerDate", nullable = false)
	private Date registerDate;
	@Column(name = "confirmRegistration", columnDefinition = "BOOLEAN", nullable = false)
	private Boolean confirmRegistration;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private Collection<GoodsOrder> goodsOrders;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private Collection<PaymentCondition> paymentConditions;

	public Customer() {
	};

	public Customer(int id, String name, String cRScode, String address,
			String contactPerson, String phoneNumber, String login,
			String pass, boolean loggedIn, LocalDateTime registerDate,
			Boolean confirmRegistration, double prepaymentValue,
			Short fullPaymentWithIn) {
		this.name = name;
		this.CRScode = cRScode;
		this.address = address;
		this.contactPerson = contactPerson;
		this.phoneNumber = phoneNumber;
		this.login = login;
		this.pass = pass;
		this.loggedIn = loggedIn;
		this.registerDate = Timestamp.valueOf(registerDate);
		this.confirmRegistration = confirmRegistration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCRScode() {
		return CRScode;
	}

	public void setCRScode(String CRScode) {
		this.CRScode = CRScode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Boolean getConfirmRegistration() {
		return confirmRegistration;
	}

	public void setConfirmRegistration(Boolean confirmRegistration) {
		this.confirmRegistration = confirmRegistration;
	}

	public Collection<GoodsOrder> getGoodsOrders() {
		return goodsOrders;
	}

	public void setGoodsOrders(Collection<GoodsOrder> goodsOrders) {
		this.goodsOrders = goodsOrders;
	}

	public Collection<PaymentCondition> getPaymentConditions() {
		return paymentConditions;
	}

	public void setPaymentConditions(
			Collection<PaymentCondition> paymentConditions) {
		this.paymentConditions = paymentConditions;
	}

	@Override
	public String toString() {
		return "Customer [getId()=" + getId() + ", getName()=" + getName()
				+ ", getCRScode()=" + getCRScode() + ", getAddress()="
				+ getAddress() + ", getContactPerson()=" + getContactPerson()
				+ ", getPhoneNumber()=" + getPhoneNumber() + ", getLogin()="
				+ getLogin() + ", getPass()=" + getPass() + ", isLoggedIn()="
				+ isLoggedIn() + ", getRegisterDate()=" + getRegisterDate()
				+ ", getConfirmRegistration()=" + getConfirmRegistration()
				+ ", getGoodsOrders()=" + getGoodsOrders()
				+ ", getPaymentConditions()=" + getPaymentConditions() + "]";
	}

}
