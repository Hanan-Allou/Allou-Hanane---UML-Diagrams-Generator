package org.mql.java.reflection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomClassLoader extends ClassLoader{
	 private final String classpath;

	    public CustomClassLoader(String classpath, ClassLoader parent) {
	        super(parent);
	        this.classpath = classpath;
	    }

	    @Override
	    protected Class<?> findClass(String name) throws ClassNotFoundException {
	        try {
	            byte[] classData = loadClassData(name);
	            return defineClass(name, classData, 0, classData.length);
	        } catch (IOException e) {
	            throw new ClassNotFoundException("Failed to load class " + name, e);
	        }
	    }

	    private byte[] loadClassData(String className) throws IOException {
	        
	        String path = className.replace('.', File.separatorChar) + ".class";
	        File classFile = new File(classpath, path);

	       
	        Path filePath = Paths.get(classFile.toURI());
	        return Files.readAllBytes(filePath);
	    }

	    
	   
	    

	    public static void main(String[] args) {
	        
	        String projectPath = "C:\\\\DATA\\\\workspace\\\\Allou Hanane - StringMapper\\\\bin";
	        CustomClassLoader customClassLoader = new CustomClassLoader(projectPath, ClassLoader.getSystemClassLoader());

	        try {
	            Class<?> loadedClass = customClassLoader.loadClass("org.mql.java.models.Person");

	            System.out.println("Loaded class: " + loadedClass.getName());

	            System.out.println("Fields:");
	            for (var field : loadedClass.getDeclaredFields()) {
	                System.out.println("  " + field.getName() + " : " + field.getType());
	            }

	            System.out.println("Methods:");
	            for (var method : loadedClass.getDeclaredMethods()) {
	                System.out.println("  " + method.getName());
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	}