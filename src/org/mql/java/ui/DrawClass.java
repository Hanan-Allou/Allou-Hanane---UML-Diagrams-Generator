package org.mql.java.ui;

import java.awt.Color;
import java.awt.Graphics;


public class DrawClass implements Shape{

	private int x , y , height, width;
	private Color color= Color.BLACK;
	public DrawClass(int x, int y, int height, int width, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.color = color;
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		g.drawRect(x, y, width, height);
		
	}

	
	

}
