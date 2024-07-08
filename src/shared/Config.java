package shared;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.IOException;

public class Config {
    private String ip;
    private int port;

    public Config() {
        loadConfig();
    }

    private void loadConfig() {
        try {
            File configFile = new File("config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(configFile);
            doc.getDocumentElement().normalize();

            NodeList serverList = doc.getElementsByTagName("server");
            Node serverNode = serverList.item(0);

            if (serverNode.getNodeType() == Node.ELEMENT_NODE) {
                Element serverElement = (Element) serverNode;
                ip = serverElement.getElementsByTagName("ip").item(0).getTextContent();
                port = Integer.parseInt(serverElement.getElementsByTagName("port").item(0).getTextContent());
            }
        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

}
