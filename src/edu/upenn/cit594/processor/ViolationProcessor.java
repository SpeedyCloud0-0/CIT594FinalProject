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

    public Map<String, Double> GetFinePerCapita(PerCapitaCalculator perCapitaCalculator){

        return perCapitaCalculator.GetValuePerCapita(violations,populations);
    }


}
