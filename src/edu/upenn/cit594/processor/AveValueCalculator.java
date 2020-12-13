package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;

import java.util.ArrayList;

public class AveValueCalculator implements AverageCalculator{

    public int GetAveValue(ArrayList<Object> properties, String zip){
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
