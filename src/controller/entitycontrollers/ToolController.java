package controller.entitycontrollers;

import model.tiles.Tile;
import model.tools.ToolModel;
import view.ToolView;
import view.factories.ToolAssetFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class ToolController {


    protected ToolView toolView;
    protected ToolModel toolModel;

    public ToolController(ToolModel toolModel) {
        this.toolModel = toolModel;
        this.toolView = new ToolView(toolModel);
    }

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


    public abstract Tile useTool(Tile tile);

    public ToolModel getToolModel() {
        return toolModel;
    }

    public String getToolName() {
        return toolModel.toString();
    }

}
