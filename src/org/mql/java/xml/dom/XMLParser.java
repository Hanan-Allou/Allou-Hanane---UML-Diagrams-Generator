package org.mql.java.xml.dom;
import org.mql.java.models.Classs;
import org.mql.java.models.FieldsModels;
import org.mql.java.models.MethodModels;
import org.mql.java.models.PackageM;
import org.mql.java.models.Project;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLParser {

    public static void createXML(Project project, String outputPath) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("project");
            rootElement.setAttribute("projectName", ""); // Set your project name here

            Element packagesElement = document.createElement("packages");
            document.appendChild(rootElement);
            rootElement.appendChild(packagesElement);

            for (PackageM pkg : project.getPackages()) {
                Element packageElement = document.createElement("package");
                packageElement.setAttribute("packageName", pkg.getName());
                packagesElement.appendChild(packageElement);

                for (Classs cls : pkg.getClasss()) {
                    Element classElement = document.createElement("class");
                    classElement.setAttribute("className", cls.getName());
                    packageElement.appendChild(classElement);

                    for (FieldsModels field : cls.getFields()) {
                        Element fieldElement = document.createElement("field");
                        fieldElement.appendChild(document.createTextNode(field.getName()));
                        classElement.appendChild(fieldElement);
                    }

                    for (MethodModels method : cls.getMethods()) {
                        Element methodElement = document.createElement("method");
                        methodElement.appendChild(document.createTextNode(method.getName()));
                        classElement.appendChild(methodElement);
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(outputPath));

            transformer.transform(source, result);

            System.out.println("XML file created successfully!");

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

   
}
