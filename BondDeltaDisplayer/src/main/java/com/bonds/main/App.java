package com.bonds.main;

import com.bonds.controller.BondCompareController;
import com.bonds.controller.BondDisplayController;
import com.bonds.controller.BondReaderController;
import com.bonds.conversionmethods.ConversionMethods;

public class App {
	
	public static void main(String[] args) {
		
		BondReaderController bondReader1 = new BondReaderController("C:\\bond_prices_day1.xml");
		BondReaderController bondReader2 = new BondReaderController("C:\\bond_prices_day2.xml");
		BondCompareController bondCompare = new BondCompareController(bondReader1, bondReader2);
		BondDisplayController bondDisplay = new BondDisplayController(bondCompare);
		
		bondDisplay.printDay2Bonds();
		
		
		//ConversionMethods.decimalPriceToBondFormatPrice(bond)
		
		
	}

}
