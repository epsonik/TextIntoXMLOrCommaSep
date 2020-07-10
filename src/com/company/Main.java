package com.company;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Main {
    public static void main(String[] args) throws IOException, JAXBException {
        System.setProperty("javax.xml.bind.JAXBContextFactory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        String inputFileName = "";
        String outputXMLFileName = "";
        String outputCSVFileName = "";
        if (0 < args.length) {
            inputFileName = args[0];
            outputXMLFileName = args[1];
            outputCSVFileName = args[2];
        }
        ParseText parseText = new ParseText(inputFileName);
        Text sentences = new Text();
        sentences.setSentence(parseText.getSentencesMap());
        JAXBContext jaxbContext = JAXBContext.newInstance(Text.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(sentences, new File(outputXMLFileName));
        parseText.givenDataArray_whenConvertToCSV_thenOutputCreated( outputCSVFileName);

    }

}
