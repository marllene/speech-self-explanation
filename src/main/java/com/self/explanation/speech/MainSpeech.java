package com.self.explanation.speech; /*************************************************************************
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


import com.self.explanation.readerbench.ReaderBenchClient;
import com.self.explanation.readerbench.SelfExplanationPayload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MainSpeech {


    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpeechSelfExplanation.class);

//        Story story = new Story();
//        story.parseXMLStoryFile(MainSpeech.class.getResource("Matilda.xml").getFile());

//        System.out.println(story.getTitle());
//        System.out.println(story.getAuthor());
//        System.out.println(story.getContent());

        ReaderBenchClient speech = context.getBean(ReaderBenchClient.class);
        speech.selfExplanation(new SelfExplanationPayload("lala", "Texttsts",
                "lsa", "lda", "lang", true, true));

    }



}
