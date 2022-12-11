package controller.entitycontrollers;

import model.tiles.PlowedTile;
import model.tiles.Tile;
import model.tools.Plow;
import model.tools.ToolModel;

public class PlowController extends ToolController {
    public PlowController() {
        super(new Plow());
    }

    @Override
    public Tile useTool(Tile tile) {
        return new PlowedTile(tile.getX(), tile.getY());
    }
}
