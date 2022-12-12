package model.tiles;

/**
 * The PlowedTile represents a plowed tile.
 */
public class PlowedTile extends Tile{

    /**
     * Instantiates a new Plowed tile.
     *
     * @param x the x-coordinate of the tile
     * @param y the y-coordinate of the tile
     */
    public PlowedTile(int x, int y){
        this.activeCrop = null;
        this.isPlowed = true;
        this.isWatered = false;
        this.isOccupied = false;
        this.hasWithered = false;
        this.hasRocks = false;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "Plowed tile";
    }
}
