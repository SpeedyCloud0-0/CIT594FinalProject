package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Violation;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ValuePerCapitaCalculator implements PerCapitaCalculator{

    public Map<String, Double> getValuePerCapita(ArrayList<Object> properties, Map<Object, Integer> populations) {
        Map<String, Double> valuePerCapita = new HashMap<>();
        Map<String, Double> valueByZip = new HashMap<>();
        Property tempP;
        String zip;
        double marketValue;

        for (int i = 0; i < properties.size(); i++){
            tempP = (Property) properties.get(i);
            zip = tempP.getZipCode();
            marketValue = tempP.getMV();
            if (valueByZip.containsKey(zip)){
                valueByZip.put(zip, valueByZip.get(zip) + marketValue);
            }else{
                valueByZip.put(zip, marketValue);
            }

        }
        //calculate market value per capita
        valueByZip.forEach((key, value) -> {
            if(populations.containsKey(key) && (valueByZip.get(key)!= 0)){
                valuePerCapita.put(key, value/populations.get(key));
            }
        });

        return valuePerCapita;
    }


}
