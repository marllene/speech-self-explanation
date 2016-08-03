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

package com.self.explanation.readerbench;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@Component
public class Explanation {

    private String title;
    private String name;
    private String summary;

    private float paraphrase;
    private float causality;
    private float textBasedInferences;
    private float bridging;
    private float inferredKnowledge;
    private float metacognition;
    private String selfExplanationColored;

    public void parseXMLStoryFile(String filename) throws ParserConfigurationException, IOException, SAXException {
        summary = "";
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File(filename));
        xmlDocument.getDocumentElement().normalize();

        name = xmlDocument.getElementsByTagName("author").item(0).getTextContent();

        NodeList nodeList = xmlDocument.getElementsByTagName("summary_body");
        for(int i = 0; i < nodeList.getLength(); i++){
            summary += nodeList.item(i).getChildNodes().item(1).getTextContent();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public float getParaphrase() {
        return paraphrase;
    }

    public void setParaphrase(float paraphrase) {
        this.paraphrase = paraphrase;
    }

    public float getCausality() {
        return causality;
    }

    public void setCausality(float causality) {
        this.causality = causality;
    }

    public float getTextBasedInferences() {
        return textBasedInferences;
    }

    public void setTextBasedInferences(float textBasedInferences) {
        this.textBasedInferences = textBasedInferences;
    }

    public float getBridging() {
        return bridging;
    }

    public void setBridging(float bridging) {
        this.bridging = bridging;
    }

    public float getInferredKnowledge() {
        return inferredKnowledge;
    }

    public void setInferredKnowledge(float inferredKnowledge) {
        this.inferredKnowledge = inferredKnowledge;
    }

    public float getMetacognition() {
        return metacognition;
    }

    public void setMetacognition(float metacognition) {
        this.metacognition = metacognition;
    }

    public String getSelfExplanationColored() {
        return selfExplanationColored;
    }

    public void setSelfExplanationColored(String selfExplanationColored) {
        this.selfExplanationColored = selfExplanationColored;
    }
}
