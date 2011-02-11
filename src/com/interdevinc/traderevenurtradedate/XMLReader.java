/*
 * DatToCSV
 * Created on Jan 06, 2010, 10:11 AM
 * @author Matthew Weppler
 * copyright 2010 InterDev Inc.
 *
 */
package com.interdevinc.traderevenurtradedate;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {

    private Document doc;
    private String[] appSettings = new String[7];
    private String[] dbSettings = new String[4];

    public XMLReader(String fileLocation) {
        try {
            File configFile = new File(fileLocation);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(configFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAppSettings() {
        NodeList nodeList = doc.getElementsByTagName("appSettings");
        for (int s = 0; s < nodeList.getLength(); ++s) {
            Node nodeItem = nodeList.item(s);
            if (nodeItem.getNodeType() == Node.ELEMENT_NODE) {
                Element eleItem = (Element) nodeItem;

                NodeList fileDirEleList = eleItem.getElementsByTagName("fileDir");
                Element fileDirEle = (Element) fileDirEleList.item(0);
                NodeList fileDir = fileDirEle.getChildNodes();
                appSettings[0] = ((Node) fileDir.item(0)).getNodeValue();

                NodeList datFileNameEleList = eleItem.getElementsByTagName("datFileName");
                Element datFileNameEle = (Element) datFileNameEleList.item(0);
                NodeList datFileName = datFileNameEle.getChildNodes();
                appSettings[1] = ((Node) datFileName.item(0)).getNodeValue();

                NodeList outputFileNameEleList = eleItem.getElementsByTagName("outputFileName");
                Element outputFileNameEle = (Element) outputFileNameEleList.item(0);
                NodeList outputFileName = outputFileNameEle.getChildNodes();
                appSettings[2] = ((Node) outputFileName.item(0)).getNodeValue();

                NodeList zipFileBaseNameEleList = eleItem.getElementsByTagName("zipFileBaseName");
                Element zipFileBaseNameEle = (Element) zipFileBaseNameEleList.item(0);
                NodeList zipFileBaseName = zipFileBaseNameEle.getChildNodes();
                appSettings[3] = ((Node) zipFileBaseName.item(0)).getNodeValue();

                NodeList backupLocation1EleList = eleItem.getElementsByTagName("backupLocation1");
                Element backupLocation1Ele = (Element) backupLocation1EleList.item(0);
                NodeList backupLocation1 = backupLocation1Ele.getChildNodes();
                appSettings[4] = ((Node) backupLocation1.item(0)).getNodeValue();

                NodeList backupLocation2EleList = eleItem.getElementsByTagName("backupLocation2");
                Element backupLocation2Ele = (Element) backupLocation2EleList.item(0);
                NodeList backupLocation2 = backupLocation2Ele.getChildNodes();
                appSettings[5] = ((Node) backupLocation2.item(0)).getNodeValue();

                NodeList yesterdaysDatEleList = eleItem.getElementsByTagName("yesterdaysDat");
                Element yesterdaysDatEle = (Element) yesterdaysDatEleList.item(0);
                NodeList yesterdaysDat = yesterdaysDatEle.getChildNodes();
                appSettings[6] = ((Node) yesterdaysDat.item(0)).getNodeValue();

//                System.out.println("Root element " + doc.getDocumentElement().getNodeName());
//                System.out.println("Application Settings: ");
//                System.out.println("File Directory: " + ((Node) fileDir.item(0)).getNodeValue());
//                System.out.println("Dat File Name: " + ((Node) datFileName.item(0)).getNodeValue());
//                System.out.println("Output File Name: " + ((Node) outputFileName.item(0)).getNodeValue());
//                System.out.println("Zip File Base Name: " + ((Node) zipFileBaseName.item(0)).getNodeValue());
//                System.out.println("Backup Location 1: " + ((Node) backupLocation1.item(0)).getNodeValue());
//                System.out.println("Backup Location 2: " + ((Node) backupLocation2.item(0)).getNodeValue());
//                System.out.println("Previous Days Directory: " + ((Node) yesterdaysDat.item(0)).getNodeValue());

            }

        }

    }

    public String[] getAppSettings() {
        return this.appSettings;
    }

    public void setDBSettings() {
        NodeList nodeList = doc.getElementsByTagName("dbSettings");
        for (int s = 0; s < nodeList.getLength(); ++s) {
            Node nodeItem = nodeList.item(s);
            if (nodeItem.getNodeType() == Node.ELEMENT_NODE) {
                Element eleItem = (Element) nodeItem;

                NodeList hostEleList = eleItem.getElementsByTagName("host");
                Element hostEle = (Element) hostEleList.item(0);
                NodeList host = hostEle.getChildNodes();
                dbSettings[0] = ((Node) host.item(0)).getNodeValue();

                NodeList databaseEleList = eleItem.getElementsByTagName("database");
                Element databaseEle = (Element) databaseEleList.item(0);
                NodeList database = databaseEle.getChildNodes();
                dbSettings[1] = ((Node) database.item(0)).getNodeValue();

                NodeList usernameEleList = eleItem.getElementsByTagName("username");
                Element usernameEle = (Element) usernameEleList.item(0);
                NodeList username = usernameEle.getChildNodes();
                dbSettings[2] = ((Node) username.item(0)).getNodeValue();

                NodeList passwordEleList = eleItem.getElementsByTagName("password");
                Element passwordEle = (Element) passwordEleList.item(0);
                NodeList password = passwordEle.getChildNodes();
                dbSettings[3] = ((Node) password.item(0)).getNodeValue();

//                System.out.println("Root element " + doc.getDocumentElement().getNodeName());
//                System.out.println("Database Settings: ");
//                System.out.println("Host: " + ((Node) host.item(0)).getNodeValue());
//                System.out.println("Database: " + ((Node) database.item(0)).getNodeValue());
//                System.out.println("Username: " + ((Node) username.item(0)).getNodeValue());
//                System.out.println("Password: " + ((Node) password.item(0)).getNodeValue());


            }
        }
    }

    public String[] getDBSettings() {
        return this.dbSettings;
    }
}
