package controller.entitycontrollers;

import model.tiles.PlowedTile;
import model.tiles.Tile;
import model.tools.Plow;

/**
 * The PlowController class is the controller of the Plow tool.
 */
public class PlowController extends ToolController {

    /**
     * Instantiates a new PlowController.
     */
    public PlowController() {
        super(new Plow());
    }

    /**
     * Uses a plow on a tile.
     *
     * @param tile the tile to be plowed
     * @return the plowed tile
     */
    @Override
    public Tile useTool(Tile tile) {
        return new PlowedTile(tile.getX(), tile.getY());
    }
}
