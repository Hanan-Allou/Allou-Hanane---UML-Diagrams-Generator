package org.mql.java.exemples;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.mql.java.models.Project;
import org.mql.java.reflection.ProjectReflect;
import org.mql.java.xml.dom.XMLParser;


public class Exemples {

	public Exemples() {
		exp04();
	}

	
	public void exp01() {
		        ProjectReflect pre = new ProjectReflect("C:\\DATA\\workspace\\projet\\p03-Annotations and Reflection_02\\bin");
		        pre.projectLoader();
		    

	}
	
	
	public void exp02() {
		Set<String> PNames = getPackageNames(new File("src"));
		Set<String> CNames = getClasseNames(new File("src"));
		
		for (String clas : PNames) {
			System.out.println(clas);
			
		}
		for (String clas : CNames) {
			System.out.println(clas);
			
		}
		
	}
	/*
	
	public void exp03() {
		ChooseFile file = new ChooseFile();
		File f = file.showProjectChooserDialog();
		String path = f.getAbsolutePath();
	        ProjectReflect pre = new ProjectReflect(path+"\\\\bin");
	        pre.projectLoader();
	    
		
	}*/
	
	
	public void exp04() {
		ProjectReflect projectReflect = new ProjectReflect("C:\\\\DATA\\\\workspace\\\\projet\\\\p03-Annotations and Reflection_02\\\\bin");
    	Project project = projectReflect.projectLoader();
    	XMLParser.createXML(project, "C:\\DATA\\workspace\\Allou Hanane - UML Diagrams Generator\\src\\resourses\\NewFile.xml");
	}
	
	void ClassExtractor() {
		Class<?> cl1 = null;
		int modifiers = 0;
		 String name;
		 Class[] interfaces;
		 Field[] fields;
		 Method[] methodes;
		 Annotation[] annotations;
		Set<String> Names = getClasseNames(new File("src"));
		for (String clas : Names) {
			try {
				System.out.println(Class.forName(clas).toString());
				cl1 = Class.forName(clas);
				modifiers = cl1.getModifiers();
				name = cl1.getSimpleName();
				interfaces = cl1.getInterfaces();
				fields = cl1.getDeclaredFields();
				methodes = cl1.getDeclaredMethods();
				annotations = cl1.getDeclaredAnnotations();
				System.out.println(name);
			} catch (ClassNotFoundException e) {
				 System.out.println("erreur");
			}
			
		}
		 
		
	}
	
	public Set<String> getClasseNames(File src) {
		Set<String> packageNames = new HashSet<String>();
		for (File el : src.listFiles()) {
			if(el.isDirectory()) {
				Set<String> temp = getClasseNames(el);
				for (String c : temp) {
					packageNames.add(el.getName() + "." + c);
				}
			}
			else {
				packageNames.add(el.getName());
			}
		}
		return packageNames;
	}
	
	
	public Set<String> getPackageNames(File src) {
		Set<String> classNames = getClasseNames(src);
		Set<String> packageNames = new HashSet<String>();
		for (String className : classNames) {
			String[] tmp = className.split("\\.");
			String packageName = tmp[0];
			for (int i = 1; i < tmp.length - 2; i++) {
				packageName += "." + tmp[i];
			}
			packageNames.add(packageName);
		}
		return packageNames;
	}
	

	
	
	public static void main(String[] args) {
		new Exemples();
	}
}
