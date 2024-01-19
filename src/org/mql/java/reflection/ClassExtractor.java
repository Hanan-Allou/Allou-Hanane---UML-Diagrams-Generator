package org.mql.java.reflection;

import java.io.File;
import java.util.HashSet;
import java.util.Set;


public class ClassExtractor {
	 public static Set<String> extractClassesInPackage(String projectPath, String packageName) {
	        String packagePath = packageName.replace(".", File.separator);
	        String packageFullPath = projectPath + File.separator + "src" + File.separator + packagePath;

	        Set<String> classes = new HashSet<>();
	        extractClasses(new File(packageFullPath), packageName, classes);

	        return classes;
	    }

	    private static void extractClasses(File directory, String packageName, Set<String> classes) {
	        if (directory.exists() && directory.isDirectory()) {
	            File[] files = directory.listFiles((dir, name) -> name.endsWith(".class"));
	            if (files != null) {
	                for (File file : files) {
	                    String className = packageName + "." + file.getName().replace(".class", "");
	                    classes.add(className);
	                }
	            }
	        }
	    }
    public static void main(String[] args) {
    	 String projectPath = "C:\\DATA\\workspace\\Allou Hanane - StringMapper";
         String packageName = "org.mql.java.Mapperr";

         Set<String> classNames = ClassExtractor.extractClassesInPackage("C:\\DATA\\workspace\\Allou Hanane - StringMapper", "org.mql.java.Mapperr");

         System.out.println("Classes in package " + packageName + ":");
         for (String className : classNames) {
             System.out.println(className);
         }
     }
	}

