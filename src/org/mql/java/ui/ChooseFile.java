package org.mql.java.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

public class ChooseFile extends JFrame{

	public ChooseFile() {
		 setTitle("Projet Loader");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(400, 200);
	        setLocationRelativeTo(null);

	        JButton browseButton = new JButton("Choisir un projet");
	        browseButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                showProjectChooserDialog();
	            }
	        });

	        JPanel panel = new JPanel();
	        panel.add(browseButton);

	        getContentPane().add(panel);
	}
	
	public File showProjectChooserDialog() {
		 JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	        int result = fileChooser.showOpenDialog(this);
	        
	        if (result == JFileChooser.APPROVE_OPTION) {
	            return fileChooser.getSelectedFile();
	        }
			return null;
	}

}
