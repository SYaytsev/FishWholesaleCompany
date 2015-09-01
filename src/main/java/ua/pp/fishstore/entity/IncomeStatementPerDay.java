package ua.pp.fishstore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "IncomeStatementPerDay")
@NamedQuery(name = "IncomeStatementPerDay.getAll", query = "SELECT ispd FROM IncomeStatementPerDay ispd")
public class IncomeStatementPerDay extends BasicEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "byDate", nullable = false)
	private Date byDate;

	@Column(name = "netSales", nullable = false)
	private double netSales;
	@Column(name = "COGS", nullable = false)
	private double COGS;
	@Column(name = "generalExpenses", nullable = false)
	private double generalExpenses;
	@Column(name = "sellingExpenses", nullable = false)
	private double sellingExpenses;
	@Column(name = "financialExpenses", nullable = false)
	private double financialExpenses;
	@Column(name = "tax", nullable = false)
	private double tax;
	@Column(name = "tradeReceivable", nullable = false)
	private double tradeReceivable;
	@Column(name = "inventory", nullable = false)
	private double inventory;
	@Column(name = "tradePayble", nullable = false)
	private double tradePayble;
	@Column(name = "equity", nullable = false)
	private double equity;
	@Column(name = "borrowings", nullable = false)
	private double borrowings;

	public IncomeStatementPerDay() {
	}

	public Date getByDate() {
		return byDate;
	}

	public void setByDate(Date byDate) {
		this.byDate = byDate;
	}

	public double getNetSales() {
		return netSales;
	}

	public void setNetSales(double netSales) {
		this.netSales = netSales;
	}

	public double getCOGS() {
		return COGS;
	}

	public void setCOGS(double cOGS) {
		COGS = cOGS;
	}

	public double getGeneralExpenses() {
		return generalExpenses;
	}

	public void setGeneralExpenses(double generalExpenses) {
		this.generalExpenses = generalExpenses;
	}

	public double getSellingExpenses() {
		return sellingExpenses;
	}

	public void setSellingExpenses(double sellingExpenses) {
		this.sellingExpenses = sellingExpenses;
	}

	public double getFinancialExpenses() {
		return financialExpenses;
	}

	public void setFinancialExpenses(double financialExpenses) {
		this.financialExpenses = financialExpenses;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTradeReceivable() {
		return tradeReceivable;
	}

	public void setTradeReceivable(double tradeReceivable) {
		this.tradeReceivable = tradeReceivable;
	}

	public double getInventory() {
		return inventory;
	}

	public void setInventory(double inventory) {
		this.inventory = inventory;
	}

	public double getTradePayble() {
		return tradePayble;
	}

	public void setTradePayble(double tradePayble) {
		this.tradePayble = tradePayble;
	}

	public double getEquity() {
		return equity;
	}

	public void setEquity(double equity) {
		this.equity = equity;
	}

	public double getBorrowings() {
		return borrowings;
	}

	public void setBorrowings(double borrowings) {
		this.borrowings = borrowings;
	}

	@Override
	public String toString() {
		return "IncomeStatementPerDay [getByDate()=" + getByDate()
				+ ", getNetSales()=" + getNetSales() + ", getCOGS()="
				+ getCOGS() + ", getGeneralExpenses()=" + getGeneralExpenses()
				+ ", getSellingExpenses()=" + getSellingExpenses()
				+ ", getFinancialExpenses()=" + getFinancialExpenses()
				+ ", getTax()=" + getTax() + ", getTradeReceivable()="
				+ getTradeReceivable() + ", getInventory()=" + getInventory()
				+ ", getTradePayble()=" + getTradePayble() + ", getEquity()="
				+ getEquity() + ", getBorrowings()=" + getBorrowings() + "]";
	}

}