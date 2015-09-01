package ua.pp.fishstore.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.pp.fishstore.dao.GoodsOrderDao;
import ua.pp.fishstore.entity.GoodsOrder;
import ua.pp.fishstore.other.OrderStatus;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AccountantService {

	@Autowired
	private GoodsOrderDao goodsOrderDao;


	// Getters and Setters
	public GoodsOrderDao getGoodsOrderDao() {
		return goodsOrderDao;
	}

	// Methods

	public GoodsOrder find(Integer primaryKey) throws SQLException {
		return goodsOrderDao.find(primaryKey);
	}

	public List<GoodsOrder> getAllWithStatus(OrderStatus orderStatus)
			throws SQLException {
		return goodsOrderDao.getAllByStatus(orderStatus);
	}

	public List<GoodsOrder> getAllGoodsOrders() throws SQLException {
		return goodsOrderDao.getAll();
	}
	
	

}
