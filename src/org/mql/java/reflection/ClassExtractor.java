package org.mql.java.reflection;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.mql.java.models.ClassModels;

public class ClassExtractor {

	public static ClassModels explorClass(Class<?> clazz){
		 int modifiers = clazz.getModifiers();
		 String name = clazz.getSimpleName();
		 Class[] interfaces = clazz.getInterfaces();
		 Field[] fields = clazz.getDeclaredFields();
		 Method[] methodes = clazz.getDeclaredMethods();
		 java.lang.annotation.Annotation[] annotations = clazz.getDeclaredAnnotations();
		 org.mql.java.models.ClassModels infoClass = new org.mql.java.models.ClassModels();
		 infoClass.setName(name);
		 infoClass.setAnnotations(annotations);
		 infoClass.setFields(fields);
		 infoClass.setInterfaces(interfaces);
		 infoClass.setMethods(methodes);
		 infoClass.setModifier(modifiers);
		return infoClass;
	}
	
	public static Set<String> getClasseNames(File src) {
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
}
