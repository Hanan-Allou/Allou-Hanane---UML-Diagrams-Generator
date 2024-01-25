package org.mql.java.ui;

import java.awt.*;
import java.util.List;

import javax.swing.JPanel;

import org.mql.java.models.Classs;
import org.mql.java.models.FieldsModels;
import org.mql.java.models.MethodModels;

public class ClassShape extends JPanel {

    private int width, height;
    private Color color;
    private Classs classInfo;

    public ClassShape(int width, int height, Color color, Classs classInfo) {
        this.width = width;
        this.height = (classInfo.getMethods().size() + classInfo.getFields().size() + 3) * 20;
        this.color = color;
        this.classInfo = classInfo;
        setPreferredSize(new Dimension(width, this.height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
       
        drawClassName(g);
        int topLineY = 30;
        int bottomLineY = height - 30;

        g.drawLine(0, topLineY, width, topLineY);

        drawAttributes(g, topLineY);
        g.drawLine(0, topLineY + classInfo.getFields().size() * 20, width, topLineY + classInfo.getFields().size() * 20);

        drawMethods(g, bottomLineY);
    }

    private void drawClassName(Graphics g) {
        g.setColor(Color.BLACK);
        Font classNameFont = new Font("Arial", Font.BOLD, 12);
        g.setFont(classNameFont);

        String className = classInfo.getName();
        int classNameWidth = g.getFontMetrics().stringWidth(className);
        g.drawString(className, (width - classNameWidth) / 2, 20);
    }

    private void drawAttributes(Graphics g, int topLineY) {
        List<FieldsModels> fields = classInfo.getFields();
        int yOffset = topLineY + 10;

        for (FieldsModels field : fields) {
            String fieldInfo = field.getName() + " : " + field.getType();
            g.drawString("-" + fieldInfo, 10, yOffset);
            yOffset += 20;
        }
    }

    private void drawMethods(Graphics g, int bottomLineY) {
        List<MethodModels> methods = classInfo.getMethods();
        int yOffset = bottomLineY - 10;

        for (MethodModels method : methods) {
            String methodInfo = method.getName() + "()" + " : " + method.getType();
            g.drawString("+" + methodInfo, 10, yOffset);
            yOffset -= 20;
        }
    }
}