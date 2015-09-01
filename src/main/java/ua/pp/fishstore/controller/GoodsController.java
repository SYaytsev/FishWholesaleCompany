package ua.pp.fishstore.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import ua.pp.fishstore.entity.Delivery;
import ua.pp.fishstore.service.DeliveryService;

@Named
@SessionScoped
public class GoodsController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private DeliveryService deliveryService;

	private List<Delivery> allDeliveries;
	private Delivery selectedDelivery;

	public GoodsController() {
	}

	@PostConstruct
	private void init() {
		selectedDelivery = new Delivery();
		this.updateAllDeliveries();
	}

	// Methods
	private void updateAllDeliveries() {
		try {
			allDeliveries = deliveryService.getAllDeliveries();
		} catch (NullPointerException npE) {

		} catch (SQLException sqlE) {
		}
	}

	public void showAddParcelDialog() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentWidth", 500);

		RequestContext.getCurrentInstance().openDialog("register", options,
				null);
	}

//	public void onRowSelect(SelectEvent event) {
//		((Delivery) event.getObject()).getId());
//	}

	// Getters and Setters

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

}
