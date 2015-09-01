package ua.pp.fishstore.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ua.pp.fishstore.entity.InventoryType;

@Repository
public class InventoryTypeDao extends AbstractDao<InventoryType, Integer> {

	@Override
	public List<InventoryType> getAll() throws SQLException {
		TypedQuery<InventoryType> namedQuery = em.createNamedQuery(
				"InventoryType.getAll", InventoryType.class);
		return namedQuery.getResultList();
	}
}