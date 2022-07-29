package com.test.util;
import java.util.Comparator;

/**
 * WordComparator is used for comparing two string objects, this is used for sorting Word objects
 */
public class WordComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.toLowerCase().compareTo(o2.toLowerCase());
    }
}
