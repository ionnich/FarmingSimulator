package controller.entitycontrollers;

import model.tiles.CropTile;
import model.tiles.EmptyTile;
import model.tiles.Tile;
import model.tiles.WitherTile;
import model.tools.Shovel;
import view.factories.ToolAssetFactory;

import javax.swing.*;
import java.awt.*;

public class ShovelController extends ToolController {

    public ShovelController() {
        super(new Shovel());
    }
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
