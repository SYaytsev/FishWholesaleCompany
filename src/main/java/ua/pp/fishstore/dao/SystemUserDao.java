package ua.pp.fishstore.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ua.pp.fishstore.entity.SystemUser;

@Repository
public class SystemUserDao extends AbstractDao<SystemUser, Integer> {

	public List<SystemUser> getAll() throws SQLException {
		TypedQuery<SystemUser> namedQuery = em.createNamedQuery(
				"SystemUser.getAll", SystemUser.class);
		return namedQuery.getResultList();
	}

	public List<SystemUser> getAllActive() throws SQLException {
		TypedQuery<SystemUser> query = em
				.createQuery(
						"SELECT su FROM SystemUser su, Employee e WHERE su.id = e.id "
								+ "AND :currDate BETWEEN su.startDate AND su.expiryDate "

								+ "AND e.activeStatus=TRUE", SystemUser.class);

		// new Date(Calendar.getInstance().getTimeInMillis())).getTime()
		query.setParameter("currDate", Timestamp.valueOf(LocalDateTime.now()));
		return query.getResultList();
	}

	public SystemUser findByLoginPass(String login, String pass)
			throws SQLException {
		TypedQuery<SystemUser> query = em
				.createQuery(
						"SELECT su FROM SystemUser su, Employee e WHERE su.id = e.id "
								+ "AND su.login = :inLogin "
								+ "AND su.pass = :inPass "
								+ "AND :currDate BETWEEN su.startDate AND su.expiryDate "
								+ "AND e.activeStatus=TRUE", SystemUser.class);
		query.setParameter("inLogin", login);
		query.setParameter("inPass", pass);
		query.setParameter("currDate", Timestamp.valueOf(LocalDateTime.now()));
		return query.getSingleResult();
	}

	public List<SystemUser> findActiveRole(Integer employeeId)
			throws SQLException {
		TypedQuery<SystemUser> query = em
				.createQuery(
						"SELECT su FROM SystemUser su WHERE su.id = :e.id "
								+ "AND :currDate BETWEEN su.startDate AND su.expiryDate ",
						SystemUser.class);
		query.setParameter("inEmployeeId", employeeId);
		query.setParameter("currDate", Timestamp.valueOf(LocalDateTime.now()));
		return query.getResultList();
	}

	// public List<SystemUser> getAllActive() throws SQLException {
	// TypedQuery<SystemUser> query = em
	// .createQuery(
	// "SELECT su FROM SystemUser su, Employee e WHERE su.id = e.id "
	// + "AND :currDate BETWEEN su.startDate AND su.expiryDate "
	//
	// + "AND e.activeStatus=TRUE", SystemUser.class);
	//
	// // new Date(Calendar.getInstance().getTimeInMillis())).getTime()
	// query.setParameter("currDate", Timestamp.valueOf(LocalDateTime.now()));
	// return query.getResultList();
	// }
}
