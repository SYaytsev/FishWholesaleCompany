package ua.pp.fishstore.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ua.pp.fishstore.entity.Employee;

@Repository
public class EmployeeDao extends AbstractDao<Employee, Integer> {

	public List<Employee> getAll() throws SQLException {
		TypedQuery<Employee> namedQuery = em.createNamedQuery(
				"Employee.getAll", Employee.class);
		return namedQuery.getResultList();
	}

	public List<Employee> getAllActive() throws SQLException {
		TypedQuery<Employee> namedQuery = em.createNamedQuery(
				"Employee.getAllActive", Employee.class);
		return namedQuery.getResultList();
	}
}
