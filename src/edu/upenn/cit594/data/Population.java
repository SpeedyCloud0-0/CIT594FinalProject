package edu.upenn.cit594.data;

public class Population {

	public String zip_code;
	public int population;
	
	public Population(String zip_code, int population) {
		this.zip_code = zip_code;
		this.population = population;
	}
	
	public String getZipCode() {
		return zip_code;
	}
	
	public int getPopulation() {
		return population;
	}
	
		
}
