package org.mql.java.ui;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.mql.java.models.*;
import org.mql.java.models.Project;
import org.mql.java.reflection.ProjectReflect;
import org.mql.java.xml.dom.ClassParser;
import org.mql.java.xml.dom.PackageParser;
import org.mql.java.xml.dom.XMLWRITER;

public class Form extends JPanel {
	private static final long serialVersionUID = 1L;
	private LabeledTextField panel ;
	public Form() {
		panel = new LabeledTextField("chemin", 30);
		add(panel);
		JButton button1 = new JButton("Generer Class Diagram");
		JButton button2 = new JButton("Generer Package Diagram");
		button1.addActionListener(e -> generateClassDiagram());
		button2.addActionListener(e -> generatePackageDiagram());
		add(button1);
		add(button2);
		
	}

	
	private void generateClassDiagram() {
		generateXmlFile();
		ClassParser x = new ClassParser();
    	Set<Classs> classesList = x.parse("resources\\\\File.xml");
    	new ClassDiagDrawer(classesList);
	}


	private void generatePackageDiagram() {
		PackageParser x = new PackageParser();
    	Set<PackageM> packageList = x.parse("resources\\File.xml");
    	new PackageDiagDrawer(packageList);
	}


	private void generateXmlFile(){
		JTextField field = panel.getTextField();
		String path = field.getText();
		File file = new File(path+"\\bin");
		if(!file.exists()) {
			System.out.println("file not found!!!!!");
			return ;
		}
		ProjectReflect projectReflect = new ProjectReflect(path+"\\bin");
    	Project project = projectReflect.projectLoader();
    	XMLWRITER.createXML(project, "resources\\File.xml");
    	
	}
	

}
