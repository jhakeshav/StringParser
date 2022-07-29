package com.test.domain;
import lombok.ToString;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@ToString
@XmlType()
public class Sentence {
    List<String> word = new ArrayList<>();

    public List<String> getWord() {
        return word;
    }

    public void setWord(List<String> word) {
        this.word = word;
    }

    public void addWord(String word) {
        this.word.add(word);
    }
}
