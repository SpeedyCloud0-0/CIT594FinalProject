package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import edu.upenn.cit594.data.Population;

public class TXTReader{

	String filename;
	
	/**
	 * Construct a reader to open a txt file
	 * 
	 * @param filename
	 */
	public TXTReader(String filename) {
	    this.filename = filename; 
	}
	
	/**
	 * Open the txt input file and save all population information
	 * into an ArrayList
	 * 
	 * @return all population information from input files
	 */
	public ArrayList<Population> open() {
		//ArrayList<String> words = new ArrayList<>();
		ArrayList<Population> populationList = new ArrayList<>();
        try{
        	Path path = Paths.get(filename);
    		String filename = path.toAbsolutePath().toString();
            BufferedReader buf = new BufferedReader(new FileReader(filename));     
            String line = "";
            String[] resArray;
            String zipCode;
            String p;
            int population;
            
            while((line = buf.readLine()) != null){
            	//words.add(line);  
            	resArray = line.split("\\s+");
            	if ((resArray[0] != null) && (resArray[0].length() >= 5) && (resArray[1] != null)) {
            		zipCode = resArray[0];
        			p = resArray[1];
        			population = Integer.parseInt(p);
        			Population populationFrame = new Population(zipCode, population);
        			populationList.add(populationFrame);
            	}		
            }	
    		
            buf.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    return populationList;
    }	
	
//	public static void main(String[] args) {
//		TXTReader t = new TXTReader("population.txt");
//		ArrayList<Population> populationList = t.open();
//		for (int i = 0; i < populationList.size(); i++) {
//			String population = populationList.get(i).getPopulation();
//			String zip_code = populationList.get(i).getZipCode();
//			System.out.println(zip_code + " " + population);
//		}
//	}
}    
	

