package ua.pp.fishstore.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Payment")
@NamedQuery(name = "Payment.getAll", query = "SELECT p FROM Payment p")
public class Payment extends BasicEntity {

	@ManyToOne
	@JoinColumn(name = "idGoodsOrder", nullable = false)
	private GoodsOrder goodsOrder;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "paymentDate", nullable = false)
	private Date paymentDate;
	@Column(name = "amount", nullable = false)
	private double amount;

	public Payment() {
	}

	public Payment(int id, GoodsOrder goodsOrder, LocalDateTime paymentDate,
			double amount) {
		this.id = id;
		this.goodsOrder = goodsOrder;
		this.paymentDate = Timestamp.valueOf(paymentDate);
		this.amount = amount;
	}

	public GoodsOrder getGoodsOrder() {
		return goodsOrder;
	}

	public void setGoodsOrder(GoodsOrder goodsOrder) {
		this.goodsOrder = goodsOrder;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Payment [getId()=" + getId() + ", getGoodsOrder()="
				+ getGoodsOrder() + ", getPaymentDate()=" + getPaymentDate()
				+ ", getAmount()=" + getAmount() + "]";
	}

}