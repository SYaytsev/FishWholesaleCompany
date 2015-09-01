package ua.pp.fishstore.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ua.pp.fishstore.entity.GoodsOrder;
import ua.pp.fishstore.other.OrderStatus;

@Repository
public class GoodsOrderDao extends AbstractDao<GoodsOrder, Integer> {

	@Override
	public List<GoodsOrder> getAll() throws SQLException {
		TypedQuery<GoodsOrder> namedQuery = em.createNamedQuery(
				"GoodsOrder.getAll", GoodsOrder.class);
		return namedQuery.getResultList();
	}

	public GoodsOrder findByNumber(String goodsOrderNumber) {
		TypedQuery<GoodsOrder> query = em
				.createQuery(
						"SELECT g FROM GoodsOrder AS g WHERE g.orderNumber = :number",
						GoodsOrder.class);
		query.setParameter("number", goodsOrderNumber);
		return query.getSingleResult();
	}

	// TODO
	public List<GoodsOrder> getAllByStatus(OrderStatus orderStatus)
			throws SQLException {

		TypedQuery<GoodsOrder> query = em
				.createQuery(
						"SELECT g FROM GoodsOrder AS g WHERE g.idOrderStatus = :status",
						GoodsOrder.class);
		query.setParameter("status", orderStatus);

		return query.getResultList();
	}

}