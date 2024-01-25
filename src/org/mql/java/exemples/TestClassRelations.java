package org.mql.java.exemples;

import java.util.HashSet;
import java.util.Set;

import org.mql.java.models.Project;
import org.mql.java.models.Relation;
import org.mql.java.reflection.ClassRelations;
import org.mql.java.reflection.ProjectReflect;

public class TestClassRelations {

    public static void main(String[] args) {

        Set<Class<?>> classes = new HashSet<>();
        classes.add(ClassA.class);
        classes.add(ClassB.class);
        classes.add(ClassC.class);

        ProjectReflect projectReflect = new ProjectReflect("C:\\DATA\\workspace\\p04-XML-2 Parsers\\bin");
    	Project project = projectReflect.projectLoader();
    	
        ClassRelations classRelations = new ClassRelations(projectReflect.getClassesLoaded());

        Set<Relation> relations = classRelations.getRelations();
        for (Relation relation : project.getRelations()) {
        	System.out.println(relation.getSourceClass().getSimpleName());
        	System.out.println(relation.getTargetClass().getSimpleName());
            System.out.println(relation.getType());
        }
    }

    static class ClassA extends ClassC{
        private ClassB b;
    }

    static class ClassB {
        private ClassC c;
    }

    static class ClassC {
    }
}
