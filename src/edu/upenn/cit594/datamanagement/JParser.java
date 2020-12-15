package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Violation;


public class JParser implements Reader {
	
	String filename;
	
	/**
	 * Construct a reader to open a json type file
	 * 
	 * @param filename
	 */
	public JParser(String filename) {
	    this.filename = filename; 
	}
	
	/**
	 * Open a json file and save the contents into an arrayList
	 * 
	 * @return all parking violation content from input json file
	 */
	@Override
	public ArrayList<Object> open(){
		ArrayList<HashMap> jsonMap = new ArrayList<>();
		ArrayList<Object> violationList = new ArrayList<>();
		
		JSONParser parser = new JSONParser();
	
		try {
			JSONArray violation_array;	
			violation_array = (JSONArray)parser.parse(new FileReader(filename));
			HashMap<String, String> violation_content = new HashMap<String, String>();
			Iterator iter = violation_array.iterator();		
			while (iter.hasNext())
			{				
				JSONObject violation = (JSONObject) iter.next();
				for (Object x: violation.keySet()) {
					String key = x.toString();
					String value = violation.get(key).toString();
					violation_content.put(key, value);
					//System.out.println(key + " " + value);						
				}
				
				if ((violation_content.get("fine") != null) && (violation_content.get("zip_code").length() == 5) && (violation_content.get("state") != null)) {
					Violation violationFrame = new Violation(violation_content.get("fine"), violation_content.get("zip_code"), violation_content.get("state"));
					violationList.add(violationFrame);
				}
				//jsonMap.add(violation_content);
			}			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
	return violationList;
	}
	
//	public static void main(String[] args) {
//		JParser j = new JParser("parking.json");
//		ArrayList<Violation> violationList = j.open();
//		for (int i = 0; i < violationList.size(); i++) {
//			String fine = violationList.get(i).getFine();
//			String zip_code = violationList.get(i).getZipCode();
//			String state = violationList.get(i).getState();
//			System.out.println(fine + " " + zip_code + " " + state);
//		}
//	}
}
