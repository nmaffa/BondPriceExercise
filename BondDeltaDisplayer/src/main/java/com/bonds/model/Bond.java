package com.bonds.model;

import java.math.BigDecimal;
import java.util.Date;

public class Bond {
	
	private BigDecimal couponRate;
	private Date maturityDate;
	private BigDecimal price;
	private BigDecimal change;
	private BigDecimal yield;
	
	public Bond(){
		super();
	}
	
	public Bond(BigDecimal couponRate, Date maturityDate, BigDecimal price){
		super();
		this.couponRate = couponRate;
		this.maturityDate = maturityDate;
		this.price = price;
	}

	public BigDecimal getCouponRate() {
		return couponRate;
	}

	public void setCouponRate(BigDecimal couponRate) {
		this.couponRate = couponRate;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	public BigDecimal getYield() {
		return yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	@Override
	public String toString() {
		return "Bond [change=" + change + ", couponRate=" + couponRate + ", maturityDate="
				+ maturityDate + ", price=" + price + "]";
	}
	
	

}
