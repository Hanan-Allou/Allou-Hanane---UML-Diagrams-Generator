package org.mql.java.reflection;

import java.lang.reflect.Field;
import java.util.*;

import org.mql.java.models.Relation;
import org.mql.java.enumerations.RelationType;
public class ClassRelationsExtractor {

	private List<Class<?>> classes = new Vector<>();
	private List<Relation> relations = new Vector<Relation>();
	
	
	public ClassRelationsExtractor(List<Class<?>> classes, List<Relation> relations) {
		this.classes = classes;
		this.relations = relations;
	}



	public  void getRelations(List<Class<?>> classes, int currentIndex){
		if (currentIndex < classes.size() - 1) {
			Class<?> currentClass = classes.get(currentIndex);
           for (int i = currentIndex + 1; i < classes.size(); i++) {
        	   Class<?> otherClass = classes.get(i);
        	   RelationType relationType = null ;
            	 if(hasAggregation(currentClass,otherClass)) {
            		   relationType = RelationType.AGGREGATION;
            		   if(isSource(currentClass, otherClass)) {
            		   Class<?> souce = currentClass;
            		   Relation relation = new Relation(relationType, souce, otherClass);
                       this.relations.add(relation);
            		   }else {
            			   Class<?> souce = otherClass;
            			   Relation relation = new Relation(relationType, souce, otherClass);
                           this.relations.add(relation);
            		   }
            	   	}
            	 if(isAssociationSimple(currentClass,otherClass)) {
          			
          			relationType = RelationType.ASSOCIATION;
          			 Relation relation = new Relation(relationType, currentClass, otherClass);
                     this.relations.add(relation);
          		}
          			
          		
          		if(hasInheritance(currentClass,otherClass)) {
          			relationType = RelationType.INHERITANCE;
          			if(isSource(currentClass, otherClass)) {
             		   Class<?> ClassParent = getParent(currentClass,otherClass);
             		  Relation relation = new Relation(relationType, ClassParent, otherClass);
                      this.relations.add(relation);
          			}
          			
          		}
            	   
            	 
                   
               }
           }
           getRelations(classes, currentIndex + 1);
		}
		
	




	
	///pour tester la quelle des classes et source de l'autre 
	private boolean isSource(Class<?> sourceClass, Class<?> targetClass) {

        for (Field field : sourceClass.getDeclaredFields()) {
            if (field.getType().equals(targetClass)) {
                return true; 
            } 
            
        }
        
		return false;
	}
	
	
	

	private boolean isPrimitive(String type) {
		return false;
	}
	 private boolean isAssociationSimple(Class<?> class1, Class<?> class2) {
	        for (Field field1 : class1.getDeclaredFields()) {
	            for (Field field2 : class2.getDeclaredFields()) {
	                if (field1.getType().equals(class2.getSimpleName()) || field2.getType().equals(class1.getSimpleName())) {
	                    return true;
	                }
	            }
	        }
	        return false; 
	    }
	 
	 private boolean hasAggregation(Class<?> Class1, Class<?> Class2) {
	            if (isSource(Class1,Class2) || isSource(Class2,Class1)) {
	                return true; 
	            }
				return false; 
	        }
	  private boolean hasInheritance(Class<?> class1, Class<?> class2) {
		  //class2 est une classe parente de class1
		return class2.isAssignableFrom(class1); 
	  }
	        
	  private Class<?>  getParent(Class<?> class1, Class<?> class2) {
		  if(class2.isAssignableFrom(class1)) return class2;
		  else return class1;
	  }
}
