package controller.entitycontrollers;

import model.tiles.EmptyTile;
import model.tiles.Tile;
import model.tools.Pickaxe;

/**
 * The PickaxeController class is the controller fof the Pickaxe tool.
 */
public class PickaxeController extends ToolController {

    /**
     * Instantiates a new PickaxeController.
     */
    public PickaxeController() {
        super(new Pickaxe());
    }

    /**
     * Uses the pickaxe tool on a tile.
     * @param tile the tile to use the pickaxe on
     * @return the tile after the pickaxe has been used
     */
    @Override
    public Tile useTool(Tile tile) {
        return new EmptyTile(tile.getX(), tile.getY());
    }
}
