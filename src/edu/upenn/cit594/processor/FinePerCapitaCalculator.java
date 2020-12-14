package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Violation;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FinePerCapitaCalculator implements PerCapitaCalculator{

    public Map<String, Double> getValuePerCapita(ArrayList<Object> violations, Map<Object, Integer> populations) {
        Map<String, Double> finePerCapita = new HashMap<>();
        Map<String, Integer> fineByZip = new HashMap<>();
        Violation tempV;
        String zip;
        int fine;
        String state;
        for (int i = 0; i < violations.size(); i++){
            tempV = (Violation) violations.get(i);
            zip = tempV.getZipCode();
            fine = tempV.getFineVal();
            state = tempV.getState();
            //Check if the violation is located in PA
            if (state.compareTo("PA") == 0){
                //Add the violation into hashmap with zipcode as the key
                if (fineByZip.containsKey(zip)){
                    fineByZip.put(zip, fineByZip.get(zip)+fine);
                }else{
                    fineByZip.put(zip, fine);
                }
            }
        }
        //calculate the fine per capita
        fineByZip.forEach((key, value) -> {
            if(populations.containsKey(key) && (fineByZip.get(key)!= 0)){
                finePerCapita.put(key, value.doubleValue()/populations.get(key));
            }
        });

        return finePerCapita;
    }


}
