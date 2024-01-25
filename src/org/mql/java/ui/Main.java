package org.mql.java.ui;



import javax.swing.JFrame;

public class Main extends JFrame{
	private static final long serialVersionUID = 1L;
	
	
	public Main(){
		init();
	}

	public void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(new Form());
		pack();
		setTitle("UML: Class Diagram Generator");
		setVisible(true);
	}

	
	public static void main(String[] args) {
		new Main();
	}
}
