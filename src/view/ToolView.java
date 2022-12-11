package view;

import model.tools.ToolModel;
import view.factories.ToolAssetFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 * The ToolView class is responsible for displaying the tools.
 */
public class ToolView extends JButton {

    /**
     * Instantiates a new ToolView.
     *
     * @param tool the tool
     */
    public ToolView(ToolModel tool) {
        updateToolView(tool);
    }

    /**
     * Updates the ToolView.
     *
     * @param tool the tool
     */
    public void updateToolView(ToolModel tool){
        ToolAssetFactory toolAssetFactory = new ToolAssetFactory();
        Image asset = toolAssetFactory.fetch(tool).getImage();

        Image scaled = asset.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaled));
    }

}
