package ua.pp.fishstore.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PaymentCondition")
@NamedQueries({ @NamedQuery(name = "PaymentCondition.getAll", query = "SELECT pc FROM PaymentCondition pc") })
public class PaymentCondition extends BasicEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startDate", nullable = false)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiryDate", nullable = false)
	private Date expiryDate;

	@Column(name = "prepaymentValue", columnDefinition = "DOUBLE DEFAULT 100.0", nullable = false)
	private double prepaymentValue;
	@Column(name = "fullPaymentWithIn", columnDefinition = "SMALLINT DEFAULT 0", nullable = false)
	private Short fullPaymentWithIn;

	@ManyToOne
	@JoinColumn(name = "idCustomer", nullable = false)
	private Customer customer;

	public PaymentCondition() {
	}

	public PaymentCondition(LocalDateTime startDate, LocalDateTime expiryDate,
			double prepaymentValue, Short fullPaymentWithIn, Customer customer) {
		this.startDate = Timestamp.valueOf(startDate);
		this.expiryDate = Timestamp.valueOf(expiryDate);
		this.prepaymentValue = prepaymentValue;
		this.fullPaymentWithIn = fullPaymentWithIn;
		this.customer = customer;
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

	public double getPrepaymentValue() {
		return prepaymentValue;
	}

	public void setPrepaymentValue(double prepaymentValue) {
		this.prepaymentValue = prepaymentValue;
	}

	public Short getFullPaymentWithIn() {
		return fullPaymentWithIn;
	}

	public void setFullPaymentWithIn(Short fullPaymentWithIn) {
		this.fullPaymentWithIn = fullPaymentWithIn;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "PaymentCondition [getId()=" + getId() + ", getStartDate()="
				+ getStartDate() + ", getExpiryDate()=" + getExpiryDate()
				+ ", getPrepaymentValue()=" + getPrepaymentValue()
				+ ", getFullPaymentWithIn()=" + getFullPaymentWithIn()
				+ ", getCustomer()=" + getCustomer() + "]";
	}

}
