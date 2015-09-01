package ua.pp.fishstore.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ua.pp.fishstore.other.OrderStatus;

@Entity
@Table(name = "GoodsOrder")
@NamedQueries({ @NamedQuery(name = "GoodsOrder.getAll", query = "SELECT go FROM GoodsOrder go") })
public class GoodsOrder extends BasicEntity {

	@ManyToOne
	@JoinColumn(name = "idCustomer", nullable = false)
	private Customer customer;

	@OneToMany(mappedBy = "goodsOrder", fetch = FetchType.LAZY)
	private Collection<Payment> payments;

	@OneToMany(mappedBy = "goodsOrder", fetch = FetchType.LAZY)
	private Collection<OrderConsistsOf> orderConsistsOf;

	@Column(name = "idOrderStatus", nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@Column(name = "orderNumber", nullable = false, length = 20)
	private String orderNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "orderDate", nullable = false)
	private Date orderDate;

	@Column(name = "totalAmount")
	private double totalAmount;
	@Column(name = "totalWeight")
	private double totalWeight;
	@Column(name = "amountMustBePrepaid")
	private double amountMustBePrepaid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "shipmentDate")
	private Date shipmentDate;

	public GoodsOrder() {
	}

	public GoodsOrder(Customer customer, OrderStatus idOrderStatus,
			String orderNumber, LocalDateTime orderDate, double totalAmount,
			double totalWeight, double amountMustBePrepaid,
			LocalDateTime shipmentDate) {
		this.customer = customer;
		this.orderStatus = idOrderStatus;
		this.orderNumber = orderNumber;
		this.orderDate = Timestamp.valueOf(orderDate);
		this.totalAmount = totalAmount;
		this.totalWeight = totalWeight;
		this.amountMustBePrepaid = amountMustBePrepaid;
		this.shipmentDate = Timestamp.valueOf(shipmentDate);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public double getAmountMustBePrepaid() {
		return amountMustBePrepaid;
	}

	public void setAmountMustBePrepaid(double amountMustBePrepaid) {
		this.amountMustBePrepaid = amountMustBePrepaid;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public Collection<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Collection<Payment> payments) {
		this.payments = payments;
	}

	public Collection<OrderConsistsOf> getOrderConsistsOf() {
		return orderConsistsOf;
	}

	public void setOrderConsistsOf(Collection<OrderConsistsOf> orderConsistsOf) {
		this.orderConsistsOf = orderConsistsOf;
	}

	@Override
	public String toString() {
		return "GoodsOrder [getId()=" + getId() + ", getCustomer()="
				+ getCustomer() + ", getOrderStatus()=" + getOrderStatus()
				+ ", getOrderNumber()=" + getOrderNumber()
				+ ", getOrderDate()=" + getOrderDate() + ", getTotalAmount()="
				+ getTotalAmount() + ", getTotalWeight()=" + getTotalWeight()
				+ ", getAmountMustBePrepaid()=" + getAmountMustBePrepaid()
				+ ", getShipmentDate()=" + getShipmentDate()
				+ ", getPayments()=" + getPayments()
				+ ", getOrderConsistsOf()=" + getOrderConsistsOf() + "]";
	}

}