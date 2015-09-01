package ua.pp.fishstore.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.pp.fishstore.dao.EmployeeDao;
import ua.pp.fishstore.entity.Employee;

@Service
public class EmployeeService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private EmployeeDao employeeDao;

	@Transactional(readOnly = true)
	public Employee read(Integer primaryKey) throws SQLException {
		return employeeDao.find(primaryKey);
	}

	@Transactional(readOnly = true)
	public List<Employee> getAllEmployees() throws SQLException {
		return employeeDao.getAll();
	}

	@Transactional(readOnly = true)
	public List<Employee> getAllActiveEmployees() throws SQLException {
		return employeeDao.getAllActive();
	}
}