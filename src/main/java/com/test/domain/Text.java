package com.test.domain;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@ToString
@XmlRootElement
public class Text {
    private List<Sentence> sentence = new ArrayList<Sentence>();

    public List<Sentence> getSentence() {
        return sentence;
    }

    public void setSentence(List<Sentence> sentence) {
        this.sentence = sentence;
    }
}
