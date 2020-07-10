package com.company;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.TreeSet;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sentence {
    private TreeSet<String> word = new TreeSet<>();

    public Sentence() {}

    public void addToken(String token){
        word.add(token);
    }

    public TreeSet<String> getWord() {
        return word;
    }
}
