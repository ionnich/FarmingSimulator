package controller.entitycontrollers;

import model.tiles.CropTile;
import model.tiles.Tile;
import model.tools.WateringCan;

/**
 * The WateringCanController class is the controller of the WateringCan tool.
 */
public class WateringCanController extends ToolController {

    /**
     * Instantiates a new WateringCanController.
     */
    public WateringCanController() {
        super(new WateringCan());
    }
    @Override
    public Tile useTool(Tile tile) {
        // if the tile is a crop tile, water the crop
        return tile;
    }

    /**
     * Uses the watering can on a given tile
     *
     * @param tile       the tile to be watered
     * @param waterBonus the water bonus of the farmer
     * @return the watered tile
     */
    public Tile waterTile(Tile tile, int waterBonus) {

        System.out.println("watering the tile");

        // get the crop on the tile
        // if the crop is not null
        if(((CropTile) tile).getCrop() != null) {
            // water the crop
            ((CropTile) tile).getCrop().water(
                waterBonus
            );
        }

        // water the crop
        return tile;
    }
}