package org.mql.java.models;

import org.mql.java.enumerations.RelationType;

public class Relation {
	private RelationType type;
    private Class<?> sourceClass;//class parent si heritage ou class source si aggregation
    private Class<?> targetClass;

    public Relation(RelationType type, Class<?> sourceClass, Class<?> targetClass) {
        this.type = type;
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

	public RelationType getType() {
		return type;
	}

	public void setType(RelationType type) {
		this.type = type;
	}

	public Class<?> getSourceClass() {
		return sourceClass;
	}

	public void setSourceClass(Class<?> sourceClass) {
		this.sourceClass = sourceClass;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(Class<?> targetClass) {
		this.targetClass = targetClass;
	}
    
	 @Override
	    public String toString() {
	        return "Relation{" +
	                "type=" + type +
	                ", sourceClass=" + sourceClass.getName() +
	                ", targetClass=" + targetClass.getName() +
	                '}';
	    }
}
