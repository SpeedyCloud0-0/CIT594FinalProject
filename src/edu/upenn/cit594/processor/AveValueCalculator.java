package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;
import java.util.ArrayList;

/** This class is the calculator class that implements average calculator interface
 *  It calculate the average market value in a specified zip code zone
 */
public class AveValueCalculator implements AverageCalculator{

    /**
     * Calculate the average value of the market value
     *
     * @param properties, zip
     * @return the average value of the market value in this zip
     */
    public int getAverage(ArrayList<Object> properties, String zip){
        double totalValue = 0;
        int homes = 0;
        int averageValue;
        Property tempP;

        for (int i = 0; i < properties.size(); i++) {
            tempP = (Property)properties.get(i);
            if (tempP.getZipCode().compareTo(zip) == 0) {
                totalValue += tempP.getMV();
                homes++;
            }
        }

        if (homes == 0) {
            averageValue = 0;
        } else {
            averageValue = (int) totalValue / homes;
        }

        return averageValue;
    }
}
