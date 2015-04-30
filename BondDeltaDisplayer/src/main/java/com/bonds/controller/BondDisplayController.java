package com.bonds.controller;

import java.math.BigDecimal;
import java.util.Queue;

import com.bonds.conversionmethods.ConversionMethods;
import com.bonds.model.Bond;

public class BondDisplayController {
	
	private BondCompareController bondCompare;
	
	public BondDisplayController(BondCompareController bondCompare){
		this.bondCompare = bondCompare;
	}
	
	public void printDay2Bonds(){
		
		Queue<Bond> bondQueue = bondCompare.compareBonds();
		
		final Object[][] table = new String[bondQueue.size() + 1][4];
		
		table[0][0] = "Bond Issue   |";
		table[0][1] = "Fractional Price|";
		table[0][2] = "Change    |";
		table[0][3] = "Decimal Price";
		
		int index = 1;
		
		while (!bondQueue.isEmpty()){
			Bond bond = bondQueue.poll();
			table[index][0] = ConversionMethods.couponRateAndDateToIssue(bond);
			table[index][1] = ConversionMethods.decimalPriceToBondFormatPrice(bond);
			table[index][2] = changeTo32(bond);
			table[index][3] = "$" + bond.getPrice().toString();
			index++;
		}
		

		for (final Object[] row : table) {
		    System.out.format("%-15s%-15s%-15s%-15s\n", row);
		    //System.out.println(row);
		}
		
	}
	
	public String changeTo32(Bond bond){
		BigDecimal bd = bond.getChange().multiply(new BigDecimal(32));
		bd.setScale(1);
		bd.stripTrailingZeros();
		
		return bd.toPlainString();
	}

}
