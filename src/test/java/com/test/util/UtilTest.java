package com.test.util;

import com.test.domain.Sentence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class UtilTest {

    @Test
    public void getOrderedWordList()  {
        Assert.assertEquals(14, Util.getOrderedWordList(UtilTest.class.getClassLoader().getResource("small.in").getFile()).size());
    }

    @Test
    public void getOrderedWordListTest() {
        Sentence sentence = new Sentence();
        sentence.addWord("a");
        sentence.addWord("is");
        sentence.addWord("test");
        sentence.addWord("This");
        List<Sentence> list = new ArrayList<>();
        list.add(sentence);
        List<Sentence> orderedWordList = Util.getOrderedWordList(UtilTest.class.getClassLoader().getResource("wordlisttest.txt").getFile());

        Assert.assertNotEquals(list, orderedWordList);
    }

    @Test
    public void getFileContent()  {
        Assert.assertTrue(!Util.getFileContent(Util.class.getClassLoader().getResource("small.in").getFile()).isEmpty());
    }

}
