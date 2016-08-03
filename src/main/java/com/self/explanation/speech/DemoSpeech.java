package com.self.explanation.speech;

import com.self.explanation.readerbench.*;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*************************************************************************
 * ADOBE CONFIDENTIAL
 * ___________________
 *
 *  Copyright 2016 Adobe Systems Incorporated
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and are protected by all applicable intellectual property
 * laws, including trade secret and copyright laws.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 **************************************************************************/

@Component
public class DemoSpeech {



    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ReaderBenchClient readerBenchClient;

    private Story story = new Story();
    private List<Explanation> explanationList = new ArrayList<Explanation>();

    public String recognize(String filename) throws IOException {

        Configuration configuration = new Configuration();

        configuration
                .setAcousticModelPath(applicationContext.getResource("cmusphinx-fr-ptm-5.2/").getURI().getPath());
        configuration
                .setDictionaryPath(applicationContext.getResource("fr-lstm.dic").getURI().getPath());
        configuration
                .setLanguageModelPath(applicationContext.getResource("fr-small.lm.bin").getURI().getPath());

        configuration.setSampleRate(22050);

        StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(
                configuration);

        InputStream stream = new FileInputStream(new File(filename));

        recognizer.startRecognition(stream);
        SpeechResult result;
        String textResult = "";
        while ((result = recognizer.getResult()) != null) {
            textResult += result.getHypothesis();
            System.out.format("Hypothesis: %s\n", result.getHypothesis());
        }
        recognizer.stopRecognition();
        System.out.format("Result: %s\n", textResult);

        return textResult;
    }


    public void recognizeExplanations(String audioExplanationPath) throws IOException, ParserConfigurationException, SAXException {
        story.parseXMLStoryFile(applicationContext.getResource("Matilda.xml").getFile().getPath());
        File explanationFolder = new File(applicationContext.getResource(audioExplanationPath).getURI().getPath());
        for(File file: explanationFolder.listFiles()){
            Explanation explanation = new Explanation();
            String textExplanation = recognize(file.getPath());
            explanation.setSummary(textExplanation);
            explanation.setName(file.getName());
            explanationList.add(explanation);
        }
    }

    public void evaluateResults() throws IOException {
        for(Explanation explanation: explanationList){
            SelfExplanationResult result = readerBenchClient.selfExplanation(new SelfExplanationPayload(story.getContent(), explanation.getSummary(),
                    "resources/config/LSA/lemonde_fr", "resources/config/LDA/lemonde_fr", "French", true, true));

            System.out.println("Computing for " + explanation.getName());

            explanation.setParaphrase(result.getData().getStrategies().get(0).getScore());
            explanation.setCausality(result.getData().getStrategies().get(1).getScore());
            explanation.setTextBasedInferences(result.getData().getStrategies().get(2).getScore());
            explanation.setBridging(result.getData().getStrategies().get(3).getScore());
            explanation.setInferredKnowledge(result.getData().getStrategies().get(4).getScore());
            explanation.setMetacognition(result.getData().getStrategies().get(5).getScore());
            explanation.setSelfExplanationColored(result.getData().getSelfExplanationColored());
        }
    }

    public void exportResultsToCsv(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
        bw.write("name, paraphrase, causality, textInference, bridging, inferredKnowledge, metacognition");
        for(Explanation explanation: explanationList){
            bw.write(explanation.getName() + "," + explanation.getParaphrase() + "," + explanation.getCausality() + "," + explanation.getTextBasedInferences()
                    + "," + explanation.getBridging() + "," + explanation.getInferredKnowledge() + "," + explanation.getMetacognition()+
                    "," + explanation.getSelfExplanationColored() + "\n");
        }
        bw.close();
    }

    public void exportResultsToCsvOneStuff(String filename, Explanation explanation) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
//        bw.write("name, paraphrase, causality, textInference, bridging, inferredKnowledge, metacognition\n");
//        for(Explanation explanation: explanationList){
        bw.write(explanation.getName() + "," + explanation.getParaphrase() + "," + explanation.getCausality() + "," + explanation.getTextBasedInferences()
                + "," + explanation.getBridging() + "," + explanation.getInferredKnowledge() + "," + explanation.getMetacognition()+
                "," + explanation.getSelfExplanationColored() + "\n");
//        }
        bw.close();
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ReaderBenchClient getReaderBenchClient() {
        return readerBenchClient;
    }

    public void setReaderBenchClient(ReaderBenchClient readerBenchClient) {
        this.readerBenchClient = readerBenchClient;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public List<Explanation> getExplanationList() {
        return explanationList;
    }

    public void setExplanationList(List<Explanation> explanationList) {
        this.explanationList = explanationList;
    }
}
