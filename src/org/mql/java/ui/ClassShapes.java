package org.mql.java.ui;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import org.mql.java.models.Classs;
import org.mql.java.models.Relation;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class ClassShapes extends JPanel {
    private static final long serialVersionUID = 1L;

    private Set<Classs> classesList;
    private Set<ClassShape> classShape = new HashSet<ClassShape>();
    private Set<Relation> relation;

    public ClassShapes(Set<Classs> classesList, Set<Relation> relation) {
        this.classesList = classesList;
        this.relation = relation;
        setLayout(new GridLayout(0, 3, 5, 5));
        addClassesToPanel();
    }

    private void addClassesToPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (Classs cls : classesList) {
            JPanel classPanel = new JPanel();
            classPanel.setLayout(new FlowLayout());
            ClassShape c = new ClassShape(220, 250, Color.GRAY, cls);
            classShape.add(c);
            classPanel.add(c);
            add(classPanel, gbc);
            
        }
    }

    
}