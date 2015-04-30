package com.bonds.controller;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.bonds.conversionmethods.ConversionMethods;
import com.bonds.model.Bond;

import java.util.*;
import java.io.*;
import java.math.BigDecimal;

public class BondReaderController{
	
	//To be made non-static
	//private static List<Bond> bonds =  new ArrayList<Bond>();
	private List<Bond> bonds;
	
	private String filename;
	
	private Bond bond;
	boolean isIssue = false;
	boolean isPrice = false;
	
	
	
	//Get rid of this main method after testing
//	public static void main(String[] args){
//		BondReaderController br = new BondReaderController("C:\\bond_prices_day1.xml");
//		try {
//			br.readXML();
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		for (Bond b: bonds){
//			System.out.println(b.toString());
//		}
//		
//	}
	
	public BondReaderController(String filename){
		this.filename = filename;
		bonds = new ArrayList<Bond>();
		isIssue = false;
		isPrice = false;
	}
	
	public void readXML() throws ParserConfigurationException, SAXException, IOException{
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		
		DefaultHandler handler = new DefaultHandler() {
		 
			public void startElement(String uri, String localName,String qName, 
		                Attributes attributes) throws SAXException {
		 
				System.out.println("Start Element :" + qName);
				
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
		 
				System.out.println("End Element :" + qName);
				if (qName.equalsIgnoreCase("bond")) {
					bonds.add(bond);
				}
		 
			}
		 
			public void characters(char ch[], int start, int length) throws SAXException {
		 
				if (isIssue) {
					System.out.println("Issue : " + new String(ch, start, length));
					String issue = new String(ch, start, length);
					bond.setMaturityDate(ConversionMethods.issueToMaturityDate(issue));
					bond.setCouponRate(ConversionMethods.issueToCouponRate(issue));
					isIssue = false;
				}
				
				if (isPrice) {
					System.out.println("Price : " + new String(ch, start, length));
					String priceStr = new String(ch, start, length);
					bond.setPrice(ConversionMethods.xmlPriceToBigDecimal(priceStr));
					isPrice = false;
				}
		 
			}
		 
		};
		
		saxParser.parse(filename, handler);
	}
	
	public List<Bond> getBonds(){
		return bonds;	
	}
	
	

}

//    public void startDocument() throws SAXException {
//        tags = new Hashtable();
//    }
//
//    public void startElement(String namespaceURI,
//            String localName,
//            String qName, 
//            Attributes atts)
//			throws SAXException {
//			
//			String key = localName;
//			Object value = tags.get(key);
//			
//			if (value == null) {
//				tags.put(key, new Integer(1));
//			} 
//			else {
//				int count = ((Integer)value).intValue();
//				count++;
//				tags.put(key, new Integer(count));
//			}
//    }
//    
//    
//    public void endDocument() throws SAXException {
//        Enumeration e = tags.keys();
//        while (e.hasMoreElements()) {
//            String tag = (String)e.nextElement();
//            int count = ((Integer)tags.get(tag)).intValue();
//            System.out.println("Local Name \"" + tag + "\" occurs " 
//                               + count + " times");
//        }    
//    }
//    
//    
//	
//	static public void main(String[] args) throws Exception {
//	    String filename = null;
//	    
//
//	    for (int i = 0; i < args.length; i++) {
//	        filename = args[i];
//	        if (i != args.length - 1) {
//	            usage();
//	        }
//	    }
//
//	    if (filename == null) {
//	        usage();
//	    } 
//	}
//	
//	private static String convertToFileURL(String filename) {
//        String path = new File(filename).getAbsolutePath();
//        if (File.separatorChar != '/') {
//            path = path.replace(File.separatorChar, '/');
//        }
//
//        if (!path.startsWith("/")) {
//            path = "/" + path;
//        }
//        return "file:" + path;
//    }
//	
//	private static void usage() {
//	    System.err.println("Usage: BondReader <file.xml>");
//	    System.err.println("       -usage or -help = this message");
//	    System.exit(1);
//	}

