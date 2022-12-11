package view;

import model.tools.ToolModel;
import view.factories.ToolAssetFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ToolView extends JButton {

    public ToolView(ToolModel tool) {
        updateToolView(tool);
    }

    public void updateToolView(ToolModel tool){
        ToolAssetFactory toolAssetFactory = new ToolAssetFactory();
        Image asset = toolAssetFactory.fetch(tool).getImage();

        Image scaled = asset.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaled));
    }

}
