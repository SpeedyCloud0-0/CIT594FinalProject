package edu.upenn.cit594.logging;

import edu.upenn.cit594.Main;
import java.io.File;
import java.io.PrintWriter;

/** This class is a logger class that using the singleton pattern
 *  It logs message into a log file
 */
public class Logger {
    private PrintWriter out;

    private Logger(String fileName) {
        try {
            out = new PrintWriter(new File(fileName));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Logger instance = new Logger(Main.logFileName);

    public static Logger getInstance(){
        return instance;
    }

    public void log(String msg){
        out.println(msg);
        out.flush();
    }
}

