package edu.upenn.cit594.data;

public class Property {

	String market_value;
	String total_livable_area;
	String zip_code;
	
	public Property(String market_value, String total_livable_area, String zip_code) {
		this.market_value = market_value;
		this.total_livable_area = total_livable_area;
		this.zip_code = zip_code;
	}
	
	public String getMarketValue() {
		return market_value;
	}
	
	public double getMV() {
		if (market_value != null && market_value.length() > 0) {
	       try {
	          return Double.parseDouble(market_value);
	       } catch(Exception e) {
	          return -1;   
	       }
		}
		else return 0;
	}
	
	public String getTotalLivableArea() {
		return total_livable_area;
	}
	
	public double getArea() {
		if (total_livable_area != null && total_livable_area.length() > 0) {
	       try {
	          return Double.parseDouble(total_livable_area);
	       } catch(Exception e) {
	          return -1;   
	       }
		}
		else return 0;
	}
	
	public String getZipCode() {
		return zip_code;
	}
	
	
}
