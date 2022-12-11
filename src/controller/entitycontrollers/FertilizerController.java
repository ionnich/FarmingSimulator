package controller.entitycontrollers;

import model.tiles.CropTile;
import model.tiles.Tile;
import model.tools.Fertilizer;


/**
 * The FertilizerController class is the controller of the Fertilizer tool.
 */
public class FertilizerController extends ToolController {

    /**
     * Instantiates a new FertilizerController.
     */
    public FertilizerController() {
        super(new Fertilizer());
    }

    @Override
    public Tile useTool(Tile tile) {
        // do nothing
        return tile;
    }

    /**
     * Fertilizes a tile.
     *
     * @param tile            the tile to be fertilized
     * @param fertilizerLevel the fertilizer level of the tile to be fertilized
     * @return the fertilized tile
     */
    public Tile fertilizeTile(Tile tile, int fertilizerLevel) {

        System.out.println("fertilizing the tile");
        if(((CropTile) tile).getCrop() != null) {
            // water the crop
            ((CropTile) tile).getCrop().fertilize(
                fertilizerLevel
            );
        }

        return tile;


    }
}
