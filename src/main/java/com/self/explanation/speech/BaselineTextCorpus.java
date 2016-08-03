
package com.self.explanation.speech;

import com.self.explanation.readerbench.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BaselineTextCorpus {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ReaderBenchClient readerBenchClient;

    private Story story;
    private List<Explanation> explanationList;


    public BaselineTextCorpus(){

    }

    public void parseXml(String selfExplanationsTextPath){
        story = new Story();
        explanationList = new ArrayList<Explanation>();
        try {
            story.parseXMLStoryFile(applicationContext.getResource("Matilda.xml").getFile().getPath());

            File explanationFolder = new File(applicationContext.getResource(selfExplanationsTextPath).getURI().getPath());
            for(File file: explanationFolder.listFiles()){
                Explanation explanation = new Explanation();
                explanation.parseXMLStoryFile(file.getPath());
                explanationList.add(explanation);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void evaluateResults(){
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
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write("name, paraphrase, causality, textInference, bridging, inferredKnowledge, metacognition");
        for(Explanation explanation: explanationList){
            bw.write(explanation.getName() + "," + explanation.getParaphrase() + "," + explanation.getCausality() + "," + explanation.getTextBasedInferences()
            + "," + explanation.getBridging() + "," + explanation.getInferredKnowledge() + "," + explanation.getMetacognition()+
                     "," + explanation.getSelfExplanationColored() + "\n");
        }
        bw.close();
    }

    public void exportTextToFile(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for(Explanation explanation: explanationList){
            bw.write("<s> " +  explanation.getSummary() + " </s>\n");
        }
        bw.close();
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
