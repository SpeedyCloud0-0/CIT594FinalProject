package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Violation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ViolationProcessor {

    ArrayList<Object> violations;
    Map<Object, Integer> populations;

    public ViolationProcessor(ArrayList<Object> violations, Map<Object, Integer> populations){
        this.violations = violations;
        this.populations = populations;
    }

    //2. Total fines per capita
    public Map<String, Double> getFinePerCapita(){
        FinePerCapitaCalculator perCapitaCalculator = new FinePerCapitaCalculator();
        return perCapitaCalculator.getValuePerCapita(violations,populations);
    }


}
