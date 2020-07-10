package com.company;


import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseText {

    private static String REGEX = "\\!|\\?|\\.";
    private static String REPLACE = "";
    private Map<Sentence, Integer> sentencesMap;

    public ParseText(String fileName) throws IOException {
        this.parseText(fileName);
    }

    private void parseText(String fileName) throws IOException {

        Integer sentCounter = 0;

        List<String> endSigns = Arrays.asList("!", ".", "?");

        this.sentencesMap = new LinkedHashMap<>();

        Sentence sentenceObj = new Sentence();
        try (BufferedReader fileBufferReader = new BufferedReader(new FileReader(fileName))) {
            String fileLineContent;
            Pattern p = Pattern.compile(REGEX);
            while ((fileLineContent = fileBufferReader.readLine()) != null) {
                StringTokenizer st1 = new StringTokenizer(fileLineContent);
                while (st1.hasMoreTokens()) {
                    String processingToken = st1.nextToken();
                    boolean checkIfStringContainsEndSign = endSigns.stream().anyMatch(processingToken::contains);
                    if (!checkIfStringContainsEndSign) {
                        sentenceObj.addToken(processingToken);
                    } else {
                        Matcher matcher = p.matcher(processingToken);
                        processingToken = matcher.replaceAll(REPLACE);
                        sentenceObj.addToken(processingToken);
                        sentencesMap.put(sentenceObj, sentCounter);
                        sentCounter++;
                        sentenceObj = new Sentence();
                    }

                }
            }
        }
    }

    public Map<Sentence, Integer> getSentencesMap() {
        return sentencesMap;
    }

    public String convertToCSV(Sentence data, Integer id, Integer longestSentenceSize) {
        String csvRow = "Sentence " +id+", "+String.join(", ", data.getWord());
        if(id.equals(0)){
            return createHeader(longestSentenceSize) + csvRow;
        }
        return csvRow;
    }
    private String createHeader(Integer longestSentenceSize){
        String header="";
        int i=1;
        while (i<=longestSentenceSize){
            header+="Word " + i +", ";
            i++;
        }
        return header + "\n";
    }
    public void givenDataArray_whenConvertToCSV_thenOutputCreated(String csvFileNAme) throws IOException {
        File csvOutputFile = new File( csvFileNAme);
        Integer longestSentenceSize = this.sentencesMap.keySet().stream().map(s->s.getWord()).mapToInt(Set::size).max().getAsInt();
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            this.sentencesMap.entrySet().stream().map(s-> convertToCSV(s.getKey(), s.getValue(), longestSentenceSize)).forEach(pw::println);
        }
    }
}
