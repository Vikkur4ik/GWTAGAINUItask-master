package server;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    private File file = null;

    public XMLParser() {
        ClassLoader classLoader = getClass().getClassLoader();
        this.file = new File(classLoader.getResource("Buses.xml").getFile());
    }

    public List<List<String>> Parse() {

        List<List<String>> busList = new ArrayList<>();

        try {
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
            ClassLoader classLoader = getClass().getClassLoader();
            this.file = new File(classLoader.getResource("Buses.xml").getFile());
            Document document = dBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("bus");

            for (int i = 0; i<nodeList.getLength(); i++) {
                Node bus = nodeList.item(i);
                Element element = (Element) bus;
                String number = element.getElementsByTagName("number").item(0).getTextContent();
                String from = element.getElementsByTagName("from").item(0).getTextContent();
                String to = element.getElementsByTagName("to").item(0).getTextContent();
                String time = element.getElementsByTagName("time").item(0).getTextContent();
                List<String> oneBus = new ArrayList<String>();
                oneBus.add(number);
                oneBus.add(from);
                oneBus.add(to);
                oneBus.add(time);
                busList.add(oneBus);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return busList;

    }




}
