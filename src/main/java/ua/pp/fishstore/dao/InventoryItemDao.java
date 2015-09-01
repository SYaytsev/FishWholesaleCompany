package ua.pp.fishstore.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ua.pp.fishstore.entity.InventoryItem;

@Repository
public class InventoryItemDao extends AbstractDao<InventoryItem, Integer> {

	@Override
	public List<InventoryItem> getAll() throws SQLException {
		TypedQuery<InventoryItem> namedQuery = em.createNamedQuery(
				"InventoryItem.getAll", InventoryItem.class);
		return namedQuery.getResultList();
	}

	public List<InventoryItem> getAllSelling() throws SQLException {

		TypedQuery<InventoryItem> query = em
				.createQuery(
						"SELECT ii FROM InventoryItem ii WHERE ii.sellingStatus = :status AND ii.currentNumber > 0",
						InventoryItem.class);
		query.setParameter("status", true);

		return query.getResultList();
	}

	public List<InventoryItem> getAllToWriteOff() throws SQLException {

		TypedQuery<InventoryItem> query = em
				.createQuery(
						"SELECT ii FROM InventoryItem ii WHERE ii.toWriteOffStatus = :status",
						InventoryItem.class);
		query.setParameter("status", true);

		return query.getResultList();
	}

}