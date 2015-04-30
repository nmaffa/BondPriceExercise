package com.bonds.controller;

import java.util.Queue;

public class BondDisplayController {
	
	private BondCompareController bondCompare;
	
	public BondDisplayController(BondCompareController bondCompare){
		this.bondCompare = bondCompare;
	}
	
	public void printDay2Bonds(){
		
		Queue bondQueue = bondCompare.compareBonds();
		
		while(!bondQueue.isEmpty()){
			System.out.println(bondQueue.poll().toString());
		}
		
	}

}
