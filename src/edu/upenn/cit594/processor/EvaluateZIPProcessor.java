package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class EvaluateZIPProcessor {
    ArrayList<Object> properties;
    Map<Object, Integer> populations;
    ArrayList<Object> violations;

    public EvaluateZIPProcessor(ArrayList<Object> properties, Map<Object, Integer> populations, ArrayList<Object> violations){
        this.properties = properties;
        this.populations = populations;
        this.violations = violations;
    }

    public String getBestZip(){

        ValuePerCapitaCalculator valueCalculator = new ValuePerCapitaCalculator();
        Map<String, Double> valuePerCapita = valueCalculator.getValuePerCapita(properties, populations);
        FinePerCapitaCalculator fineCalculator = new FinePerCapitaCalculator();
        Map<String, Double> finePerCapita = fineCalculator.getValuePerCapita(violations,populations);
        double bestRatio = 0.0;
        String bestZIP = "";
        for (String key : valuePerCapita.keySet()){
            if (finePerCapita.containsKey(key)){
                double tempRatio = valuePerCapita.get(key) / finePerCapita.get(key);
                if (tempRatio > bestRatio){
                    bestRatio = tempRatio;
                    bestZIP = key;
                }
            }
        }

        bestZIP = "Best ZIP is " + bestZIP;
        return bestZIP;

    }
}
