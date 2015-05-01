package com.bonds.junit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.bonds.controller.BondCompareController;
import com.bonds.controller.BondDisplayController;
import com.bonds.controller.BondReaderController;

public class XmlToDisplayTest {

	private BondReaderController brc1;
	private BondReaderController brc2;
	private BondCompareController bcc;
	private BondDisplayController bdc;
	
	@Before
	public void initializeControllers(){
		
		//Arrange - initialize controllers for below tests
		brc1 = new BondReaderController("src/main/resources/com/bonds/xml/bond_prices_day1.xml");
		brc2 = new BondReaderController("src/main/resources/com/bonds/xml/bond_prices_day2.xml");
		bcc = new BondCompareController(brc1, brc2);
		bdc = new BondDisplayController(bcc);
	}
	
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
	
//	@Test
//	public void compareBondsTest(){
//		
//		//Arrange
//		int expected = 6;
//		
//		//Act
//		int actual = brc2.readXML();
//		
//		//Assert
//		assertEquals(expected, actual);
//		
//	}
	
	
	
}
