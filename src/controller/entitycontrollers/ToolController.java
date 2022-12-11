package controller.entitycontrollers;

import model.tiles.Tile;
import model.tools.ToolModel;
import view.ToolView;
import view.factories.ToolAssetFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The ToolController is an abstract class that is used by specific tool controllers
 */
public abstract class ToolController {
    protected ToolView toolView;
    protected ToolModel toolModel;

    /**
     * Instantiates a new ToolController.
     *
     * @param toolModel the tool model
     */
    public ToolController(ToolModel toolModel) {
        this.toolModel = toolModel;
        this.toolView = new ToolView(toolModel);
    }

    /**
     * Gets the tool view.
     *
     * @return the tool view
     */
    public ToolView getToolView() {
        return this.toolView;
    }

//    public JButton initializeToolView() {
//        ToolAssetFactory toolAssetFactory = new ToolAssetFactory();
//        Image asset = toolAssetFactory.fetch(toolModel).getImage();
//
//        Image scaled = asset.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
//        this.toolView.setIcon(new ImageIcon(scaled));
//        return this.toolView;
//    }


    /**
     * Uses a tool on a given tile.
     *
     * @param tile the tile to use the tool on
     * @return the tile after the tool has been used
     */
    public abstract Tile useTool(Tile tile);

    /**
     * Gets the tool model.
     *
     * @return the tool model
     */
    public ToolModel getToolModel() {
        return toolModel;
    }

    /**
     * Gets the tool name.
     *
     * @return the tool name
     */
    public String getToolName() {
        return toolModel.toString();
    }

}
