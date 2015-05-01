package com.bonds.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.bonds.comparator.CompareByChangeDescendingComparator;
import com.bonds.model.Bond;

public class BondCompareController {
	
	private BondReaderController day1Reader;
	private BondReaderController day2Reader;
	
	public BondCompareController(BondReaderController day1Reader,
			BondReaderController day2Reader) {
		super();
		this.day1Reader = day1Reader;
		this.day2Reader = day2Reader;
	}

	public Queue<Bond> compareBonds(){
		
		List<Bond> day1Bonds;
		List<Bond> day2Bonds;
		
		try {
			day1Reader.readXML();
			day2Reader.readXML();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		day1Bonds = day1Reader.getBonds();
		day2Bonds = day2Reader.getBonds();
		
		Queue<Bond> sortedByChangeBonds = new PriorityQueue<Bond>(1, new CompareByChangeDescendingComparator());
		
		for (int i = 0; i < day2Bonds.size(); i++){
			BigDecimal decimalChange = day2Bonds.get(i).getPrice().subtract(day1Bonds.get(i).getPrice());
			decimalChange = decimalChange.abs();
			day2Bonds.get(i).setChange(decimalChange);
			sortedByChangeBonds.add(day2Bonds.get(i));
		}
		
		return sortedByChangeBonds;
		
	}

}
