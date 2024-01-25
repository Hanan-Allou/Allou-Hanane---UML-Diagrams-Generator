package org.mql.java.xml.dom;

import org.mql.java.models.Classs;
import org.mql.java.models.FieldsModels;
import org.mql.java.models.MethodModels;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassParser {

    public Set<Classs> parse(String src) {
        Set<Classs> classesList = new HashSet<Classs>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(src));
            Element project = document.getDocumentElement();
            NodeList packageNodes = project.getElementsByTagName("package");

            for (int i = 0; i < packageNodes.getLength(); i++) {
                Node packageNode = packageNodes.item(i);
                NodeList classNodes = packageNode.getChildNodes();

                for (int j = 0; j < classNodes.getLength(); j++) {
                    Node classNode = classNodes.item(j);

                    if (classNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element classElement = (Element) classNode;

                        String className = classElement.getAttribute("className");

                        List<FieldsModels> fieldsList = new ArrayList<>();
                        List<MethodModels> methodsList = new ArrayList<>();
                        NodeList methodNodes = classElement.getElementsByTagName("method");
                        for (int k = 0; k < methodNodes.getLength(); k++) {
                            Node methodNode = methodNodes.item(k);

                            if (methodNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element methodElement = (Element) methodNode;

                                String methodType = methodElement.getAttribute("methodType");
                                String methodName = methodElement.getTextContent();
                                methodsList.add(new MethodModels(methodName, methodType));
                            }
                        }
                        NodeList FieldNodes = classElement.getElementsByTagName("field");
                        for (int k = 0; k < FieldNodes.getLength(); k++) {
                            Node FieldNode = FieldNodes.item(k);

                            if (FieldNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element fieldElement = (Element) FieldNode;

                                String fieldType = fieldElement.getAttribute("fieldType");
                                String fieldName = fieldElement.getTextContent();
                                fieldsList.add(new FieldsModels(fieldName, fieldType));
                            }
                        }
                        Classs classs = new Classs(className, fieldsList, methodsList);
                        classesList.add(classs);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur dans l'importation du fichier XML : " + e.getMessage());
        }

        return classesList;
    }

    
}
