package ua.pp.fishstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "Constant")
@NamedQuery(name = "Constant.getConstant", query = "SELECT consts FROM Constant consts")
public class Constant extends BasicEntity {

	@Column(name = "sellingExpensesIndex", nullable = false)
	private double sellingExpensesIndex;
	@Column(name = "interestRatePerYear", nullable = false)
	private double interestRatePerYear;
	@Column(name = "taxRate", nullable = false)
	private double taxRate;
	@Column(name = "generalExpensesPerMonth", nullable = false)
	private double generalExpensesPerMonth;
	@Column(name = "equity", nullable = false)
	private double equity;
	@Column(name = "daysForReserve", nullable = false)
	private Short daysForReserve;

	public Constant() {
	}

	public double getSellingExpensesIndex() {
		return sellingExpensesIndex;
	}

	public void setSellingExpensesIndex(double sellingExpensesIndex) {
		this.sellingExpensesIndex = sellingExpensesIndex;
	}

	public double getInterestRatePerYear() {
		return interestRatePerYear;
	}

	public void setInterestRatePerYear(double interestRatePerYear) {
		this.interestRatePerYear = interestRatePerYear;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public double getGeneralExpensesPerMonth() {
		return generalExpensesPerMonth;
	}

	public void setGeneralExpensesPerMonth(double generalExpensesPerMonth) {
		this.generalExpensesPerMonth = generalExpensesPerMonth;
	}

	public double getEquity() {
		return equity;
	}

	public void setEquity(double equity) {
		this.equity = equity;
	}

	public Short getDaysForReserve() {
		return daysForReserve;
	}

	public void setDaysForReserve(Short daysForReserve) {
		this.daysForReserve = daysForReserve;
	};

	@Override
	public String toString() {
		String txt = String
				.format("Constant id = %1$5d;| Selling expenses index = %2$5s;| "
						+ "Interest rate per year = %3$5s;| Tax rate = %4$5s;| "
						+ "General expenses per month = %5$21s;| Equity = %6$21s;| "
						+ "Days for reservee = %7$3s;| ", this.getId(),
						this.getSellingExpensesIndex(),
						this.getInterestRatePerYear(), this.getTaxRate(),
						this.getGeneralExpensesPerMonth(), this.getEquity(),
						this.getDaysForReserve());
		return txt;
	}
}
