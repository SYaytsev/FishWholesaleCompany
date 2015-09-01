package ua.pp.fishstore.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.pp.fishstore.dao.EmployeeDao;
import ua.pp.fishstore.dao.SystemUserDao;
import ua.pp.fishstore.entity.Employee;
import ua.pp.fishstore.entity.SystemUser;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private SystemUserDao systemUserDao;

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public List<Employee> getAllEmployees() throws SQLException {
		return employeeDao.getAll();
	}

	public List<Employee> getAllActiveEmployees() throws SQLException {
		return employeeDao.getAllActive();
	}

	public List<SystemUser> getAllUsers() throws SQLException {
		return systemUserDao.getAll();
	}

	public List<SystemUser> getAllActiveUsers() throws SQLException {
		return systemUserDao.getAllActive();
	}

	public void create(SystemUser systemUser) throws SQLException {
		systemUserDao.persist(systemUser);
	}

	public void update(SystemUser systemUser) throws SQLException {
		systemUserDao.merge(systemUser);
	}

	public SystemUser read(Integer primaryKey) throws SQLException {
		return systemUserDao.find(primaryKey);
	}

	// private boolean isEmployeeHasValidRole(Employee employee) {
	//
	// return true;
	//
	// }

}
