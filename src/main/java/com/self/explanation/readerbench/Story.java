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
public class Story {

    private String title;
    private String author;
    private String content;


    public void parseXMLStoryFile(String filename) throws ParserConfigurationException, IOException, SAXException {
        content = "";
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File(filename));
        xmlDocument.getDocumentElement().normalize();

        title = xmlDocument.getElementsByTagName("title").item(0).getTextContent();
        author = xmlDocument.getElementsByTagName("author").item(0).getTextContent();

        NodeList nodeList = xmlDocument.getElementsByTagName("p");
        for(int i = 0; i < nodeList.getLength(); i++){
            content += nodeList.item(i).getTextContent();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
