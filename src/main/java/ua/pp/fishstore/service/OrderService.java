package ua.pp.fishstore.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.pp.fishstore.dao.GoodsOrderDao;
import ua.pp.fishstore.dao.OrderConsistsOfDao;
import ua.pp.fishstore.dao.PaymentDao;
import ua.pp.fishstore.entity.GoodsOrder;
import ua.pp.fishstore.entity.OrderConsistsOf;
import ua.pp.fishstore.entity.Payment;
import ua.pp.fishstore.other.OrderStatus;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class OrderService {

	@Autowired
	private GoodsOrderDao goodsOrderDao;
	@Autowired
	private OrderConsistsOfDao orderConsistsOfDao;

	@Autowired
	private PaymentDao paymentDao;

	// Getters and Setters
	public GoodsOrderDao getGoodsOrderDao() {
		return goodsOrderDao;
	}

	public OrderConsistsOfDao getOrderConsistsOfDao() {
		return orderConsistsOfDao;
	}

	// Methods
	public String generateOrderNumber() {
		String tmp = "";
		LocalDateTime currentDate = LocalDateTime.now();
		tmp += currentDate.getSecond();
		tmp += currentDate.getMinute();
		tmp += currentDate.getHour();
		tmp += currentDate.getDayOfMonth();
		tmp += currentDate.getMonthValue();
		tmp += currentDate.getYear();
		return tmp;
	}

	public void persistOrder(GoodsOrder goodsOrder) throws SQLException {
		goodsOrderDao.persist(goodsOrder);
	}

	public GoodsOrder findOrder(Integer primaryKey) throws SQLException {
		return goodsOrderDao.find(primaryKey);
	}

	public GoodsOrder findOrderByName(String goodsOrderNumber)
			throws SQLException {
		return goodsOrderDao.findByNumber(goodsOrderNumber);
	}

	public List<GoodsOrder> getAllWithStatus(OrderStatus orderStatus)
			throws SQLException {
		return goodsOrderDao.getAllByStatus(orderStatus);
	}

	public List<GoodsOrder> getAllGoodsOrders() throws SQLException {
		return goodsOrderDao.getAll();
	}

	public void persistOrderedItem(OrderConsistsOf orderConsistsOf)
			throws SQLException {
		orderConsistsOfDao.persist(orderConsistsOf);
	}

	public void persistPayment(Payment payment) throws SQLException {
		paymentDao.persist(payment);
	}
}
