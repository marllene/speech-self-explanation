package com.self.explanation.speech;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MainSpeech {


    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpeechSelfExplanation.class);

//        BaselineTextCorpus corpus = context.getBean(BaselineTextCorpus.class);
//        corpus.parseXml("selfExplanations/Mathilda/CM2/");
//        corpus.exportTextToFile("self_explanation.txt");
//        corpus.evaluateResults();
//        corpus.exportResultsToCsv("MathildaBaseline3.csv");

        DemoSpeech speech = context.getBean(DemoSpeech.class);
        speech.recognizeExplanations("/wavs/Mathilda/1");
        speech.evaluateResults();
        speech.exportResultsToCsv("MathildaSpeechLSTM1.csv");
    }



}
