package com.test.util;
import com.test.domain.Sentence;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
/**
 * Util is used for reading the text data and parsing in to for XML content.
 */

public class Util {
    private static Logger logger = Logger.getLogger(Util.class.getName());
    public static List<Sentence> getOrderedWordList(String fileName)  {
        List<Sentence> sentences = new ArrayList<Sentence>(16);
        try {
            List<String> wordList = getWordList(getFileContent(fileName));
            for (int i = 0; i < wordList.size(); i++) {
                String line = wordList.get(i).trim();
                if (line.equals("")) {
                    continue;
                }
                String[] result = line.split("\\s+|\\\t|,|\r\n|\\)|\\(|-|:");
                List<String> tempList = Arrays.asList(result);
                Collections.sort(tempList, new WordComparator());
                Sentence sentence = new Sentence();
                for (int j = 0; j < tempList.size(); j++) {
                    String word = tempList.get(j);
                    if (word.equals("")) {
                        continue;
                    }
                    sentence.addWord(word);
                }
                sentences.add(sentence);
            }
        } catch (Exception e) {
            logger.info("Internal Error : " + e.getMessage());
            return null;
        }
        return sentences;
    }

    public static List<String[]> getOrderedWordArray(String fileName)  {
        List<String[]> sentences = new ArrayList<String[]>(16);
        try {
            List<String> wordList = getWordList(getFileContent(fileName));
            for (int i = 0; i < wordList.size(); i++) {
                String line = wordList.get(i).trim();
                if (line.equals("")) {
                    continue;
                }
                String[] result = line.split("\\s+|\\\t|,|\r\n|\\)|\\(|-|:");
                List<String> tempList = Arrays.asList(result);
                Collections.sort(tempList, new WordComparator());
                List<String> words = new ArrayList<>();
                words.add("Sentence " + (i+1));
                for (int j = 0; j < tempList.size(); j++) {
                    String word = tempList.get(j);
                    if (word.equals("")) {
                        continue;
                    }
                    words.add(word);
                }

                sentences.add(words.toArray(new String[0]));
            }
        } catch (Exception e) {
            logger.info("Internal Error : " + e.getMessage());
            return null;
        }
        return sentences;
    }

    public static List<String> getWordList(String content) {
        List<String> asList = new ArrayList<>();
        try {
            if (!content.isEmpty()) {
                String[] result = content.split("^(Mr\\.)|!|\\?|\\.");
                for (int i = 0; i < result.length; i++) {
                    System.out.println(result[i]);
                }
                asList = Arrays.asList(result);
            }
        } catch (Exception e) {
            logger.info("Got an exception. " + e.getMessage());
            e.printStackTrace();
        }
        return asList;
    }

    public static String getFileContent(String filePath) {
        String str = null;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            str = new String(bytes);
        } catch (IOException io) {
            logger.info("Unable to open file. " + io.getMessage());
            io.printStackTrace();
        }
        return str;
    }
}
