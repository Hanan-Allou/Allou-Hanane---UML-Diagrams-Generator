package org.mql.java.ui;

import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.mql.java.models.PackageM;

public class PackageDiagDrawer extends JFrame{
	private static final long serialVersionUID = 1L;
	private Set<PackageM> packageM;

	 
	    public PackageDiagDrawer(Set<PackageM> packageM) {
	        this.packageM = packageM;
	    	initializeUI();
	    }
	    private void initializeUI() {
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setTitle("Package Diagram");
	        PackageShapes packageShapes = new PackageShapes(packageM);
	        JScrollPane scrollPane = new JScrollPane(packageShapes);
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
