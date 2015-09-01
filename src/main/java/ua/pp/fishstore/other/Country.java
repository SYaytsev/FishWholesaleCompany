package ua.pp.fishstore.other;

public enum Country {
	CANADA("Canada"), USA("USA"), NORWAY("Norway"), FINLAND("Finland"), JAPAN("Japan");

	private String label;

	Country(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
