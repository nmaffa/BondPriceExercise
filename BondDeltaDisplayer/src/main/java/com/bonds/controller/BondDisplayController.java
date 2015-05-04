package com.bonds.controller;

import java.math.BigDecimal;
import java.util.Queue;

import com.bonds.model.Bond;
import com.bonds.staticmethods.ConversionMethods;

public class BondDisplayController {
	
	private Queue<Bond> bondQueue;
	
	//Currently accepts sorted Queue of Bonds; can be abstracted to Collection if necessary
	public BondDisplayController(Queue<Bond> bondQueue){
		this.bondQueue = bondQueue;
	}
	
	//Returns count of bonds in queue
	public int printDay2Bonds(){
		
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
			table[index][2] = ConversionMethods.changeTo32(bond);
			table[index][3] = "$" + bond.getPrice().toString();
			index++;
		}
		

		for (final Object[] row : table) {
		    System.out.format("%-15s%-15s%-15s%-15s\n", row);
		}
		
		return index - 1;
		
	}
	
}
