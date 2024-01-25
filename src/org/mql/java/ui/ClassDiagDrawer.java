package org.mql.java.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.mql.java.models.Classs;
import org.mql.java.models.Relation;

public class ClassDiagDrawer extends JFrame{
	private static final long serialVersionUID = 1L;
	private Set<Classs> classes;
	    private Set<Relation> relations;

	    public ClassDiagDrawer(Set<Classs> classes, Set<Relation> relations) {
	        this.classes = classes;
	        this.relations = relations;
	    }
	    public ClassDiagDrawer(Set<Classs> classes) {
	        this.classes = classes;
	    	initializeUI();
	    }
	    private void initializeUI() {
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setTitle("Class Diagram");
	        ClassShapes classShapes = new ClassShapes(classes,null);
	        JScrollPane scrollPane = new JScrollPane(classShapes);
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

           getContentPane().add(scrollPane);
           setSize(600, 400);
           setLocationRelativeTo(null);
           setVisible(true);
	    }
	    public void updateLayout() {
	        revalidate();
	        repaint();
	    }
}
