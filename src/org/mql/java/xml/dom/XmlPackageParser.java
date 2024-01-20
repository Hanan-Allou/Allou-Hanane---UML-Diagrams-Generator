package org.mql.java.xml.dom;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.mql.java.models.Classs;
import org.mql.java.models.FieldsModels;
import org.mql.java.models.MethodModels;
import org.mql.java.models.PackageM;

public class XmlPackageParser {


	public List<PackageM> parse(String source) {
			
		Set<Classs> classes = new HashSet<Classs>();
		List<PackageM> packagem = new Vector<PackageM>();
        XMLNode projet = new XMLNode(source);
        XMLNode packagesNode = projet.getChild("packages");
        XMLNode[] packageNodes = packagesNode.getChildren();

        for (XMLNode packageNode : packageNodes) {
            String packageName = packageNode.getAttribute("packageName");
            XMLNode[] childNodes = packageNode.getChildren();
            for (XMLNode childNode : childNodes) {
                    String className = childNode.getAttribute("className");
                    System.out.println(className);
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
            PackageM pck = new PackageM(packageName,classes);
            packagem.add(new PackageM(packageName,classes));
            
        }

        return packagem;
    }

}
