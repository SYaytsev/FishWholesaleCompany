package ua.pp.fishstore.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.pp.fishstore.dao.DeliveryDao;
import ua.pp.fishstore.dao.GoodsOrderDao;
import ua.pp.fishstore.dao.InventoryItemDao;
import ua.pp.fishstore.dao.InventoryTypeDao;
import ua.pp.fishstore.entity.Delivery;
import ua.pp.fishstore.entity.InventoryItem;
import ua.pp.fishstore.entity.InventoryType;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class InventoryService {

	@Autowired
	private InventoryItemDao inventoryItemDao;

	@Autowired
	private DeliveryDao deliveryDao;

	@Autowired
	private InventoryTypeDao inventoryTypeDao;

	// Getters and Setters

	public InventoryItemDao getInventoryItemDao() {
		return inventoryItemDao;
	}

	public DeliveryDao getDeliveryDao() {
		return deliveryDao;
	}

	public InventoryTypeDao getInventoryTypeDao() {
		return inventoryTypeDao;
	}


	// Methods

	public void persistDelivery(Delivery delivery) throws SQLException {
		deliveryDao.persist(delivery);
	}

	public List<Delivery> getAllDeliveries() throws SQLException {
		return deliveryDao.getAll();
	}

	public InventoryType findInventoryType(Integer primaryKey)
			throws SQLException {
		return inventoryTypeDao.find(primaryKey);
	}

	public List<InventoryType> getAllInventoryTypes() throws SQLException {
		return inventoryTypeDao.getAll();
	}

	public InventoryItem findInventoryItem(Integer primaryKey)
			throws SQLException {
		return inventoryItemDao.find(primaryKey);
	}

	public List<InventoryItem> getAllSellingInventoryItems()
			throws SQLException {
		return inventoryItemDao.getAllSelling();
	}

	public void persistInventoryItems(List<InventoryItem> inventoryItems,
			Delivery delivery) throws SQLException {
		for (InventoryItem item : inventoryItems) {
			if (item != null) {
				item.setDelivery(delivery);
				inventoryItemDao.persist(item);
			}
		}
	}


}
