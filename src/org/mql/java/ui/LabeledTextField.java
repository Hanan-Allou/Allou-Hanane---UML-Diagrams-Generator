package org.mql.java.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabeledTextField extends JPanel {

	private static final long serialVersionUID = 1L;
	JTextField t1 ;
	public LabeledTextField(String label, int size) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		if(!label.contains(":")) label += ":";
		
		JLabel l1 = new JLabel(label);
		t1 = new JTextField(size);
		
		
		add(l1);
		add(t1);
		
	}
	
	public JTextField getTextField() {
		
		return t1;
	}
	public LabeledTextField(String label, int size, int labelSize) {
		this(label,size);
		JLabel l1 = (JLabel) getComponent(0);
		l1.setPreferredSize(new Dimension(labelSize, getComponent(1).getPreferredSize().height));
	}

}
