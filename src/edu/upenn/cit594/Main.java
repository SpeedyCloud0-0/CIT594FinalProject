package edu.upenn.cit594;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.datamanagement.CSVHReader;
import edu.upenn.cit594.datamanagement.CSVReader;
import edu.upenn.cit594.datamanagement.JParser;
import edu.upenn.cit594.datamanagement.TXTReader;
import edu.upenn.cit594.processor.EvaluateZIPProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.PropertyProcessor;
import edu.upenn.cit594.processor.ViolationProcessor;
import edu.upenn.cit594.ui.UserInterface;

public class Main {

    public static void main(String[] args) throws IOException {

        //check the argument number
        if(args.length != 5){
            System.out.println("Error 1: Runtime arguments number is wrong.");
            return;
        }

        String violationFileFormat = args[0];
        String violationFileName = args[1];
        String propertyFileName = args[2];
        String populationFileName = args[3];
        String logFileName = args[4];
        int action;

        //check the violation input file format
        if(violationFileFormat.compareTo("csv") != 0 && violationFileFormat.compareTo("json") != 0){
            System.out.println("Error 2: File format is wrong.");
            return;
        }

        //check if all the files exist or can be opened
        File violationFile = new File(violationFileName);
        File propertyFile = new File(propertyFileName);
        File populationFile = new File(populationFileName);
        if (!(violationFile.exists() && violationFile.canRead())){
            System.out.println("Error 3: Cannot find or read from violation file.");
        }
        if (!(propertyFile.exists() && propertyFile.canRead())){
            System.out.println("Error 3: Cannot find or read from property file.");
        }
        if (!(populationFile.exists() && populationFile.canRead())){
            System.out.println("Error 3: Cannot find or read from population file.");
        }

        //prompt the user to specify the action
        System.out.print("Please specify which action you want to execute: " +
                "\n0: Exit\n1: Total population\n2: Total parking fines per capita\n" +
                "3: Average market value\n4: Average livable area\n5: Total residential market value per capita\n" +
                "6: The best ZIP code with high market value and low parking violations\n");

        //Get input from user and check it's validation
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        if (Pattern.matches("[0-6]", input)){
            action = Integer.valueOf(input);
            if (action == 0){return;}
        }else{
            System.out.println("Error 4: Input invalid.");
            return;
        }

        //Prepare population data
        TXTReader t = new TXTReader("population.txt");
        ArrayList<Population> pList = t.open();
        PopulationProcessor pp = new PopulationProcessor(pList);
        Map<Object, Integer> ppList = pp.getPopulationByZip();

        //Prepare properties data
        CSVHReader hr = new CSVHReader("properties.csv");
        ArrayList<Object> propertyList = hr.open();

        //Prepare violations data
        CSVReader cr = new CSVReader("parking.csv");
        ArrayList<Object> violationList = cr.open();

        JParser jp = new JParser("parking.json");

        UserInterface ui = new UserInterface();

        switch (action){
            //1. Total population
            case 1: {
                ui.writeToConsole(pp.getTotalPopulation());
                break;
            }
            //2. Total fines per capita
            case 2: {
                ViolationProcessor vp = new ViolationProcessor(violationList, ppList);
                ui.writeToConsole(vp.getFinePerCapita());
                break;
            }
            //3. Average Market Value
            case 3: {
                System.out.println("Enter the zip code to calculate");
                String zip = s.nextLine();
                PropertyProcessor ptp = new PropertyProcessor(propertyList, ppList);
                ui.writeToConsole(ptp.getAveValue(zip));
                break;
            }
            //4. Average Total Livable Area
            case 4: {
                System.out.println("Enter the zip code to calculate");
                String zip = s.nextLine();
                PropertyProcessor ptp = new PropertyProcessor(propertyList, ppList);
                ui.writeToConsole(ptp.getAveArea(zip));
                break;
            }
            //5. Total Residential market Value Per Capita
            case 5: {
                PropertyProcessor ptp = new PropertyProcessor(propertyList, ppList);
                ui.writeToConsole(ptp.getValuePerCapita());
                break;
            }
            //6. Best Community with more value and less violation
            case 6: {
                EvaluateZIPProcessor ezp = new EvaluateZIPProcessor(propertyList, ppList, violationList);
                ui.writeToConsole(ezp.getBestZip());
                break;
            }
            default: break;

        }
    }
}
