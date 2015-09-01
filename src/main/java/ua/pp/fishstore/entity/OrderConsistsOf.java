package ua.pp.fishstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "OrderConsistsOf")
@NamedQuery(name = "OrderConsistsOf.getAll", query = "SELECT oco FROM OrderConsistsOf oco")
public class OrderConsistsOf extends BasicEntity {

	@JoinColumn(name = "idInventoryItem")
	private InventoryItem inventoryItem;

	@JoinColumn(name = "idGoodsOrder", nullable = false)
	private GoodsOrder goodsOrder;

	@Column(name = "numberToBeShipped", columnDefinition = "INTEGER", nullable = false)
	private Integer numberToBeShipped;

	public OrderConsistsOf() {
	}

	public OrderConsistsOf(InventoryItem inventoryItem, GoodsOrder goodsOrder,
			Integer numberToBeShipped) {
		this.inventoryItem = inventoryItem;
		this.goodsOrder = goodsOrder;
		this.numberToBeShipped = numberToBeShipped;
	}

	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}

	public void setInventoryItem(InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
	}

	public GoodsOrder getGoodsOrder() {
		return goodsOrder;
	}

	public void setGoodsOrder(GoodsOrder goodsOrder) {
		this.goodsOrder = goodsOrder;
	}

	public Integer getNumberToBeShipped() {
		return numberToBeShipped;
	}

	public void setNumberToBeShipped(Integer numberToBeShipped) {
		this.numberToBeShipped = numberToBeShipped;
	}

	@Override
	public String toString() {
		return "OrderConsistsOf [getId()=" + getId() + ", getInventoryItem()="
				+ getInventoryItem() + ", getGoodsOrder()=" + getGoodsOrder()
				+ ", getNumberToBeShipped()=" + getNumberToBeShipped() + "]";
	}
}