package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Violation;

public class CSVHReader implements Reader {

	String filename;
	
	/**
	 * Construct a reader to read a csv file with head
	 * 
	 * @param filename
	 */
	public CSVHReader(String filename) {
	    this.filename = filename; 
	}
	
	/**
	 * Open a csv file and save contents in an arrayList
	 * 
	 * @return the contents from the input csv file
	 */
	@Override
	public ArrayList<Object> open() {
		BufferedReader br = null;
		String line = "";
		ArrayList<Object> propertyList = new ArrayList<>();
		
		try {
			br = new BufferedReader(new FileReader(filename));
			String header = br.readLine();
			String[] columns = null;
	        if (header != null) {
	            columns = header.split(",");
	        }
	        
	        int mv_index = 0;
	        int tla_index = 0;
	        int zipcode_index = 0;
	        for (int i = 0; i < columns.length; i++) {
	        	if (columns[i].contentEquals("market_value")){
	        		mv_index = i;
	        	}else if (columns[i].contentEquals("total_livable_area")) {
	        		tla_index = i;
	        	}else if (columns[i].contentEquals("zip_code")) {
	        		zipcode_index = i;
	        	}
	        }
	        
            String[] resArray;
            String market_value = null;
            String total_livable_area = null;
            String zip_code = null;
			while ((line = br.readLine()) != null) {
				resArray = line.split(",");
				if ((resArray[mv_index] != null) && (resArray[mv_index].contentEquals("0.0") == false) && (resArray[tla_index] != null) && (resArray[tla_index].contentEquals("0.0") == false) && (resArray[zipcode_index].length() >= 5)) {
					market_value = resArray[mv_index];
					total_livable_area = resArray[tla_index];
					zip_code = resArray[zipcode_index].substring(0,5);							          	
					//System.out.println("market_value= " + market_value + " , total_livable_area=" + total_livable_area + " , zipCode=" + zip_code);
					Property propertyFrame = new Property(market_value, total_livable_area, zip_code);
	    			propertyList.add(propertyFrame); 
				}
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (br != null) {
				try {
					br.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	return propertyList;
	}
	
	
//	public static void main(String[] args) {
//		CSVHReader c = new CSVHReader("properties.csv");
//		ArrayList<Property> propertyList = c.open();
//		for (int i = 0; i < propertyList.size(); i++) {
//			String market_value = propertyList.get(i).getMarketValue();
//			String zip_code = propertyList.get(i).getZipCode();
//	 		String area = propertyList.get(i).getTotalLivableArea();
//	 		double mv = propertyList.get(i).getMV();
//	 		double tlaArea = propertyList.get(i).getArea();
//			System.out.println(market_value + " " + zip_code + " " + area);
//			System.out.println(Double.toString(mv) + " " + zip_code + " " + Double.toString(tlaArea));
//		}
//	}
}