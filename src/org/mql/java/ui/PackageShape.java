package org.mql.java.ui;

import java.awt.*;
import java.util.Set;
import javax.swing.JPanel;

import org.mql.java.models.Classs;
import org.mql.java.models.PackageM;


public class PackageShape extends JPanel {
    private static final long serialVersionUID = 1L;
    private int width, height;
    private Color borderColor;
    private Color fillColor;
    private PackageM packageModel;

    public PackageShape(int width, int height, Color borderColor, Color fillColor, PackageM packageModel) {
        this.width = width;
        this.height = height;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
        this.packageModel = packageModel;
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(fillColor);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(2)); 
        g2d.drawRect(0, 0, width, height);

        g2d.setColor(Color.BLACK);
        Font packageNameFont = new Font("Arial", Font.BOLD, 12);
        g2d.setFont(packageNameFont);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        String packageName = packageModel.getName();
        int textWidth = fontMetrics.stringWidth(packageName);
        int textHeight = fontMetrics.getHeight();
        g2d.drawString(packageName, (width - textWidth) / 2, (height + textHeight) / 2);

       
        Set<Classs> classes = packageModel.getClasss();
        int yOffset = (height + textHeight) / 2 + textHeight; 
        for (Classs clazz : classes) {
            g2d.drawString(clazz.getName(), 10, yOffset);
            yOffset += 20; 
        }
    }
}