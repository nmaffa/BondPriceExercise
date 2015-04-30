package com.bonds.junit;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.bonds.conversionmethods.ConversionMethods;
import com.bonds.model.Bond;

import org.junit.Test;

public class ConversionMethodsTest {
	
	@Test
	public void testReadPriceWithPlus(){
		
		// Arrange – prepare everything I need to do a test
	    String priceString = "108-24+";
	    BigDecimal expected = new BigDecimal(108.765625);

	    // Act – the money line
	   BigDecimal actual = ConversionMethods.xmlPriceToBigDecimal(priceString);

	   // Assert
	   assertEquals(expected, actual);
		
	}
	
	@Test
	public void testReadPriceWithoutPlus(){
		
		// Arrange – prepare everything I need to do a test
	    String priceString = "108-24";
	    BigDecimal expected = new BigDecimal(108.75);

	    // Act – the money line
	   BigDecimal actual = ConversionMethods.xmlPriceToBigDecimal(priceString);

	   // Assert
	   assertEquals(expected, actual);
		
	}
	
	@Test
	public void testReadPrice2WithPlus(){
		
		// Arrange – prepare everything I need to do a test
	    String priceString = "97-16+";
	    BigDecimal expected = new BigDecimal(97.515625);

	    // Act – the money line
	   BigDecimal actual = ConversionMethods.xmlPriceToBigDecimal(priceString);

	   // Assert
	   assertEquals(expected, actual);
		
	}
	
	@Test
	public void testIssueToCouponRate(){
		
		// Arrange – prepare everything I need to do a test
	    String issueString = "3.4567 3/16/92";
	    BigDecimal expected = new BigDecimal(3.4567).setScale(4, RoundingMode.HALF_UP);

	    // Act – the money line
	   BigDecimal actual = ConversionMethods.issueToCouponRate(issueString);

	   // Assert
	   assertEquals(expected, actual);
		
	}

	@Test
	public void testIssueToMaturityDate(){
		
		// Arrange – prepare everything I need to do a test
	    String issueString = "3.4567 3/16/92";
	    Date expected = new Date(192, 2, 16);

	    // Act – the money line
	   Date actual = ConversionMethods.issueToMaturityDate(issueString);

	   // Assert
	   assertEquals(expected, actual);
		
	}
	
	@Test
	public void testCouponRateAndDateToIssue(){
		
		// Arrange – prepare everything I need to do a test
		Bond b = new Bond();
		b.setCouponRate(new BigDecimal(1.375));
		b.setMaturityDate(new Date(210, 2, 31));
	    String expected = "1.375 3/31/10";

	    // Act – the money line
	   String actual = ConversionMethods.couponRateAndDateToIssue(b);

	   // Assert
	   assertEquals(expected, actual);
		
	}
	
	@Test
	public void testDecimalPriceToBondFormatPrice(){
		
		// Arrange – prepare everything I need to do a test
		Bond b = new Bond();
		b.setPrice(new BigDecimal(98.515625));
	    String expected = "98-16+";

	    // Act – the money line
	   String actual = ConversionMethods.decimalPriceToBondFormatPrice(b);

	   // Assert
	   assertEquals(expected, actual);
		
	}
	
	@Test
	public void testDecimalPriceToBondFormatPrice2(){
		
		// Arrange – prepare everything I need to do a test
		Bond b = new Bond();
		b.setPrice(new BigDecimal(98.5));
	    String expected = "98-16";

	    // Act – the money line
	   String actual = ConversionMethods.decimalPriceToBondFormatPrice(b);

	   // Assert
	   assertEquals(expected, actual);
		
	}
	
	@Test
	public void testDecimalPriceToBondFormatPrice3(){
		
		// Arrange – prepare everything I need to do a test
		Bond b = new Bond();
		b.setPrice(new BigDecimal(98.00));
	    String expected = "98-0";

	    // Act – the money line
	   String actual = ConversionMethods.decimalPriceToBondFormatPrice(b);

	   // Assert
	   assertEquals(expected, actual);
		
	}
	
	@Test
	public void testDecimalPriceToBondFormatPrice4(){
		
		// Arrange – prepare everything I need to do a test
		Bond b = new Bond();
		b.setPrice(new BigDecimal(103.984375));
	    String expected = "103-31+";

	    // Act – the money line
	   String actual = ConversionMethods.decimalPriceToBondFormatPrice(b);

	   // Assert
	   assertEquals(expected, actual);
		
	}
	
	@Test
	public void testDecimalPriceToBondFormatPrice5(){
		
		// Arrange – prepare everything I need to do a test
		Bond b = new Bond();
		b.setPrice(new BigDecimal(103.96875));
	    String expected = "103-31";

	    // Act – the money line
	   String actual = ConversionMethods.decimalPriceToBondFormatPrice(b);

	   // Assert
	   assertEquals(expected, actual);
		
	}
	
}
