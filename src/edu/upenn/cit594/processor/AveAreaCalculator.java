package edu.upenn.cit594.processor;
import edu.upenn.cit594.data.Property;
import java.util.ArrayList;

/** This class is the calculator class that implements average calculator interface
 *  It calculate the average livable area in a specified zip code zone
 */
public class AveAreaCalculator implements AverageCalculator{

    /**
     * Calculate the average value of the livable area
     *
     * @param properties, zip
     * @return the average value of the livable area in this zip
     */
    public int getAverage(ArrayList<Object> properties, String zip){
        double totalArea = 0;
        int homes = 0;
        int averageArea;
        Property tempP;

        for (int i = 0; i < properties.size(); i++) {
            tempP = (Property) properties.get(i);
            if (tempP.getZipCode().compareTo(zip) == 0) {
                totalArea += tempP.getArea();
                homes++;
            }
        }

        if (homes == 0) {
            averageArea = 0;
        } else {
            averageArea = (int) totalArea / homes;
        }

        return averageArea;
    }

}
