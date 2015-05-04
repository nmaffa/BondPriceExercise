package com.bonds.main;

import com.bonds.controller.BondCompareController;
import com.bonds.controller.BondDisplayController;
import com.bonds.controller.BondReaderController;
import com.bonds.staticmethods.ConversionMethods;

public class App {
	
	public static void main(String[] args) {
		
//		BondReaderController bondReader1 = new BondReaderController("C:\\bond_prices_day1.xml");
//		BondReaderController bondReader2 = new BondReaderController("C:\\bond_prices_day2.xml");
		BondReaderController bondReader1 = new BondReaderController("src/main/resources/com/bonds/xml/bond_prices_day1.xml");
		BondReaderController bondReader2 = new BondReaderController("src/main/resources/com/bonds/xml/bond_prices_day2.xml");
		BondCompareController bondCompare = new BondCompareController(bondReader1.readXmlAndGetBonds(), bondReader2.readXmlAndGetBonds());
		BondDisplayController bondDisplay = new BondDisplayController(bondCompare.getSortedBonds());
		
		bondDisplay.printDay2Bonds();
		
		
	}

}
