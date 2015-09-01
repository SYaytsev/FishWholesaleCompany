package ua.pp.fishstore.other;

// TODO add methods to return different arrays, optional to values()
// SECURITY_OFFICER("Security officer")
public enum SystemUserRole {

	GENERAL_MANAGER("General manager"), COLD_STORE_MANAGER("Cold store manager"), ACCOUNTANT(
			"Accountant"), SECURITY_OFFICER("Security officer");

	private String label;

	private SystemUserRole(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}