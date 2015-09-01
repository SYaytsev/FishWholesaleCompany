package ua.pp.fishstore.entity;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "InventoryType")
@NamedQuery(name = "InventoryType.getAll", query = "SELECT it FROM InventoryType it")
public class InventoryType extends BasicEntity {

	@Column(name = "name", nullable = false, length = 30)
	private String name;

	@Column(name = "weight", nullable = false)
	private double weight;

	@Column(name = "description", nullable = false, length = 1000)
	private String description;

	@OneToMany(mappedBy = "inventoryType", fetch = FetchType.LAZY)
	private Collection<InventoryItem> inventoryItems;

	public InventoryType() {
	}

	public InventoryType(String name, double weight, String description) {
		this.name = name;
		this.weight = weight;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<InventoryItem> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(Collection<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	@Override
	public String toString() {
		return "InventoryType [getId()=" + getId() + ", getName()=" + getName()
				+ ", getWeight()=" + getWeight() + ", getDescription()="
				+ getDescription() + "]";
	}

}