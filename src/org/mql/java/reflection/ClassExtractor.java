package org.mql.java.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.mql.java.models.Annotation;

public class ClassExtractor {

	public static org.mql.java.models.Class explorClass(Class<?> clazz){
		int modifiers = clazz.getModifiers();
		String name = clazz.getSimpleName();
		 Class[] interfaces = clazz.getInterfaces();
		 Field[] fields = clazz.getDeclaredFields();
		 Method[] methodes = clazz.getDeclaredMethods();
		 java.lang.annotation.Annotation[] annotations = clazz.getDeclaredAnnotations();
		 
		 org.mql.java.models.Class infoClass = new org.mql.java.models.Class();
		 infoClass.setName(name);
		 infoClass.setAnnotations(annotations);
		 infoClass.setFields(fields);
		 infoClass.setInterfaces(interfaces);
		 infoClass.setMethods(methodes);
		 infoClass.setModifier(modifiers);
		return infoClass;
	}
}
