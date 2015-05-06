package com.green.rukh.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  not used anywhere
 * @author Rajan Prasad Upadhyay
 */
public class Logger {

    public static enum LogMode {

        CONSOLE,
        FILE,
        BOTH
    }

    public static enum Severity {

        INFO,
        DEBUG,
        ERROR,
        CRITICAL
    }

    public static Severity severity = Severity.INFO;

    public static LogMode mode = LogMode.BOTH;

    public static String fileName = "log.txt";

    static {
        File file = new File(fileName);
        try {
            file.delete();
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void log(Severity severity, String message) {
        if (Logger.mode == LogMode.BOTH) {
            logFile(severity, message);
            logConsole(severity, message);
        } else if (Logger.mode == LogMode.CONSOLE) {
            logConsole(severity, message);
        } else {
            logFile(severity, message);
        }
    }

    private static void logFile(Severity severity, String message) {
        File file = new File(fileName);
        
        try {
            //create a new file if it does not 
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true);
            PrintWriter out = new PrintWriter(new BufferedWriter(writer));
            out.println(message);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void logConsole(Severity severity, String message) {
        if (severity == Severity.ERROR) {
            System.err.println(message);;
        } else {
            System.out.println(message);
        }
    }
    
    public static void main(String[] args) {
        Logger.log(Logger.Severity.INFO, "This is a test");
        Logger.log(Logger.Severity.INFO, "And so is this.");
    }
}
