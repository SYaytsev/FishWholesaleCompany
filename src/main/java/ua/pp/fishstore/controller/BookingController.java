package ua.pp.fishstore.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import ua.pp.fishstore.entity.GoodsOrder;
import ua.pp.fishstore.entity.InventoryItem;

@Named
@SessionScoped
public class BookingController implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<InventoryItem> allSellingInventoryItems;

	private List<InventoryItem> selectedInventoryItems;
	private InventoryItem selectedInventoryItem;

	@PostConstruct
	private void init() {

	}

	// Methods

	public void updateSellingItems() {
		
		
	}

	// Getters and Setters


}
