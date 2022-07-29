package com.test;
import com.opencsv.CSVWriter;
import com.test.util.Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * TextToCSVMain is used for generating CSV file
 */

public class TextToCSVMain {
    private static Logger logger = Logger.getLogger(TextToXMLMain.class.getName());

    public static void main(String[] args) throws IOException {
        if (args != null) {
            if (args.length != 2) {
                logger.warning("Invalid input, input or output file values are missing");
                return;
            }
        }
        String fileInputAbsPath = new File(args[0]).getAbsolutePath();
        logger.info("File Input Absolute Path: " + fileInputAbsPath);
        String fileOutputAbsPath = new File(args[1]).getAbsolutePath();
        logger.info("File Output Absolute Path: " + fileOutputAbsPath);
        try {
            //Text text = new Text();
            List<String[]> text = Util.getOrderedWordArray(fileInputAbsPath);
            WritingToCSV(text, fileOutputAbsPath);
            logger.info("File text generated successfully in csv format");
        } catch (Exception e) {
            logger.info("Got an exception. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void WritingToCSV(List<String[]> t, String fileOutputAbsPath) throws IOException
    {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileOutputAbsPath))) {
            writer.writeAll(t);
        }
    }
}