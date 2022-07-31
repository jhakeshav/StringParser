package com.test;

import com.opencsv.CSVWriter;
import com.test.domain.Text;
import com.test.util.Util;
import lombok.val;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * TextToXMLAndCSVMain is used for generating XML content format.
 */
public class TextToXMLAndCSVMain {
    private static Logger logger = Logger.getLogger(TextToXMLAndCSVMain.class.getName());

    public static void main(String[] args) throws IOException {
       if (args != null) {
            if (args.length != 3) {
                logger.warning("Invalid input, input or output file values are missing");
                return;
            }
        }
        String fileInputAbsPath = new File(args[0]).getAbsolutePath();
        logger.info("File Input Absolute Path: " + fileInputAbsPath);
        String fileOutputAbsPath = new File(args[1]).getAbsolutePath();
        logger.info("File Output Absolute Path: " + fileOutputAbsPath);
        String format = args[2];
        logger.info("File Output Absolute Path: " + fileOutputAbsPath);
        if (null !=format && format!="" && format.trim().toLowerCase().equals("xml")) {
            TextToXML(fileInputAbsPath, fileOutputAbsPath);
        } else if (null !=format && format!="" && format.trim().toLowerCase().equals("csv")) {
            WritingToCSV(fileInputAbsPath, fileOutputAbsPath);
        } else {
            logger.warning("Invalid input for format, only XML and CSV are accepted as valid input");
        }
    }

    public static void TextToXML(String fileInputAbsPath, String fileOutputAbsPath) {
        try {
            Text text = new Text();
            try {
                text.setSentence(Util.getOrderedWordList(fileInputAbsPath)); // if user wants to use small.in that can also be passed here.
                logger.info("File text generated successfully in xml format");
            } catch (Exception e) {
                logger.info("Got an exception. " + e.getMessage());
                e.printStackTrace();
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(Text.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(text, sw);
            String xmlContent = sw.toString();
            byte[] b = xmlContent.getBytes(StandardCharsets.UTF_8);
            System.out.println(xmlContent);
            logger.info("Writing file to location " + fileOutputAbsPath);
            Path path = Paths.get(fileOutputAbsPath);
            try {
                Files.write(path, b);
            } catch (IOException ex) {
                logger.info("Invalid Path " + ex.getMessage());
            }
        } catch (JAXBException e) {
            logger.info("Got an exception. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void WritingToCSV(String fileInputAbsPath, String fileOutputAbsPath) throws IOException {
        try {
            List<String> header = new ArrayList<String>();
            header.add(""); // adding the first column as empty value
            List<String[]> text = Util.getOrderedWordArray(fileInputAbsPath, header);
            System.out.println(header);
            WritingCSVContent(text, fileOutputAbsPath,header);
            logger.info("File text generated successfully in csv format");
        } catch (Exception e) {
            logger.info("Got an exception. " + e.getMessage());
            e.printStackTrace();
        }

    }
    public static void WritingCSVContent(List<String[]> t, String fileOutputAbsPath,List<String> header) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileOutputAbsPath))) {
            writer.writeNext(header.toArray(new String[0]));
            writer.writeAll(t);
        }
        catch (IOException ioe)
        {
            logger.info("Invalid File " + ioe.getMessage());
        }
    }
}