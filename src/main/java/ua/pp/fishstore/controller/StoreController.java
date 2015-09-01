package ua.pp.fishstore.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import ua.pp.fishstore.entity.Delivery;
import ua.pp.fishstore.entity.InventoryItem;
import ua.pp.fishstore.entity.InventoryType;
import ua.pp.fishstore.other.Country;
import ua.pp.fishstore.service.InventoryService;

@Named
@Scope("session")
public class StoreController implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(StoreController.class);

	@Inject
	private InventoryService inventoryService;

	private List<Delivery> allDeliveries;

	private Delivery selectedDelivery;

	private Delivery newDelivery;
	private InventoryType selectedInventoryType;
	private InventoryItem newInventoryItem;

	private Integer inventoryTypeId;

	private List<InventoryType> inventoryTypes;
	private List<InventoryItem> inventoryItems;

	public StoreController() {
	}

	@PostConstruct
	private void init() {
		this.updateNewDelivery();
		this.defaultNewInventoryItem();
		this.updateInventoryTypes();
		selectedDelivery = new Delivery();
		this.updateAllDeliveries();
		this.updateInventoryItems();
	}

	// Methods

	public void updateNewDelivery() {
		newDelivery = new Delivery();
	}

	public void updateInventoryTypes() {
		try {
			inventoryTypes = inventoryService.getAllInventoryTypes();
		} catch (Exception e) {
		}
	}

	public void updateInventoryItems() {
		inventoryItems = new ArrayList<InventoryItem>();
	}

	public void defaultNewInventoryItem() {
		newInventoryItem = new InventoryItem();
	}

	private void updateAllDeliveries() {
		try {
			allDeliveries = inventoryService.getAllDeliveries();
		} catch (NullPointerException npE) {

		} catch (SQLException sqlE) {
		}
	}

	public void viewAddDeliveryDialog() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentWidth", 1000);
		options.put("contentHeight", 500);
		RequestContext.getCurrentInstance().openDialog("delivery", options,
				null);
	}

	public void closeRegisterDialog() {
		RequestContext.getCurrentInstance().closeDialog("delivery");
	}

	public void createNewDelivery() throws SQLException {
		try {
			// inventoryService.persistDelivery(newDelivery);
			// RequestContext.getCurrentInstance().reset("delivery");
		} catch (Exception e) {

		}

	}

	public void addItemToDelivery() throws SQLException {
		InventoryType selectedInventoryType = inventoryService
				.findInventoryType(inventoryTypeId);
		newInventoryItem.setInventoryType(selectedInventoryType);
		inventoryItems.add(newInventoryItem);
		defaultNewInventoryItem();
		inventoryTypeId = 0;
	}

	public void saveDelivery() throws SQLException {

		if (inventoryItems == null) {
//			FacesContext.getCurrentInstance().addMessage("controlButtons",
//					new FacesMessage(null, "dddd"));
		} else {

			inventoryService.persistDelivery(newDelivery);
			inventoryService.persistInventoryItems(inventoryItems, newDelivery);
			deleteDelivery();
			updateAllDeliveries();
			closeRegisterDialog();
		}
	}

	public void deleteDelivery() {
		updateNewDelivery();
		defaultNewInventoryItem();
		inventoryTypeId = 0;
		log.error("Новый заказ удален");
	}

	// public void onRowSelect(SelectEvent event) {
	// ((Delivery) event.getObject()).getId());
	// }

	// Getters and Setters

	public Country[] getCountries() {
		return Country.values();
	}

	public List<Delivery> getAllDeliveries() {
		return allDeliveries;
	}

	public Delivery getSelectedDelivery() {
		return selectedDelivery;
	}

	public void setSelectedDelivery(Delivery selectedDelivery) {
		this.selectedDelivery = selectedDelivery;
	}

	public void setAllDeliveries(List<Delivery> allDeliveries) {
		this.allDeliveries = allDeliveries;
	}

	public Delivery getNewDelivery() {
		return newDelivery;
	}

	public void setNewDelivery(Delivery newDelivery) {
		this.newDelivery = newDelivery;
	}

	public List<InventoryType> getInventoryTypes() {
		return inventoryTypes;
	}

	public void setInventoryTypes(List<InventoryType> inventoryTypes) {
		this.inventoryTypes = inventoryTypes;
	}

	public List<InventoryItem> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(List<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public InventoryType getSelectedInventoryType() {
		return selectedInventoryType;
	}

	public void setSelectedInventoryType(InventoryType selectedInventoryType) {
		this.selectedInventoryType = selectedInventoryType;
	}

	public InventoryItem getNewInventoryItem() {
		return newInventoryItem;
	}

	public void setNewInventoryItem(InventoryItem newInventoryItem) {
		this.newInventoryItem = newInventoryItem;
	}

	public Integer getInventoryTypeId() {
		return inventoryTypeId;
	}

	public void setInventoryTypeId(Integer inventoryTypeId) {
		this.inventoryTypeId = inventoryTypeId;
	}

}
