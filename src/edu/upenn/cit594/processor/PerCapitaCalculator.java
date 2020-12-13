package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Violation;

import java.util.ArrayList;
import java.util.Map;

public interface PerCapitaCalculator {

    public Map<String, Double> GetValuePerCapita(ArrayList<Object> objList, Map<Object, Integer> populations);

}
