package ua.pp.fishstore.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import ua.pp.fishstore.entity.GoodsOrder;
import ua.pp.fishstore.entity.Payment;
import ua.pp.fishstore.other.OrderStatus;
import ua.pp.fishstore.service.AccountantService;
import ua.pp.fishstore.service.OrderService;

@Named
@SessionScoped
public class OrderController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AccountantService accountantService;
	@Inject
	private OrderService orderService;

	private List<GoodsOrder> allGoodsOrders;

	private List<GoodsOrder> reservedGoodsOrders;
	private List<GoodsOrder> reservedPrepaidGoodsOrders;
	private List<GoodsOrder> reservedPrepaidReadyGoodsOrders;
	private List<GoodsOrder> shippedGoodsOrders;
	private List<GoodsOrder> fullyPaidGoodsOrders;

	private GoodsOrder selectedOrder;

	private Payment newPayment;
	private GoodsOrder orderToAddPayment;

	private Payment selectedPayment;

	@PostConstruct
	private void init() {
		defaultData();
		updateAllGoodsOrders();
		updateAll();
	}

	public void updateAll() {
		updateAllGoodsOrders();
		reservedGoodsOrders = updateGoodsOrdersWithStatus(OrderStatus.RESERVED);
		reservedPrepaidGoodsOrders = updateGoodsOrdersWithStatus(OrderStatus.RESERVED_PREPAID);
		reservedPrepaidReadyGoodsOrders = updateGoodsOrdersWithStatus(OrderStatus.RESERVED_PREPAID_READY);
		shippedGoodsOrders = updateGoodsOrdersWithStatus(OrderStatus.SHIPPED);
		fullyPaidGoodsOrders = updateGoodsOrdersWithStatus(OrderStatus.FULLY_PAID);
	}

	public void defaultData() {
		newPayment = new Payment();
		orderToAddPayment = new GoodsOrder();
		selectedPayment = new Payment();
	}

	public void updateReservedGoodsOrders() {
		reservedGoodsOrders = updateGoodsOrdersWithStatus(OrderStatus.RESERVED);
	}

	public void updateSelectedOrder() throws SQLException {
		selectedOrder = accountantService.find(selectedOrder.getId());
	}

	// General update method for all lists of GoodsOrder
	private void updateAllGoodsOrders() {
		try {
			allGoodsOrders = accountantService.getAllGoodsOrders();
		} catch (Exception e) {
			allGoodsOrders = new ArrayList<GoodsOrder>();
		}
	}

	private List<GoodsOrder> updateGoodsOrdersWithStatus(OrderStatus orderStatus) {
		try {
			return accountantService.getAllWithStatus(orderStatus);
		} catch (Exception e) {
			return new ArrayList<GoodsOrder>();
		}
	}

	public boolean findGoodsOrder() {
		String tmp = orderToAddPayment.getOrderNumber();
		try {
			orderToAddPayment = orderService.findOrderByName(tmp);
			return true;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Warning!",
							"Order # " + tmp + " does not exist"));
			return false;
		}
	}

	public void addNewPayment() {
		if (findGoodsOrder()) {
			newPayment.setGoodsOrder(orderToAddPayment);
			try {
				orderService.persistPayment(newPayment);
				defaultData();
			} catch (Exception e) {
			}
		}
	}

	public void addPayment() {
		try {
			newPayment.setGoodsOrder(selectedOrder);
			orderService.persistPayment(newPayment);
			updateAll();
			closeAddPaymentDialog();
		} catch (Exception e) {
		}

	}

	public void addPaymentDialog(GoodsOrder goodsOrder) {
		orderToAddPayment = goodsOrder;
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentWidth", 400);
		RequestContext.getCurrentInstance().openDialog("addPayment", options,
				null);
	}

	public void closeAddPaymentDialog() {
		defaultData();
		RequestContext.getCurrentInstance().closeDialog("addPayment");
	}

	public void showDetail(GoodsOrder goodsOrder) {
		try {
			selectedOrder = orderService.findOrderByName(goodsOrder
					.getOrderNumber());
		} catch (Exception e) {
		}
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentWidth", 400);
		RequestContext.getCurrentInstance().openDialog("orderPayments",
				options, null);
	}

	public void closeShowDetail() {
		defaultData();
		RequestContext.getCurrentInstance().closeDialog("orderPayments");
	}

	public void fillSelectedData(String orderNumber) {
		orderToAddPayment.setOrderNumber(orderNumber);
	}

	// Getters and Setters
	public Payment getNewPayment() {
		return newPayment;
	}

	public void setNewPayment(Payment newPayment) {
		this.newPayment = newPayment;
	}

	public GoodsOrder getSelectedOrder() {
		return selectedOrder;
	}

	public Payment getSelectedPayment() {
		return selectedPayment;
	}

	public void setSelectedPayment(Payment selectedPayment) {
		this.selectedPayment = selectedPayment;
	}

	public void setSelectedOrder(GoodsOrder selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public GoodsOrder getOderToAddPayment() {
		return orderToAddPayment;
	}

	public void setOderToAddPayment(GoodsOrder orderToAddPayment) {
		this.orderToAddPayment = orderToAddPayment;
	}

	public List<GoodsOrder> getAllGoodsOrders() {
		return allGoodsOrders;
	}

	public void setAllGoodsOrders(List<GoodsOrder> allGoodsOrders) {
		this.allGoodsOrders = allGoodsOrders;
	}

	public List<GoodsOrder> getReservedGoodsOrders() {
		return reservedGoodsOrders;
	}

	public void setReservedGoodsOrders(List<GoodsOrder> reservedGoodsOrders) {
		this.reservedGoodsOrders = reservedGoodsOrders;
	}

	public List<GoodsOrder> getReservedPrepaidGoodsOrders() {
		return reservedPrepaidGoodsOrders;
	}

	public void setReservedPrepaidGoodsOrders(
			List<GoodsOrder> reservedPrepaidGoodsOrders) {
		this.reservedPrepaidGoodsOrders = reservedPrepaidGoodsOrders;
	}

	public List<GoodsOrder> getReservedPrepaidReadyGoodsOrders() {
		return reservedPrepaidReadyGoodsOrders;
	}

	public void setReservedPrepaidReadyGoodsOrders(
			List<GoodsOrder> reservedPrepaidReadyGoodsOrders) {
		this.reservedPrepaidReadyGoodsOrders = reservedPrepaidReadyGoodsOrders;
	}

	public List<GoodsOrder> getShippedGoodsOrders() {
		return shippedGoodsOrders;
	}

	public void setShippedGoodsOrders(List<GoodsOrder> shippedGoodsOrders) {
		this.shippedGoodsOrders = shippedGoodsOrders;
	}

	public List<GoodsOrder> getFullyPaidGoodsOrders() {
		return fullyPaidGoodsOrders;
	}

	public void setFullyPaidGoodsOrders(List<GoodsOrder> fullyPaidGoodsOrders) {
		this.fullyPaidGoodsOrders = fullyPaidGoodsOrders;
	}

}
