package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import edu.upenn.cit594.data.Population;

/** This class is an processor that process the population data
 *  there is one variable: populationList, which contains a list of population
 *  there are two functions in this processor: getPopulationByZip and getTotalPopulation
 */
public class PopulationProcessor {

	ArrayList<Object> populationList;

	/**
	 * Constructor of PopulationProcessor
	 */
	public PopulationProcessor(ArrayList<Object> populationList) {
		this.populationList = populationList;
	}

	/**
	 * Get each zone's population by zip code
	 *
	 * @return a map of population number, zip code as key
	 */
	public Map<Object, Integer>  getPopulationByZip() {
		List<Population> pList = new ArrayList<>(); 
		for (int i=0; i < populationList.size(); i++) {
			pList.add((Population) populationList.get(i));
		}
		 
		Map<Object, Integer> populationByZip = new HashMap<>();
		
		populationByZip =  pList.stream()
                .collect(Collectors.groupingBy(Population -> Population.zip_code,
                        Collectors.summingInt(Population -> Population.population)));

		return populationByZip;

	}

	/**
	 * @return total population of all zip code
	 */
	public int getTotalPopulation(){
		int totalPopulation = 0;
		Population tempP;
		for (int i = 0; i < populationList.size(); i++){
			tempP = (Population)populationList.get(i);
			totalPopulation += tempP.getPopulation();
		}
		return totalPopulation;
	}
}
