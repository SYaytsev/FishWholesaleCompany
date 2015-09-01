package ua.pp.fishstore.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import ua.pp.fishstore.entity.Employee;
import ua.pp.fishstore.entity.SystemUser;
import ua.pp.fishstore.other.SystemUserRole;
import ua.pp.fishstore.service.SystemUserService;

@Named
@SessionScoped
public class SystemUserController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger
			.getLogger(SystemUserController.class);

	@Inject
	private SystemUserService systemUserService;

	private int selectedEmployeeId;
	private int selectedSystemUserId;
	private SystemUser newSystemUser;

	private List<Employee> allEmployees;
	private List<Employee> currentEmployees;
	private List<SystemUser> systemUsers;

	@PostConstruct
	private void init() {
		updateAllEmployees();
		updateListEmployees();
		updateListActiveSystemUsers();
		defaultNewSystemUser();
	}

	// Methods
	private void updateListEmployees() {
		try {
			currentEmployees = systemUserService.getAllActiveEmployees();
		} catch (Exception e) {
			currentEmployees = new ArrayList<Employee>();
			log.error(e.getMessage(), e);
		}
	}

	private void updateAllEmployees() {
		try {
			allEmployees = new ArrayList<Employee>();
			List<Employee> tmpList = systemUserService.getAllEmployees();
			for (Employee employeeItem : tmpList) {
				if (employeeItem.getSystemUsers().size() != 0) {
					allEmployees.add(employeeItem);
				}
			}
		} catch (Exception e) {
			allEmployees = new ArrayList<Employee>();
			log.error(e.getMessage(), e);
		}
	}

	private void updateListActiveSystemUsers() {
		try {
			systemUsers = systemUserService.getAllActiveSystemUsers();
		} catch (Exception e) {
			systemUsers = new ArrayList<SystemUser>();
			log.error(e.getMessage(), e);
		}
	}

	private void defaultNewSystemUser() {
		selectedEmployeeId = 0;
		newSystemUser = new SystemUser();
		newSystemUser.setStartDate(new Date());
	}

	public void updateSystemUser(RowEditEvent event) {
		SystemUser systemUser = (SystemUser) event.getObject();
		try {
			systemUserService.merge(systemUser);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"User data was updated"));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void createNewSystemUser() {
		try {
			Employee tmpEmployee = systemUserService.find(selectedEmployeeId);
			newSystemUser.setEmployee(tmpEmployee);
			systemUserService.persist(newSystemUser);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"New user was added"));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		updateListActiveSystemUsers();
		updateAllEmployees();
		defaultNewSystemUser();
		log.info(newSystemUser);
	}

	public void deleteSystemUser(SystemUser systemUser) {
		Date tmpDate = systemUser.getStartDate();
		if (tmpDate.compareTo(new Date()) < 0) {
			systemUser.setExpiryDate(tmpDate);
			try {
				systemUserService.merge(systemUser);
				log.info("Data of " + systemUser.getLogin() + " was updated");
				FacesContext.getCurrentInstance().addMessage(

						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Warning!", "You delete current user"));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		updateListActiveSystemUsers();
		updateAllEmployees();
	}

	// Enumeration
	public SystemUserRole[] getSystemUserRoles() {
		return SystemUserRole.values();
	}

	// Setters and Getters
	public int getSelectedEmployeeId() {
		return selectedEmployeeId;
	}

	public int getSelectedSystemUserId() {
		return selectedSystemUserId;
	}

	public void setSelectedSystemUserId(int selectedSystemUserId) {
		this.selectedSystemUserId = selectedSystemUserId;
	}

	public void setSelectedEmployeeId(int selectedEmployeeId) {
		this.selectedEmployeeId = selectedEmployeeId;
	}

	public SystemUser getNewSystemUser() {
		return newSystemUser;
	}

	public void setNewSystemUser(SystemUser newSystemUser) {
		this.newSystemUser = newSystemUser;
	}

	public List<Employee> getAllEmployees() {
		return allEmployees;
	}

	public void setAllEmployees(List<Employee> allEmployees) {
		this.allEmployees = allEmployees;
	}

	public List<Employee> getCurrentEmployees() {
		return currentEmployees;
	}

	public void setCurrentEmployees(List<Employee> currentEmployees) {
		this.currentEmployees = currentEmployees;
	}

	public List<SystemUser> getSystemUsers() {
		return systemUsers;
	}

	public void setSystemUsers(List<SystemUser> systemUsers) {
		this.systemUsers = systemUsers;
	}
}
