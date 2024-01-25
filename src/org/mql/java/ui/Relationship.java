package org.mql.java.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

import org.mql.java.enumerations.RelationType;

public class Relationship extends JPanel {
	private static final long serialVersionUID = 1L;
	private ClassShape sourceClass;
    private ClassShape targetClass;
    private RelationType relationType;

    public Relationship(ClassShape sourceClass, ClassShape targetClass, RelationType relationType) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
        this.relationType = relationType;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (relationType) {
            case AGGREGATION:
                drawAggregation(g);
                break;
            case ASSOCIATION:
                drawAssociation(g);
                break;
            case INHERITANCE:
                drawInheritance(g);
                break;
            default:
                drawGenericRelation(g);
        }
    }
private void drawAggregation(Graphics g) {
        
    }

    private void drawAssociation(Graphics g) {
        g.drawLine(sourceClass.getX() + sourceClass.getWidth() / 2, sourceClass.getY() + sourceClass.getHeight(),
                targetClass.getX() + targetClass.getWidth() / 2, targetClass.getY());
    }


    private void drawGenericRelation(Graphics g) {
        g.drawLine(sourceClass.getX() + sourceClass.getWidth() / 2, sourceClass.getY() + sourceClass.getHeight(),
                targetClass.getX() + targetClass.getWidth() / 2, targetClass.getY());
    }
    
    private void drawInheritance(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int x1 = sourceClass.getX() + sourceClass.getWidth() / 2;
        int y1 = sourceClass.getY() + sourceClass.getHeight();
        int x2 = targetClass.getX() + targetClass.getWidth() / 2;
        int y2 = targetClass.getY();

        g2d.drawLine(x1, y1, x2, y2);
        Polygon arrowhead = createArrowhead(x1, y1, x2, y2);
        g2d.fillPolygon(arrowhead);
    }

    private Polygon createArrowhead(int x1, int y1, int x2, int y2) {
        Polygon arrowhead = new Polygon();
        arrowhead.addPoint(x2, y2);
        arrowhead.addPoint(x2 - 5, y2 - 10);
        arrowhead.addPoint(x2 + 5, y2 - 10);
        return arrowhead;
    }
}    