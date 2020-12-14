package edu.upenn.cit594.logging;
import java.io.File;
import java.io.PrintWriter;

public class Logger {
    private PrintWriter out;

    private Logger(String fileName) {
        try {
            out = new PrintWriter(new File(fileName));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Logger instance = new Logger("log.txt");

    public static Logger getInstance(){
        return instance;
    }

    public void log(String msg){
        out.println(msg);
        out.flush();
    }
}

