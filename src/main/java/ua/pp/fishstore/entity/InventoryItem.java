package ua.pp.fishstore.entity;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "InventoryItem")
@NamedQuery(name = "InventoryItem.getAll", query = "SELECT ii FROM InventoryItem ii")
public class InventoryItem extends BasicEntity {

	@Column(name = "declaredIncomingNumber", columnDefinition = "INTEGER", nullable = false)
	private Integer declaredIncomingNumber;
	@Column(name = "realIncomingNumber", columnDefinition = "INTEGER  DEFAULT 0", nullable = false)
	private Integer realIncomingNumber;
	@Column(name = "currentNumber", columnDefinition = "INTEGER DEFAULT 0", nullable = false)
	private Integer currentNumber;
	@Column(name = "provisions", columnDefinition = "INTEGER DEFAULT 0", nullable = false)
	private Integer provisions;
	@Column(name = "COGS", nullable = false)
	private double COGS;
	@Column(name = "sellingStatus", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
	private Boolean sellingStatus;
	@Column(name = "price", nullable = false)
	private double price;
	@Column(name = "wroteOffNumber", columnDefinition = "INTEGER DEFAULT 0", nullable = false)
	private Integer wroteOffNumber;
	@Column(name = "toWriteOffStatus", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
	private Boolean toWriteOffStatus;

	@ManyToOne
	@JoinColumn(name = "idDelivery", nullable = false)
	private Delivery delivery;

	@ManyToOne
	@JoinColumn(name = "idInventoryType", nullable = false)
	private InventoryType inventoryType;

	@OneToMany(mappedBy = "inventoryItem", fetch = FetchType.LAZY)
	private Collection<OrderConsistsOf> orderConsistsOf;

	@Transient
	private int selectedNumber;
	@Transient
	private int totalSelectedNumber;
	@Transient
	private double cost;
	@Transient
	private double totalCost;
	@Transient
	private double weight;
	@Transient
	private double totalWeight;

	public InventoryItem() {
		inventoryType = new InventoryType();
		delivery = new Delivery();
		// realIncomingNumber = 0;
		// currentNumber = 0;
		// provisions = 0;
		// wroteOffNumber = 0;
		// sellingStatus = false;
		// toWriteOffStatus = false;
	}

	public InventoryItem(Integer declaredIncomingNumber,
			Integer realIncomingNumber, Integer currentNumber,
			Integer provisions, double cOGS, Boolean sellingStatus,
			double price, Integer wroteOffNumber, Boolean toWriteOffStatus) {
		this.declaredIncomingNumber = declaredIncomingNumber;
		this.realIncomingNumber = realIncomingNumber;
		this.currentNumber = currentNumber;
		this.provisions = provisions;
		this.COGS = cOGS;
		this.sellingStatus = sellingStatus;
		this.price = price;
		this.wroteOffNumber = wroteOffNumber;
		this.toWriteOffStatus = toWriteOffStatus;
	}

	public Integer getDeclaredIncomingNumber() {
		return declaredIncomingNumber;
	}

	public void setDeclaredIncomingNumber(Integer declaredIncomingNumber) {
		this.declaredIncomingNumber = declaredIncomingNumber;
	}

	public Integer getRealIncomingNumber() {
		return realIncomingNumber;
	}

	public void setRealIncomingNumber(Integer realIncomingNumber) {
		this.realIncomingNumber = realIncomingNumber;
	}

	public Integer getCurrentNumber() {
		return currentNumber - totalSelectedNumber - selectedNumber;
	}

	public void setCurrentNumber(Integer currentNumber) {
		this.currentNumber = currentNumber;
	}

	public Integer getProvisions() {
		return provisions;
	}

	public void setProvisions(Integer provisions) {
		this.provisions = provisions;
	}

	public double getCOGS() {
		return COGS;
	}

	public void setCOGS(double cOGS) {
		COGS = cOGS;
	}

	public Boolean getSellingStatus() {
		return sellingStatus;
	}

	public void setSellingStatus(Boolean sellingStatus) {
		this.sellingStatus = sellingStatus;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getWroteOffNumber() {
		return wroteOffNumber;
	}

	public void setWroteOffNumber(Integer wroteOffNumber) {
		this.wroteOffNumber = wroteOffNumber;
	}

	public Boolean getToWriteOffStatus() {
		return toWriteOffStatus;
	}

	public void setToWriteOffStatus(Boolean toWriteOffStatus) {
		this.toWriteOffStatus = toWriteOffStatus;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public Collection<OrderConsistsOf> getOrderConsistsOf() {
		return orderConsistsOf;
	}

	public void setOrderConsistsOf(Collection<OrderConsistsOf> orderConsistsOf) {
		this.orderConsistsOf = orderConsistsOf;
	}

	public int getSelectedNumber() {
		return selectedNumber;
	}

	public void setSelectedNumber(int selectedNumber) {
		this.selectedNumber = selectedNumber;
	}

	public double getCost() {
		cost = price * (selectedNumber * 100.0 / 100);
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getWeight() {
		weight = inventoryType.getWeight() * (selectedNumber * 100.0 / 100);
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getTotalSelectedNumber() {
		return totalSelectedNumber;
	}

	public void setTotalSelectedNumber(int totalSelectedNumber) {
		this.totalSelectedNumber = totalSelectedNumber;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	@Override
	public String toString() {
		return "InventoryItem [getId()=" + getId()
				+ ", getDeclaredIncomingNumber()="
				+ getDeclaredIncomingNumber() + ", getRealIncomingNumber()="
				+ getRealIncomingNumber() + ", getCurrentNumber()="
				+ getCurrentNumber() + ", getProvisions()=" + getProvisions()
				+ ", getCOGS()=" + getCOGS() + ", getSellingStatus()="
				+ getSellingStatus() + ", getPrice()=" + getPrice()
				+ ", getWroteOffNumber()=" + getWroteOffNumber()
				+ ", getToWriteOffStatus()=" + getToWriteOffStatus()
				// + ", getDelivery()=" + getDelivery().getDeliveryNumber()
				+ ", getInventoryType()=" + getInventoryType().getName() + "]";
	}

}