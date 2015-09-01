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
public class SystemUserService {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private SystemUserDao systemUserDao;

	private static String defaultPassword = "123456";

	// Getters and Setters
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public SystemUserDao getSystemUserDao() {
		return systemUserDao;
	}

	// Methods

	public Employee find(Integer primaryKey) throws SQLException {
		return employeeDao.find(primaryKey);
	}

	public SystemUser findSystemUser(Integer primaryKey) throws SQLException {
		return systemUserDao.find(primaryKey);
	}

	public SystemUser findByLoginPass(String login, String pass)
			throws SQLException {
		return systemUserDao.findByLoginPass(login, pass);
	}

	public void merge(SystemUser systemUser) throws SQLException {
		systemUserDao.merge(systemUser);
	}

	public void persist(SystemUser systemUser) throws SQLException {
		systemUser.setLogin(generateLoginFor(systemUser.getEmployee()));
		systemUser.setPass(getDefaultPassword());
		systemUserDao.persist(systemUser);
	}

	public List<Employee> getAllEmployees() throws SQLException {
		return employeeDao.getAll();
	}

	public List<Employee> getAllActiveEmployees() throws SQLException {
		return employeeDao.getAllActive();
	}

	public List<SystemUser> getAllActiveSystemUsers() throws SQLException {
		return systemUserDao.getAllActive();
	}

	private String generateLoginFor(Employee employee) {
		String login;
		login = employee.getName().substring(0, 1);
		login += employee.getLastName();
		return login.toLowerCase();
	}

	public static String getDefaultPassword() {
		return defaultPassword;
	}

	public static void setDefaultPassword(String defaultPassword) {
		SystemUserService.defaultPassword = defaultPassword;
	}

}
