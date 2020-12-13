package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PropertyProcessor {

    ArrayList<Object> properties;
    Map<Object, Integer> populations;
    String zip;

    public PropertyProcessor(ArrayList<Object> properties, Map<Object, Integer> populations, String zip) {
        this.zip = zip;
        this.properties = properties;
        this.populations = populations;
    }

    public int GetAveValue(AverageCalculator aveCalculator){

        return aveCalculator.GetAveValue(properties, zip);

    }

    public Map<String, Double> GetValuePerCapita(PerCapitaCalculator perCapitaCalculator){

        return perCapitaCalculator.GetValuePerCapita(properties, populations);

    }
}
