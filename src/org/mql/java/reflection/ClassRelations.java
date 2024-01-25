package org.mql.java.reflection;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.mql.java.enumerations.RelationType;
import org.mql.java.models.Project;
import org.mql.java.models.Relation;

public class ClassRelations {

    private Set<Class<?>> classes;
    private Set<Relation> relations;

    public ClassRelations(Set<Class<?>> classes) {
        this.classes = classes;
        this.relations = detectRelations();
    }

    public Set<Relation> getRelations() {
        return relations;
    }

    public Set<Relation> detectRelations() {
        Set<Relation> detectedRelations = new HashSet<>();
        Set<Class<?>> visitedClasses = new HashSet<>();

        for (Class<?> currentClass : classes) {
            Set<Class<?>> remainingClasses = new HashSet<>(classes);
            remainingClasses.remove(currentClass);
            detectRelationsRecursively(currentClass, remainingClasses, detectedRelations, visitedClasses);
        }
        return detectedRelations;
    }

    private void detectRelationsRecursively(Class<?> currentClass, Set<Class<?>> remainingClasses,
            Set<Relation> detectedRelations, Set<Class<?>> visitedClasses) {
        visitedClasses.add(currentClass);

        for (Class<?> otherClass : remainingClasses) {
            if (!visitedClasses.contains(otherClass)) {

                if (hasAggregation(currentClass, otherClass)) {
                    detectedRelations.add(new Relation(RelationType.AGGREGATION, currentClass, otherClass));
                }

                if (isAssociationSimple(currentClass, otherClass)) {
                    detectedRelations.add(new Relation(RelationType.ASSOCIATION, currentClass, otherClass));
                }

                if (hasInheritance(currentClass, otherClass)) {
                	System.out.println("heare "+currentClass+ otherClass);
                    detectedRelations.add(new Relation(RelationType.INHERITANCE, currentClass, otherClass));
                }

                detectRelationsRecursively(otherClass, remainingClasses, detectedRelations, visitedClasses);
            }
        }
    }
    private boolean isSource(Class<?> sourceClass, Class<?> targetClass) {
        for (Field field : sourceClass.getDeclaredFields()) {
            if (field.getType().equals(targetClass)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAssociationSimple(Class<?> class1, Class<?> class2) {
        for (Field field1 : class1.getDeclaredFields()) {
            for (Field field2 : class2.getDeclaredFields()) {
                if (field1.getType().equals(class2) || field2.getType().equals(class1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasAggregation(Class<?> class1, Class<?> class2) {
        return isSource(class1, class2) || isSource(class2, class1);
    }


    private boolean hasInheritance(Class<?> class1, Class<?> class2) {
        return class1.getSuperclass()==class2 || class2.getSuperclass()==class1;
    }
    
    public static void main(String[] args) {
    	ProjectReflect projectReflect = new ProjectReflect("C:\\DATA\\workspace\\p04-XML-2 Parsers\\bin");
    	Project project = projectReflect.projectLoader();
    	
    	ClassRelations classRelations = new ClassRelations(projectReflect.getClassesLoaded());
    	 Set<Relation> relations = classRelations.getRelations();
         for (Relation relation : relations) {
             System.out.println(relation);
         }
	}
}
