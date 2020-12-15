package edu.upenn.cit594.data;

/**
 * This is the violation object class
 * It has three variables: zipcode, fine and state
 */
public class Violation {

	String fine;
	String zip_code;
	String state;
	
	public Violation(String fine, String zip_code, String state) {
		this.fine = fine;
		this.zip_code = zip_code;
		this.state = state;
	}
	
	public String getFine() {
		return fine;
	}
	
	public int getFineVal() {
		int fineVal = Integer.parseInt(fine);
		return fineVal;
	}
	
	public String getZipCode() {
		return zip_code;
	}
	
	public String getState() {
		return state;
	}
	


	
}
