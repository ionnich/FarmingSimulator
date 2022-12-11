package controller.entitycontrollers;

import model.tiles.CropTile;
import model.tiles.Tile;
import model.tools.Fertilizer;


public class FertilizerController extends ToolController {

    public FertilizerController() {
        super(new Fertilizer());
    }

    @Override
    public Tile useTool(Tile tile) {
        // do nothing
        return tile;
    }
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
