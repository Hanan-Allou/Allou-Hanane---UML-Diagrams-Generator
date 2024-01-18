package org.mql.java.reflection;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProjectLoader {
	private  String path;
	public ProjectLoader(String path) {
		this.path = path;
	}
	
	public  void loadPrject() {
		 CustomClassLoader customClassLoader = new CustomClassLoader(path, ClassLoader.getSystemClassLoader());
		 System.out.println(customClassLoader);
	      List<String> packages = new ArrayList<>();
	      List<String> classes = retrieveClassesInPackage(path);
		  retrievePackages(new File(path), "", packages);
	        for (String packageName : packages) {
	            System.out.println("Package : " + packageName);
	            for (String className : classes) {
	                System.out.println(packageName+ "."+ className.replace("$", "."));
	                try {
	                Class<?> loadedClass = customClassLoader.loadClass("org.mql.java.exemples.Exemple");
	                classInfo(loadedClass);
	                }catch(ClassNotFoundException e) {
	                	System.out.println("ClassNotFoundException");
	                	}
	            }
	        }
	}
	
	
	public static void retrievePackages(File directory, String parent, List<String> packages) {
        File[] files = directory.listFiles();
        List<Class<?>>  classess = new Vector<Class<?>>();
        boolean containsClass = false;
        for (File file : files) {
            if (file.isDirectory()) {
            	retrievePackages(file, parent + file.getName() + ".", packages);
            } else if (file.getName().endsWith(".class")) {
                containsClass = true;
               
            }
        }
        if (containsClass) {
            packages.add(parent.substring(0, parent.length() - 1));
        }
    }
	
	public static List<String> retrieveClassesInPackage(String projectPath) {
        List<String> classes = new ArrayList<>();
        File root = new File(projectPath);
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(retrieveClassesInPackage(file.getAbsolutePath()));
            } else if (file.getName().endsWith(".class")) {
            	classes.add(file.getName().replace(".class", ""));
            }
        }
        return classes;
    }
	
	public static void classInfo(Class<?> className){	
		Field [] fields = className.getDeclaredFields();
		System.out.println("liste des fields : ");
		for(Field field : fields) {
			   System.out.println("Nom du champ : " + field.getName());
	            System.out.println("Type du champ : " + field.getType().getName());
		}
		System.out.println("liste de methodes");
		Method [] methods = className.getDeclaredMethods();
		for (Method method : methods) {
			   System.out.println("Nom de method : " +method.getName());
	            System.out.println("Type du retour de method : " + method.getReturnType().getName());
		}
		System.out.println("Class mere");
		 List<String> classesMeres = new Vector<>();
	        Class<?> classeMere = className.getSuperclass();
	        while (classeMere != null) {
	            classesMeres.add(classeMere.getName());
	            classeMere = classeMere.getSuperclass();
	        }
	        System.out.println("Classes mères : " + classesMeres);
	    
		
		System.out.println("class Interne");
		Class<?>[] classess = className.getClasses();
		for (Class<?> classs : classess) {
			System.out.println(classs.getName().replace("$", "."));
		}
		
		System.out.println("interface implementer");
		 Class<?>[] interfaces =className.getInterfaces();
		 for (Class<?> interfacee : interfaces) {
				System.out.println(interfacee.getName());
			}
		
	
	
	
}


}
