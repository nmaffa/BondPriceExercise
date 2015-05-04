package com.bonds.staticmethods;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import java.util.regex.Pattern;

import com.bonds.model.Bond;

public class ConversionMethods {

	public static BigDecimal xmlPriceToBigDecimal(String xmlPrice){
		
		String[] splitPrice = xmlPrice.split("-");
		Integer dollarValue = Integer.parseInt(splitPrice[0]);
		BigDecimal newPrice = new BigDecimal(dollarValue);
		
		BigDecimal priceFraction = new BigDecimal(0.0000);
		if (splitPrice[1].contains("+")){
			priceFraction = priceFraction.add(new BigDecimal(0.015625));
			splitPrice[1] = splitPrice[1].replace("+", "");
		}
		priceFraction = priceFraction.add((new BigDecimal(splitPrice[1])).divide(new BigDecimal(32.0000)));
		
		newPrice = newPrice.add(priceFraction);
		newPrice.setScale(6, RoundingMode.HALF_UP);
		
		return newPrice;
	}
	
	public static BigDecimal issueToCouponRate(String issue){
		
		String[] issueSplit = issue.split(" ");
		BigDecimal couponRate = new BigDecimal(issueSplit[0]);
		couponRate.setScale(6, RoundingMode.HALF_UP);
		
		return couponRate;
		
	}
	
	public static Date issueToMaturityDate(String issue){
		
		String[] issueSplit = issue.split(" ");
		String[] dateSplit = issueSplit[1].split("/");
		
		int year = 100 + Integer.parseInt(dateSplit[2]);
		if (year < (new Date()).getYear()){
			year += 100;
		}
		int month = Integer.parseInt(dateSplit[0])-1;
		int day = Integer.parseInt(dateSplit[1]);
		
		Date maturityDate = new Date(year, month, day);
		
		return maturityDate;
		
	}
	
	public static String couponRateAndDateToIssue(Bond bond){
	
		String issue = bond.getCouponRate().toString() + " ";
		issue += (bond.getMaturityDate().getMonth()+1) + "/";
		issue += (bond.getMaturityDate().getDate()) + "/";
		int year = bond.getMaturityDate().getYear() % 100;
		if (year < 10){
			issue+=0;
		}
		issue += year;		
		
		return issue;
		
	}
	
	public static String decimalPriceToBondFormatPrice(Bond bond){
		
		String[] decimalSplit = bond.getPrice().toString().split(Pattern.quote("."));
		
		String bondPrice = decimalSplit[0];
		
		if (decimalSplit.length == 1){
			bondPrice += "-0";
			return bondPrice;
		}
		
		bondPrice += "-";
		BigDecimal postDecimal = new BigDecimal("0." + decimalSplit[1]);

		if ((postDecimal.doubleValue() % 0.03125) == 0.0){
			int price32nds = postDecimal.multiply(new BigDecimal(32.0000)).intValue();
			bondPrice += price32nds;
		}
		else{
			int price32nds = postDecimal.subtract(new BigDecimal(0.015625)).multiply(new BigDecimal(32.0000)).intValue();
			bondPrice += price32nds + "+";
		}
		
		return bondPrice;
	}
	
	public static String changeTo32(Bond bond){
		BigDecimal bd = bond.getChange().multiply(new BigDecimal(32));
//		bd.setScale(1);
//		bd.stripTrailingZeros();
		
		return bd.stripTrailingZeros().toPlainString();
	}
	
}
