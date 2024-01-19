package org.mql.java.reflection;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClassLoaderH extends ClassLoader {
    private String projectPath;
    private Map<String, Class<?>> loadedClasses = new HashMap<>();

    public ClassLoaderH(String projectPath, ClassLoader parent) {
        super(parent);
        this.projectPath = projectPath;
    }

    public Class<?> loadClassFromFile(String className) throws ClassNotFoundException {
        if (loadedClasses.containsKey(className)) {
            return loadedClasses.get(className);
        }

        byte[] classData = loadClassData(className);
        if (classData == null) {
            throw new ClassNotFoundException("Class not found: " + className);
        }

        Class<?> clazz = defineClass(className, classData, 0, classData.length);
        loadedClasses.put(className, clazz);
        return clazz;
    }

    private byte[] loadClassData(String className) {
        String path = projectPath + File.separator + className.replace('.', File.separatorChar) + ".class";
        try (FileInputStream fis = new FileInputStream(path);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            int b;
            while ((b = fis.read()) != -1) {
                bos.write(b);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
