package model.tiles;

import model.crops.Crop;

/**
 * The CropTile class represents a tile that has a crop planted on it.
 */
public class CropTile extends Tile {

    /**
     * Instantiates a new Crop tile.
     *
     * @param crop the crop
     * @param x    the x-coordinate of the tile
     * @param y    the y-coordinate of the tile
     */
    public CropTile(Crop crop, int x, int y) {
        this.hasRocks = false;
        this.isOccupied = true;
        this.isPlowed = false;
        this.hasWithered = false;
        this.activeCrop = crop;
        this.x = x;
        this.y = y;
     }

    /**
     * Allows the crop to grow.
     */
    public void growCrop() {
         this.activeCrop.grow();
     }

    /**
     * Gets the crop.
     *
     * @return the crop
     */
    public Crop getCrop() {
         return this.activeCrop;
     }

     /**
      * Displays the status of the crop.
      */
     @Override
    public String toString(){
        String acc = "";
        acc += this.activeCrop.toString();

        return acc;
     }
}

