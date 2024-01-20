package org.mql.java.ui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;




public class Entity extends JPanel{
	private static final long serialVersionUID = 1L;

	private int width, height;
    private Vector<Shape> shapes;

    public Entity(int width, int height) {
        super();
        this.width = width;
        this.height = height;

        setBackground(Color.white);
        setBorder(new LineBorder(Color.blue));
        shapes = new Vector<Shape>();
    }
    public void add(Shape shape) {
        shapes.add(shape);
        repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Shape shape : shapes) {
            shape.paint(g);
        }
        System.out.println("component()");
    }
	}


