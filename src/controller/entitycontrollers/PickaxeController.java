package controller.entitycontrollers;

import model.tiles.EmptyTile;
import model.tiles.Tile;
import model.tools.Pickaxe;

public class PickaxeController extends ToolController {

    public PickaxeController() {
        super(new Pickaxe());
    }

    @Override
    public Tile useTool(Tile tile) {
        return new EmptyTile(tile.getX(), tile.getY());
    }
}
