package edu.upenn.cit594.processor;
import java.util.ArrayList;
import java.util.Map;

/** This class is an processor that process the three data set: properties, populations, and violations
 *  then evaluate which zip code has the most market value / violation fine ratio.
 *  the zip code has the least ratio will be the best zip.
 */
public class EvaluateZIPProcessor {
    ArrayList<Object> properties;
    Map<Object, Integer> populations;
    ArrayList<Object> violations;

    public EvaluateZIPProcessor(ArrayList<Object> properties, Map<Object, Integer> populations, ArrayList<Object> violations){
        this.properties = properties;
        this.populations = populations;
        this.violations = violations;
    }

    /**
     * Evaluate the best zone
     *
     * @return the best zone's zip code
     */
    public String getBestZip(){

        PropertyProcessor pp = new PropertyProcessor(properties, populations);
        ViolationProcessor violationProcessor = new ViolationProcessor(violations, populations);
        Map<String, Double> finePerCapita = violationProcessor.getFinePerCapita();
        double bestRatio = 0.0;
        String bestZIP = "";
        for (String key : finePerCapita.keySet()){
            if (pp.getValuePerCapita(key) >= 0){
                //calculate the ratio by market value per capita divide by fine per capita
                double tempRatio = pp.getValuePerCapita(key)/ finePerCapita.get(key);
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
