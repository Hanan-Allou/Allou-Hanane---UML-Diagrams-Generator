package org.mql.java.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.JPanel;

import org.mql.java.models.PackageM;

public class PackageShapes extends JPanel {
    private static final long serialVersionUID = 1L;

    private Set<PackageM> packageMList;
    private Set<PackageShape> packageShape;

    public PackageShapes(Set<PackageM> packageMList) {
        this.packageMList = packageMList;
        setLayout(new GridLayout(0, 3, 5, 5));
        addPackageToPanel();
    }

    private void addPackageToPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (PackageM pck : packageMList) {
            JPanel packagePanel = new JPanel();
            packagePanel.setLayout(new FlowLayout());
            PackageShape c = new PackageShape(220, 250, Color.GRAY, Color.LIGHT_GRAY, pck );
            packagePanel.add(c);
            packagePanel.add(c);
            add(packagePanel, gbc);
            
        }
    }

    
}