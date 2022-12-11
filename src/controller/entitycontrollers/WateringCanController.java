package controller.entitycontrollers;

import model.tiles.CropTile;
import model.tiles.Tile;
import model.tools.ToolModel;
import model.tools.WateringCan;

public class WateringCanController extends ToolController {
    public WateringCanController() {
        super(new WateringCan());
    }
    @Override
    public Tile useTool(Tile tile) {
        // if the tile is a crop tile, water the crop
        return tile;
    }

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
