package ua.pp.fishstore.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.pp.fishstore.dao.DeliveryDao;
import ua.pp.fishstore.entity.Delivery;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DeliveryService {

	@Autowired
	private DeliveryDao deliveryDao;

	// Getters and Setters
	public DeliveryDao getDeliveryDao() {
		return deliveryDao;
	}

	// Methods
	public List<Delivery> getAllDeliveries() throws SQLException {
		return deliveryDao.getAll();
	}

}
