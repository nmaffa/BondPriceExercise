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
	
//	private BondReaderController day1Reader;
//	private BondReaderController day2Reader;
	private List<Bond> day1Bonds;
	private List<Bond> day2Bonds;
	private Queue<Bond> sortedByChangeBonds;
	
//	public BondCompareController(BondReaderController day1Reader,
//			BondReaderController day2Reader) {
//		super();
//		this.day1Reader = day1Reader;
//		this.day2Reader = day2Reader;
//	}
	
	public BondCompareController(List<Bond> day1Bonds, List<Bond> day2Bonds){
		super();
		this.day1Bonds = day1Bonds;
		this.day2Bonds = day2Bonds;
	}
	
	//Returns sum of absolute value of change between each bond pair 
	public BigDecimal compareBonds(){
		
		BigDecimal sumOfChange = new BigDecimal(0);
		
		sortedByChangeBonds = new PriorityQueue<Bond>(1, new CompareByChangeDescendingComparator());
		
		for (int i = 0; i < day2Bonds.size(); i++){
			BigDecimal decimalChange = day2Bonds.get(i).getPrice().subtract(day1Bonds.get(i).getPrice());
			decimalChange = decimalChange.abs();
			sumOfChange = sumOfChange.add(decimalChange);
			day2Bonds.get(i).setChange(decimalChange);
			sortedByChangeBonds.add(day2Bonds.get(i));
		}
		
		return sumOfChange;
		
	}
	
	
	
	public Queue<Bond> getSortedBonds(){
		compareBonds();
		return sortedByChangeBonds;
	}

}
