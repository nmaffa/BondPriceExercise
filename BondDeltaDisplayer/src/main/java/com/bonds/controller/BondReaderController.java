package com.bonds.controller;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.bonds.model.Bond;
import com.bonds.staticmethods.ConversionMethods;

import java.util.*;
import java.io.*;

public class BondReaderController{
	
	private List<Bond> bonds;
	private String filename;
	private int bondCount;
	
	
	public BondReaderController(String filename){
		this.filename = filename;
		bonds = new ArrayList<Bond>();
		bondCount = 0;
	}
	
	public int readXML(){
		
		bonds.clear();
		bondCount = 0;
			
		DefaultHandler handler = new DefaultHandler() {
		 
			Bond bond;
			boolean isIssue = false;
			boolean isPrice =false;
			
			public void startElement(String uri, String localName,String qName, 
		                Attributes attributes) throws SAXException {
		 
				//System.out.println("Start Element :" + qName);
				
				if (qName.equalsIgnoreCase("bond")) {
					bond = new Bond();
				}
		 
				if (qName.equalsIgnoreCase("issue")) {
					isIssue = true;
				}
		 
				if (qName.equalsIgnoreCase("price")) {
					isPrice = true;
				}
		 
			}
		 
			public void endElement(String uri, String localName,
				String qName) throws SAXException {
		 
				//System.out.println("End Element :" + qName);
				if (qName.equalsIgnoreCase("bond")) {
					bonds.add(bond);
					bondCount++;
				}
		 
			}
		 
			public void characters(char ch[], int start, int length) throws SAXException {
		 
				if (isIssue) {
					//System.out.println("Issue : " + new String(ch, start, length));
					String issue = new String(ch, start, length);
					bond.setMaturityDate(ConversionMethods.issueToMaturityDate(issue));
					bond.setCouponRate(ConversionMethods.issueToCouponRate(issue));
					isIssue = false;
				}
				
				if (isPrice) {
					//System.out.println("Price : " + new String(ch, start, length));
					String priceStr = new String(ch, start, length);
					bond.setPrice(ConversionMethods.xmlPriceToBigDecimal(priceStr));
					isPrice = false;
				}
		 
			}
		 
		};
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser;
		
		try {
			saxParser = factory.newSAXParser();
			saxParser.parse(filename, handler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bondCount;
	}
	
	public List<Bond> getBonds(){
		return bonds;	
	}
	
	public List<Bond> readXmlAndGetBonds(){
		readXML();
		return bonds;
	}

}

