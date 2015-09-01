package ua.pp.fishstore.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import ua.pp.fishstore.entity.GoodsOrder;
import ua.pp.fishstore.entity.InventoryItem;
import ua.pp.fishstore.entity.OrderConsistsOf;
import ua.pp.fishstore.other.OrderStatus;
import ua.pp.fishstore.service.InventoryService;
import ua.pp.fishstore.service.OrderService;

@Named
@Scope("session")
public class BuyGoodsController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger
			.getLogger(BuyGoodsController.class);

	@Inject
	private LoginController loginController;

	@Inject
	private InventoryService inventoryService;
	@Inject
	private OrderService orderService;

	private List<InventoryItem> allSellingInventoryItems;
	private List<InventoryItem> selectedInventoryItems;

	private GoodsOrder newGoodsOrder;

	@PostConstruct
	private void init() {
		updateSellingItems();
		updateSelectedInventoryItems();
		defaultGoodsOrder();
	}

	// Methods
	public void createNewOrder() throws SQLException {

		if (loginController.getCurrentCustomer() == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"You are not able to submit order!",
							"You should login or register"));
		} else {
			newGoodsOrder.setCustomer(loginController.getCurrentCustomer());

			newGoodsOrder.setOrderNumber(orderService.generateOrderNumber());

			newGoodsOrder.setOrderDate(new Date());

			newGoodsOrder.setOrderStatus(OrderStatus.RESERVED);

			// зарезервивовать товар

			newGoodsOrder.setShipmentDate(new Date());

			newGoodsOrder.setAmountMustBePrepaid(0);
			newGoodsOrder.setTotalAmount(0);
			newGoodsOrder.setTotalWeight(0);

			try {
				orderService.persistOrder(newGoodsOrder);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}

			for (InventoryItem item : selectedInventoryItems) {
				OrderConsistsOf orderConsistsOf = new OrderConsistsOf(item,
						newGoodsOrder, item.getTotalSelectedNumber());
				orderService.persistOrderedItem(orderConsistsOf);
			}
			deleteAllFromBasket();
		}
	}

	public void updateSellingItems() {
		try {
			allSellingInventoryItems = inventoryService
					.getAllSellingInventoryItems();
		} catch (NullPointerException | SQLException e) {
			allSellingInventoryItems = new ArrayList<InventoryItem>();
			log.error(e.getMessage(), e);
		}
	}

	public void updateSelectedInventoryItems() {
		selectedInventoryItems = new ArrayList<InventoryItem>();
	}

	public void defaultGoodsOrder() {
		newGoodsOrder = new GoodsOrder();
	}

	public void addToBasket(InventoryItem selectedItem) {
		selectedItem.setTotalSelectedNumber(selectedItem
				.getTotalSelectedNumber() + selectedItem.getSelectedNumber());
		selectedItem.setTotalWeight(selectedItem.getTotalWeight()
				+ selectedItem.getWeight());
		selectedItem.setTotalCost(selectedItem.getTotalCost()
				+ selectedItem.getCost());
		selectedItem.setSelectedNumber(0);
		selectedItem.setWeight(0);
		selectedItem.setCost(0);
		if (!isSelectedItem(selectedItem, selectedInventoryItems)) {
			selectedInventoryItems.add(selectedItem);
		}
	}

	private boolean isSelectedItem(InventoryItem selectedItem,
			List<InventoryItem> selectedInventoryItems2) {
		for (InventoryItem item : selectedInventoryItems2) {
			if (item.getInventoryType().getId() == selectedItem
					.getInventoryType().getId()) {
				return true;
			}
		}
		return false;
	}

	public boolean isBasketEmpty() {
		if (selectedInventoryItems.isEmpty())
			return false;
		else
			return true;
	}

	public void deleteFromBasket(InventoryItem selectedItem) {
		try {
			InventoryItem tmp = inventoryService.findInventoryItem(selectedItem
					.getId());
			allSellingInventoryItems.add(
					allSellingInventoryItems.indexOf(selectedItem), tmp);
			allSellingInventoryItems.remove(selectedItem);
		} catch (NullPointerException | SQLException e) {
			log.error(e.getMessage(), e);
		}
		selectedInventoryItems.remove(selectedItem);
	}

	public double calculateTotalCost() {
		double tmp = 0.0;
		for (InventoryItem item : selectedInventoryItems) {
			tmp += item.getTotalCost();
		}
		return tmp;
	}

	public double calculateTotalWeight() {
		double tmp = 0.0;
		for (InventoryItem item : selectedInventoryItems) {
			tmp += item.getTotalWeight();
		}
		return tmp;
	}

	public int calculateTotalQuantity() {
		int tmp = 0;
		for (InventoryItem item : selectedInventoryItems) {
			tmp += item.getTotalSelectedNumber();
		}
		return tmp;
	}

	public void deleteAllFromBasket() {
		updateSellingItems();
		updateSelectedInventoryItems();
	}

	// Getters and Setters
	public List<InventoryItem> getAllSellingInventoryItems() {
		return allSellingInventoryItems;
	}

	public void setAllSellingInventoryItems(
			List<InventoryItem> allSellingInventoryItems) {
		this.allSellingInventoryItems = allSellingInventoryItems;
	}

	public List<InventoryItem> getSelectedInventoryItems() {
		return selectedInventoryItems;
	}

	public void setSelectedInventoryItems(
			List<InventoryItem> selectedInventoryItems) {
		this.selectedInventoryItems = selectedInventoryItems;
	}

}
