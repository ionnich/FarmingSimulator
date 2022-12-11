package controller.entitycontrollers;

import model.tiles.CropTile;
import model.tiles.EmptyTile;
import model.tiles.Tile;
import model.tiles.WitherTile;
import model.tools.Shovel;
import view.factories.ToolAssetFactory;

import javax.swing.*;
import java.awt.*;

/**
 * The ShovelController class is the controller of the shovel tool.
 */
public class ShovelController extends ToolController {

    /**
     * Instantiates a new ShovelController.
     */
    public ShovelController() {
        super(new Shovel());
    }

    /**
     * Uses a shovel on a tile.
     *
     * @param tile the tile to be shovelled
     * @return the shovelled tile
     */
    @Override
    public Tile useTool(Tile tile) {

        // check if tile contains a withered crop
        if(tile instanceof WitherTile)
            return new EmptyTile(tile.getX(), tile.getY());

        // check if tile contains a plant
        if(tile instanceof CropTile)
            return new EmptyTile(tile.getX(), tile.getY());

        return tile;
    }
}
