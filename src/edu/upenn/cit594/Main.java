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
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.EvaluateZIPProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.PropertyProcessor;
import edu.upenn.cit594.processor.ViolationProcessor;
import edu.upenn.cit594.ui.UserInterface;

public class Main {

    public static String logFileName = "";

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

        //check the log file status and write the start time to it
        logFileName = args[4];
        File logFile = new File(logFileName);
        logFile.createNewFile();
        Logger l = Logger.getInstance();
        l.log(String.format("%d %s %s %s %s %s", System.currentTimeMillis(),
                violationFileFormat, violationFileName, propertyFileName, populationFileName, logFileName));


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

        //Prepare population data
        TXTReader t = new TXTReader(populationFileName);
        ArrayList<Population> pList = t.open();
        l.log(String.format("%d %s", System.currentTimeMillis(),propertyFileName));
        PopulationProcessor pp = new PopulationProcessor(pList);
        Map<Object, Integer> ppList = pp.getPopulationByZip();

        //Prepare properties data
        CSVHReader hr = new CSVHReader(propertyFileName);
        ArrayList<Object> propertyList = hr.open();
        l.log(String.format("%d %s", System.currentTimeMillis(),propertyFileName));

        //Prepare violations data
        ArrayList<Object> violationList;
        if (violationFileFormat.compareTo("csv") == 0){
            CSVReader cr = new CSVReader(violationFileName);
            violationList = cr.open();
        }else{
            JParser jp = new JParser(violationFileName);
            violationList = jp.open();
        }
        l.log(String.format("%d %s", System.currentTimeMillis(),violationFileName));

        UserInterface ui = new UserInterface();
        int action = 1;

        while(true){
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
                l.log(String.format("%d %s", System.currentTimeMillis(),input));
                if (action == 0){
                    break;
                }
            }else{
                System.out.println("Error 4: Input invalid.");
                break;
            }


            String zip = "";
            PropertyProcessor ptp = null;

            if (action == 3 || action == 4 || action == 5){
                System.out.println("Enter the zip code to calculate");
                zip = s.nextLine();
                l.log(String.format("%d %s", System.currentTimeMillis(),zip));
                ptp = new PropertyProcessor(propertyList, ppList);
            }

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
                    ui.writeToConsole(ptp.getAveValue(zip));
                    break;
                }
                //4. Average Total Livable Area
                case 4: {
                    ui.writeToConsole(ptp.getAveArea(zip));
                    break;
                }
                //5. Total Residential market Value Per Capita
                case 5: {
                    ui.writeToConsole(ptp.getValuePerCapita(zip));
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
}
