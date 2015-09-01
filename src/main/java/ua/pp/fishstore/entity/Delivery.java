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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Delivery")
@NamedQuery(name = "Delivery.getAll", query = "SELECT d FROM Delivery d")
public class Delivery extends BasicEntity {

	@Column(name = "deliveryNumber", nullable = false, length = 20)
	private String deliveryNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deliveryDate", nullable = false)
	private Date deliveryDate;

	@Column(name = "supplier", nullable = false, length = 20)
	private String supplier;

//	@Enumerated(EnumType.STRING)
	@Column(name = "countryFrom", nullable = false, length = 20)
	private String countryFrom;

	@OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
	private Collection<InventoryItem> inventoryItems;

	public Delivery() {
	}

	public Delivery(String deliveryNumber, LocalDateTime deliveryDate,
			String supplier, String countryFrom,
			Collection<InventoryItem> inventoryItems) {
		this.deliveryNumber = deliveryNumber;
		this.deliveryDate = Timestamp.valueOf(deliveryDate);
		this.supplier = supplier;
		this.countryFrom = countryFrom;
		this.inventoryItems = inventoryItems;
	}

	public String getDeliveryNumber() {
		return deliveryNumber;
	}

	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getCountryFrom() {
		return countryFrom;
	}

	public void setCountryFrom(String countryFrom) {
		this.countryFrom = countryFrom;
	}

	public Collection<InventoryItem> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(Collection<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	@Override
	public String toString() {
		return "Delivery [getDeliveryNumber()=" + getDeliveryNumber()
				+ ", getDeliveryDate()=" + getDeliveryDate()
				+ ", getSupplier()=" + getSupplier() + ", getCountryFrom()="
				+ getCountryFrom() + ", getInventoryItems()="
				+ getInventoryItems() + "]";
	}

}