package edu.upenn.cit594.ui;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserInterface {


    public UserInterface(){ }

    public void writeToConsole(int i){
        System.out.println(i);
    }

    public void writeToConsole(String s){
        System.out.println(s);
    }

    public void writeToConsole(Map<String, Double> map){
        map.forEach((key, value) -> {
            //truncate the result
            DecimalFormat df = new DecimalFormat("#.0000");
            df.setRoundingMode(RoundingMode.FLOOR);
            System.out.println(String.format("%s %s", key, df.format(value)));
        });
    }

    public void writeToLog(){

    }

}
