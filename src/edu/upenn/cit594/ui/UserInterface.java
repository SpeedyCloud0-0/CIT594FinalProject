package edu.upenn.cit594.ui;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/** This is the user interface class that manage the process of writing info to console
 */
public class UserInterface {

    public UserInterface(){ }

    /**
     * Construct several functions to write different data types to console
     * Write int to console
     */
    public void writeToConsole(int i){
        System.out.println(i);
    }

    /**
     * Construct several functions to write different data types to console
     * Write String to console
     */
    public void writeToConsole(String s){
        System.out.println(s);
    }


    /**
     * Construct several functions to write different data types to console
     * Write map to console, sort it and then truncate the data before writing
     */
    public void writeToConsole(Map<String, Double> map){
        //sort the map
        TreeMap treeMap = new TreeMap<String, Double>(map);

        treeMap.forEach((key, value) -> {
            //truncate the result
            DecimalFormat df = new DecimalFormat("#0.0000");
            df.setRoundingMode(RoundingMode.FLOOR);
            System.out.println(String.format("%s %s", key, df.format(value)));
        });
    }

}
