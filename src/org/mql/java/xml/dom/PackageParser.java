package org.mql.java.xml.dom;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.mql.java.models.Classs;
import org.mql.java.models.FieldsModels;
import org.mql.java.models.PackageM;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PackageParser {

	
	
	  public List<PackageM> parse(String src) {
	        Set<Classs> classesList = new HashSet<>();
	        List<PackageM> packages = new ArrayList<>();
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        try {
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.parse(new File(src));
	            Element project = document.getDocumentElement();
	            NodeList packageNodes = project.getElementsByTagName("package");
	            
	            for (int i = 0; i < packageNodes.getLength(); i++) {
	                Node packageNode = packageNodes.item(i);

	            	String packageName = packageNode.getAttributes().getNamedItem("packageName").getNodeValue();
	                NodeList classNodes = packageNode.getChildNodes();

	                for (int j = 0; j < classNodes.getLength(); j++) {
	                    Node classNode = classNodes.item(j);

	                    if (classNode.getNodeType() == Node.ELEMENT_NODE) {
	                        Element classElement = (Element) classNode;

	                        String className = classElement.getAttribute("className");

	                        List<FieldsModels> fieldsList = new ArrayList<>();

	                        NodeList methodNodes = classElement.getElementsByTagName("method");
	                        for (int k = 0; k < methodNodes.getLength(); k++) {
	                            Node methodNode = methodNodes.item(k);

	                            if (methodNode.getNodeType() == Node.ELEMENT_NODE) {
	                                Element methodElement = (Element) methodNode;

	                                String methodType = methodElement.getAttribute("methodType");
	                                String methodName = methodElement.getTextContent();
	                                fieldsList.add(new FieldsModels(methodName, methodType));
	                            }
	                        }
	                        Classs classs = new Classs(className, fieldsList, new ArrayList<>());
	                        classesList.add(classs);
	                    }
	                }
	                PackageM packag = new PackageM(packageName,classesList);
	                packages.add(packag);
	            }
	           
	        } catch (Exception e) {
	            System.out.println("Erreur dans l'importation du fichier XML : " + e.getMessage());
	        }

	        return packages;
	    }
	  
}
