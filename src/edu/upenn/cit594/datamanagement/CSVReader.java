package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Violation;

public class CSVReader {

	String filename;
	
	/**
	 * Construct a reader to read a csv file
	 * 
	 * @param filename
	 */
	public CSVReader(String filename) {
	    this.filename = filename; 
	}
	
	/**
	 * Open a csv file and save contents in an arrayList
	 * 
	 * @return the contents from the input csv file
	 */
	public ArrayList<Violation> open() {
		BufferedReader br = null;
		String line = "";
		//ArrayList<String> csv = new ArrayList<>();
		ArrayList<Violation> violationList = new ArrayList<>();
		
		try {
			br = new BufferedReader(new FileReader(filename));
            String[] resArray;
			while ((line = br.readLine()) != null) {
				resArray = line.split(",");
				if ((resArray.length > 6) && (resArray[1] != null)  && (resArray[4] != null) && (resArray[6] != null)) {
					String fine = resArray[1];
					String state = resArray[4];
	            	String zipCode = resArray[6];
					//System.out.println("fine= " + fine + " , state=" + state + " , zipCode=" + zipCode);
					if (zipCode.length() == 5) {
						Violation violationFrame = new Violation(fine, zipCode, state);
	        			violationList.add(violationFrame);
					}		
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
	return violationList;
	}
	
	
//	public static void main(String[] args) {
//		CSVReader c = new CSVReader("parking.csv");
//		ArrayList<Violation> violationList = c.open();
//		for (int i = 0; i < violationList.size(); i++) {
//			String fine = violationList.get(i).getFine();
//			String zip_code = violationList.get(i).getZipCode();
//	 		String population = violationList.get(i).getState();
//			System.out.println(fine + " " + zip_code + " " + population);
//		}
//	}
}


