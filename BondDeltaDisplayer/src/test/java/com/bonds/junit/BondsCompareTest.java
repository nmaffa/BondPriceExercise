package com.bonds.junit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;

import com.bonds.controller.BondCompareController;
import com.bonds.model.Bond;

public class BondsCompareTest {
	
	private static BondCompareController bcc;
	private static List<Bond> day1Bonds;
	private static List<Bond> day2Bonds;
	
	@BeforeClass
	public static void initializeVariables(){
		
		//Arrange lists and BondCompareController for testing
		
		day1Bonds = new ArrayList<Bond>();
		day1Bonds.add(new Bond(new BigDecimal(1.5), new Date(), new BigDecimal(105.75)));
		
	}
	
	

}
