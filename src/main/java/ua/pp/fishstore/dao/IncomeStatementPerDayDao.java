package ua.pp.fishstore.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ua.pp.fishstore.entity.IncomeStatementPerDay;

@Repository
public class IncomeStatementPerDayDao extends
		AbstractDao<IncomeStatementPerDay, Integer> {

	public List<IncomeStatementPerDay> getAll() throws SQLException {
		TypedQuery<IncomeStatementPerDay> namedQuery = em.createNamedQuery(
				"IncomeStatementPerDay.getAll", IncomeStatementPerDay.class);
		return namedQuery.getResultList();
	}

	public List<IncomeStatementPerDay> getAllForPeriod(LocalDateTime startDate,
			LocalDateTime endDate) throws SQLException {

		TypedQuery<IncomeStatementPerDay> query = em.createQuery(
				"SELECT ispd FROM IncomeStatementPerDay ispd WHERE "
						+ "ispd.byDate >= :startDate "
						+ "AND ispd.byDate <= :endDate",
				IncomeStatementPerDay.class);

		query.setParameter("startDate", Timestamp.valueOf(startDate));
		query.setParameter("endDate", Timestamp.valueOf(endDate));

		return query.getResultList();
	}
}
