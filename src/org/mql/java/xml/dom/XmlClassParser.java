package org.mql.java.xml.dom;


import java.util.List;
import java.util.Vector;

import org.mql.java.models.Classs;
import org.mql.java.models.FieldsModels;
import org.mql.java.models.MethodModels;

public class XmlClassParser {

	
	public List<Classs> parse(String source) {
			
		List<Classs> classes = new Vector<Classs>();
        XMLNode projet = new XMLNode(source);
        XMLNode packagesNode = projet.getChild("packages");
        XMLNode[] packageNodes = packagesNode.getChildren();

        for (XMLNode packageNode : packageNodes) {
            String packageName = packageNode.getAttribute("packageName");
            XMLNode[] childNodes = packageNode.getChildren();
            for (XMLNode childNode : childNodes) {
               // if (childNode.equals("class")) {
                    String className = childNode.getAttribute("className");
                   
                    XMLNode[] children = childNode.getChildren();
                    
                    List<FieldsModels> fields = new Vector<>();
                    List<MethodModels> methods = new Vector<>();
                    for(XMLNode child : children) {
                        XMLNode fieldNode = child.getChild("field");
                        XMLNode methodNode = child.getChild("method");
                        String fieldValue = (fieldNode != null) ? fieldNode.getValue() : null;
                        String fieldType = (fieldNode != null) ? fieldNode.getAttribute("fieldType") : null;

                        String methodValue = (methodNode != null) ? methodNode.getValue() : null;
                        String methodType = (methodNode != null) ? methodNode.getAttribute("methodType") : null;

                        FieldsModels field = new FieldsModels(fieldValue, fieldType);
                        MethodModels method = new MethodModels(methodValue, methodType);

                        fields.add(field);
                        methods.add(method);
                    }
                   

                    Classs clas = new Classs(className, fields, methods);
                    classes.add(clas);
                }
            
        }

        return classes;
    }

	
}
