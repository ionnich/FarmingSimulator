package model.tiles;

/**
 * The WitherTile represents a tile with a withered crop on it.
 */
public class WitherTile extends Tile{

    /**
     * Instantiates a new WitherTile.
     *
     * @param x the x-coordinate of the tile
     * @param y the y-coordinate of the tile
     */
    public WitherTile(int x, int y){
        this.activeCrop = null;
        this.isPlowed = false;
        this.isWatered = false;
        this.hasWithered = true;
        this.isOccupied = true;
        this.hasRocks = false;
        this.x = x;
        this.y = y;
    }
}
