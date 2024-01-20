package org.mql.java.ui;

import java.awt.Color;

import javax.swing.JFrame;


public class GenerateEntity extends JFrame{
	private static final long serialVersionUID = 1L;

	private Entity entity;
	
	public GenerateEntity() {
		entity = new Entity(600,300);
		setContentPane(entity);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		entity.add(new DrawClass(50, 50, 70, 70,Color.BLACK));
	}

	public static void main(String[] args) {
		new GenerateEntity();
	}
}
