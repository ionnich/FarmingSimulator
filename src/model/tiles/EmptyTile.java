package model.tiles;

/**
 * The EmptyTile represents an initial tile.
 */
public class EmptyTile extends Tile{

    /**
     * Instantiates a new EmptyTile.
     *
     * @param x the x-coordinate of the tile
     * @param y the y-coordinate of the tile
     */
// default tile for the farmland
    public EmptyTile(int x, int y){
        this.activeCrop = null;
        this.isOccupied = false;
        this.isPlowed = false;
        this.isWatered = false;
        this.hasWithered = false;
        this.hasRocks = false;
        this.x = x;
        this.y = y;
    }
}
