package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.datamanagement.TXTReader;

public class PopulationProcessor {

	ArrayList<Population> populationList;
	
	public PopulationProcessor(ArrayList<Population> populationList) {
		this.populationList = populationList;
	}
	
	public Map<Object, Integer>  getPopulationByZip() {
		List<Population> pList = new ArrayList<>(); 
		for (int i=0; i < populationList.size(); i++) {
			pList.add(populationList.get(i));
		}
		 
		Map<Object, Integer> populationByZip = new HashMap<>();
		
		populationByZip =  pList.stream()
                .collect(Collectors.groupingBy(Population -> Population.zip_code,
                        Collectors.summingInt(Population -> Population.population)));

		return populationByZip;

	}

	public int getTotalPopulation(){
		int totalPopulation = 0;
		for (Population p : populationList){
			totalPopulation += p.getPopulation();
		}
		return totalPopulation;
	}
	
//	public static void main(String[] args) {
//		TXTReader t = new TXTReader("population.txt");
//		ArrayList<Population> pList = t.open();
//		PopulationProcessor pp = new PopulationProcessor(pList);
//		Map<Object, Integer>  ppList = pp.getPopulationByZip();
//		for (Entry<Object, Integer> entry : ppList.entrySet()) {
//		    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
//		}
//	}

}
