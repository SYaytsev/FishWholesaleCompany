package ua.pp.fishstore.other;

public enum OrderStatus {
	RESERVED("Reserved"), RESERVED_PREPAID("Reserved and prepaid"), RESERVED_PREPAID_READY(
			"Reserved, prepaid and ready"), SHIPPED("Shipped"), FULLY_PAID("Fully paid");

	private String label;

	private OrderStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
