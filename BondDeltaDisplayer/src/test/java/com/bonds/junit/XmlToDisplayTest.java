package com.bonds.junit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.bonds.controller.BondCompareController;
import com.bonds.controller.BondDisplayController;
import com.bonds.controller.BondReaderController;

public class XmlToDisplayTest {

	private static BondReaderController brc1;
	private static BondReaderController brc2;
	private static BondCompareController bcc;
	private static BondDisplayController bdc;
	
	@BeforeClass
	public static void initializeControllers(){
		
		//Arrange - initialize controllers for below tests
		brc1 = new BondReaderController("src/main/resources/com/bonds/xml/bond_prices_day1.xml");
		brc2 = new BondReaderController("src/main/resources/com/bonds/xml/bond_prices_day2.xml");
		bcc = new BondCompareController(brc1.readXmlAndGetBonds(), brc2.readXmlAndGetBonds());
		bdc = new BondDisplayController(bcc.getSortedBonds());
	}
	
//	private BondReaderController brc1;
//	private BondReaderController brc2;
//	private BondCompareController bcc;
//	private BondDisplayController bdc;
//	
//	@Before
//	public void initializeControllers(){
//		
//		//Arrange - initialize controllers for below tests
//		brc1 = new BondReaderController("src/main/resources/com/bonds/xml/bond_prices_day1.xml");
//		brc2 = new BondReaderController("src/main/resources/com/bonds/xml/bond_prices_day2.xml");
//		bcc = new BondCompareController(brc1.readXmlAndGetBonds(), brc2.readXmlAndGetBonds());
//		bdc = new BondDisplayController(bcc.getSortedBonds());
//	}
	
	@Test
	public void readXmlTest1() throws ParserConfigurationException, SAXException, IOException{
		
		//Arrange
		int expected = 6;
		
		//Act
		int actual = brc1.readXML();
		
		//Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void readXmlTest2() throws ParserConfigurationException, SAXException, IOException{
		
		//Arrange
		int expected = 6;
		
		//Act
		int actual = brc2.readXML();
		
		//Assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void compareBondsTest(){
		
		//Arrange
		BigDecimal expected = new BigDecimal(0.859375);
		
		//Act
		BigDecimal actual = bcc.compareBonds();
		
		//Assert
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void printBondsTest(){
		
		//Arrange
		int expected = 6;
		
		//Act
		int actual = bdc.printDay2Bonds();
		
		//Assert
		assertEquals(expected, actual);
		
	}
	
	
	
}
