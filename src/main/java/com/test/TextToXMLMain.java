package com.test;

import com.test.domain.Text;
import com.test.util.Util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
/**
 * TextToXMLMain is used for generating XML content format.
 */
public class TextToXMLMain {
    private static Logger logger = Logger.getLogger(TextToXMLMain.class.getName());

    public static void main(String[] args) {
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
            Text text = new Text();
            text.setSentence(Util.getOrderedWordList(fileInputAbsPath)); // if user wants to use small.in that can also be passed here.
            jaxbObjectToXML(text, fileOutputAbsPath);
            logger.info("File text generated successfully in xml format");
        } catch (Exception e) {
            logger.info("Got an exception. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void jaxbObjectToXML(Text t, String fileOutputAbsPath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Text.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(t, sw);
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
}