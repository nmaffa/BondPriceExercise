package com.bonds.comparator;

import java.util.Comparator;

import com.bonds.model.Bond;

public class CompareByChangeDescendingComparator implements
		Comparator<Bond> {
	
	public int compare(Bond o1, Bond o2) {
		if ((o1.getChange() == null) && (o2.getChange() == null)){
			return 0;
		}
		if (o1.getChange() == null){
			return -1;
		}
		if (o2.getChange() == null){
			return 1;
		}
		
		return o2.getChange().compareTo(o1.getChange());
	}

}
