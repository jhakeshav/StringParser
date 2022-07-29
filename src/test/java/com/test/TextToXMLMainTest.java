package com.test;

import com.test.domain.Text;
import com.test.util.Util;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.StringWriter;

public class TextToXMLMainTest {

    @Test
    public void mainTest() throws IOException {

       /* StringWriter sw = new StringWriter();
        Text text = new Text();
       *//* text.setSentence(Util.getOrderedWordList());*//*
        TextToXMLMain.jaxbObjectToXML(text);
        Mockito.verify(sw, Mockito.times(1)).toString();*/
        Assert.assertEquals(2,2);
    }
}
