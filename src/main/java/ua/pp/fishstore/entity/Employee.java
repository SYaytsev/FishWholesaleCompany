package ua.pp.fishstore.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
@NamedQueries({
		@NamedQuery(name = "Employee.getAll", query = "SELECT e FROM Employee e ORDER BY e.lastName"),
		@NamedQuery(name = "Employee.getAllActive", query = "SELECT e FROM Employee e WHERE e.activeStatus=TRUE") })
public class Employee extends BasicEntity {

	@Column(name = "name", nullable = false, length = 20)
	private String name;
	@Column(name = "lastName", nullable = false, length = 20)
	private String lastName;
	@Column(name = "personalNumber", nullable = false, length = 10)
	private String personalNumber;
	@Column(name = "position", nullable = false, length = 30)
	private String position;

	@Column(name = "activeStatus", columnDefinition = "BOOLEAN", nullable = false)
	private Boolean activeStatus;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEmployee")
	private Collection<SystemUser> systemUsers;

	public Employee() {
	}

	public Employee(String name, String lastName, String personalNumber,
			String position, Boolean activeStatus) {
		this.name = name;
		this.lastName = lastName;
		this.personalNumber = personalNumber;
		this.position = position;
		this.activeStatus = activeStatus;
		this.systemUsers = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Boolean getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Collection<SystemUser> getSystemUsers() {
		return systemUsers;
	}

	public void setSystemUsers(Collection<SystemUser> systemUsers) {
		this.systemUsers = systemUsers;
	}

	@Override
	public String toString() {
		String txt = String
				.format("Employee id = %1$5d;| Name = %2$20s;| Last name = %3$20s;|"
						+ " Personal number = %4$10s;| Position = %5$30s;| Active status = %6$6B;",
						this.getId(), this.getName(), this.getLastName(),
						this.getPersonalNumber(), this.getPosition(),
						this.getActiveStatus());
		return txt;
	}
}
