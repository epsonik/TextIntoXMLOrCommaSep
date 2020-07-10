package com.company;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Text {
    private Set<Sentence> sentence = new HashSet<>();

    public void setSentence(Map<Sentence, Integer> sentence) {
        this.sentence = sentence.keySet();
    }
}
