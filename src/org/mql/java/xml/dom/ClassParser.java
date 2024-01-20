package org.mql.java.xml.dom;

import org.mql.java.models.Classs;
import org.mql.java.models.FieldsModels;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassParser {

    public List<Classs> parse(String src) {
        List<Classs> classesList = new ArrayList<>();

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
            }
        } catch (Exception e) {
            System.out.println("Erreur dans l'importation du fichier XML : " + e.getMessage());
        }

        return classesList;
    }

    public static void main(String[] args) {
        ClassParser x = new ClassParser();
        List<Classs> classesList = x.parse("C:\\\\DATA\\\\workspace\\\\Allou Hanane - UML Diagrams Generator\\\\src\\\\resourses\\\\File.xml");

        for (Classs classs : classesList) {
            System.out.println("Class Name: " + classs.getName());
            System.out.println("Fields:");
            for (FieldsModels fieldsModel : classs.getFields()) {
                System.out.println("\tField Name: " + fieldsModel.getName() + ", Field Type: " + fieldsModel.getType());
            }
            System.out.println("///////////////////////////");
        }
    }
}
