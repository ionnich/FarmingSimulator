package model.tiles;

/**
 * The RockyTile represents a tile with a rock on it.
 */
public class RockyTile extends Tile{

    /**
     * Instantiates a new Rocky tile.
     *
     * @param x the x-coordinates of the tile
     * @param y the y-coordinates of the tile
     */
    public RockyTile(int x, int y){
        super.activeCrop = null;
        super.isPlowed = false;
        super.isOccupied = true;
        super.isWatered = false;
        super.hasWithered = false;
        super.hasRocks = true;
        super.x = x;
        super.y = y;
    }

    @Override
    public String toString(){
        return "This tile has rocks :(";
    }
}
