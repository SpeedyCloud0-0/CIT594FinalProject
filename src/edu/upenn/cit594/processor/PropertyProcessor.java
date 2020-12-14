package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.CSVHReader;
import edu.upenn.cit594.datamanagement.TXTReader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PropertyProcessor {

    ArrayList<Object> properties;
    Map<Object, Integer> populations;

    public PropertyProcessor(ArrayList<Object> properties, Map<Object, Integer> populations) {

        this.properties = properties;
        this.populations = populations;
    }
    //3. Average Market Value
    public int getAveValue(String zip){
        AveValueCalculator aveCalculator = new AveValueCalculator();
        return aveCalculator.getAverage(properties, zip);

    }

    //4. Average Total Livable Area
    public int getAveArea( String zip){
        AveAreaCalculator aveCalculator = new AveAreaCalculator();
        return aveCalculator.getAverage(properties, zip);

    }

    //5. Total Residential market Value Per Capita
    public Map<String, Double> getValuePerCapita(){
        ValuePerCapitaCalculator perCapitaCalculator = new ValuePerCapitaCalculator();
        return perCapitaCalculator.getValuePerCapita(properties, populations);

    }

}
