package model.tiles;

import model.crops.Crop;

/**
 * The Tile is an abstract class that represents a tile on the farmland.
 */
public abstract class Tile {
    protected boolean isPlowed;
    protected boolean isWatered;
    protected boolean hasWithered;
    protected boolean hasRocks;
    protected int x;
    protected int y;
    protected boolean isOccupied;
    protected Crop currentCrop;
    protected Crop activeCrop = null;

    /**
     * Displays the status of the tile.
     */
    @Override
    public String toString() {
        String acc = "";
            acc += "\nPlowed: " + this.isPlowed ;
            acc += "\nWatered: " + this.isWatered;
            acc += "\nWithered: " + this.hasWithered;
            acc += "\nHas Rocks: " + this.hasRocks;
            acc += "\nOccupied: " + this.isOccupied;

        return acc;
    }

    /**
     * Gets the x-coordinate of the tile.
     *
     * @return the x-coordinate of the tile
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the tile.
     *
     * @return the y-coordinate of the tile
     */
    public int getY() {
        return y;
    }

    /**
     * Tracks if the tile is occupied.
     *
     * @return the boolean value of isOccupied
     */
    public boolean isOccupied() {
        return isOccupied;
    }
}
