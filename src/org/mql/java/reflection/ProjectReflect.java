package org.mql.java.reflection;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.mql.java.models.*;


public class ProjectReflect {
    private String path;
    private Set<PackageM> packageM = new HashSet<>();
    private  Set<Class<?>> ClassesLoaded = new HashSet<Class<?>>();
    private Set<Relation> relations = new HashSet<Relation>();
    Set<Classs> classesInPackage = new HashSet<>();
    public ProjectReflect(String path) {
        this.path = path;
    }

    public Project projectLoader() {
    	SourceClassLoader clsL = new SourceClassLoader();
        Set<String> packageNames = extractDirectories(new File(path));
        
        for (String packageName : packageNames) {
            Set<String> classNames = retrieveClassesInPackage(path, packageName);

            
            
            for (String className : classNames) {
                try {
                	Class<?> loadedClass = clsL.loadClass(path, className);
                    this.ClassesLoaded.add(loadedClass);
                    Classs classs = createClass(loadedClass);
                    
                    this.classesInPackage.add(classs);
                } catch (Exception e) {
                    System.out.println("Error loading class " + className + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }
            
            this.packageM.add(createPackage(packageName, classesInPackage));
            
            classesInPackage.clear();
           
        }
        ClassRelations classRelations = new ClassRelations(ClassesLoaded);
        Project p = createProject(path, packageM, classRelations.getRelations());
       // printProjectDetails();
        return p;
    }

   

    
    private Project createProject(String path, Set<PackageM> packageM, Set<Relation> relations) {
        Project p = new Project(path, packageM, relations);
        return p;
    }
   
	private PackageM createPackage(String packageName, Set<Classs> classesInPackage) {
        return new PackageM(packageName, new HashSet<>(classesInPackage));
    }

    private Classs createClass(Class<?> loadedClass) {
        List<MethodModels> methods = createMethods(loadedClass);
        List<FieldsModels> fields = createFields(loadedClass);
        return new Classs(loadedClass.getSimpleName(), fields, methods);
    }

    private List<FieldsModels> createFields(Class<?> loadedClass) {
        List<FieldsModels> fields = new LinkedList<>();
        Field[] classFields = loadedClass.getDeclaredFields();
        for (Field field : classFields) {
            fields.add(new FieldsModels(field.getName(), field.getType().getSimpleName()));
        }
        return fields;
    }

    private List<MethodModels> createMethods(Class<?> loadedClass) {
        List<MethodModels> methods = new LinkedList<>();
        Method[] classMethods = loadedClass.getDeclaredMethods();
        for (Method method : classMethods) {
            methods.add(new MethodModels(method.getName(), method.getReturnType().getSimpleName()));
        }
        return methods;
    }

    
    
    private Set<String> extractDirectories(File src) {
        Set<String> classNames = getClassNames(src);
        Set<String> packageNames = new HashSet<>();
        for (String className : classNames) {
            String[] tmp = className.split("\\.");
            if (tmp.length > 1) {
                String packageName = tmp[0];
                for (int i = 1; i < tmp.length - 1; i++) {
                    packageName += "." + tmp[i];
                }
                packageNames.add(packageName);
            }
        }
        return packageNames;
    }
    
    private static Set<String> retrieveClassesInPackage(String projectPath, String packageName) {
        Set<String> classes = new HashSet<>();
        File root = new File(projectPath + File.separator + File.separator + packageName.replace(".", File.separator));
        File[] files = root.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    Set<String> subPackageClasses = retrieveClassesInPackage(projectPath, packageName + "." + file.getName());
                    classes.addAll(subPackageClasses);
                } else if (file.getName().endsWith(".class")) {
                    String qualifiedClassName = packageName + "." + file.getName().replace(".class", "");
                    classes.add(qualifiedClassName);
                }
            }
        }
        
        return classes;
    }

    private Set<String> getClassNames(File src) {
        Set<String> classNames = new HashSet<>();
        if (src != null && src.isDirectory()) {
            File[] files = src.listFiles();
            if (files != null) {
                for (File el : files) {
                    if (el.isDirectory()) {
                        Set<String> temp = getClassNames(el);
                        for (String c : temp) {
                            classNames.add(el.getName() + "." + c);
                        }
                    } else if (el.getName().endsWith(".class")) {
                        String className = el.getName().replace(".class", "");
                        classNames.add(className);
                    }
                }
            }
        }
        return classNames;
    }



    private void printProjectDetails() {
        System.out.println("Project Path: " + path);
        for (PackageM pkg : packageM) {
            System.out.println("Package: " + pkg.getName());
            for (Classs cls : pkg.getClasss()) {
            	
                System.out.println("\tClass: " + cls.getName());
                for (FieldsModels field : cls.getFields()) {
                    System.out.println("\t\tField: " + field.getName() + " - Type: " + field.getType());
                }
                for (MethodModels method : cls.getMethods()) {
                    System.out.println("\t\tMethod: " + method.getName() + " - Return Type: " + method.getType());
                }
            }
        }
    }

	public Set<PackageM> getPackageM() {
		return packageM;
	}

	
	public Set<Class<?>> getClassesLoaded() {
		return ClassesLoaded;
	}

  
	
	
    
   
}
