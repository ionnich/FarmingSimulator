package model.tiles;

import model.crops.Crop;

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

    @Override
    public String toString() {
        String acc = "";
        if(activeCrop != null){
            acc += this.activeCrop.toString();
        }
        acc += "\nPlowed: " + this.isPlowed ;
        acc += "\nWatered: " + this.isWatered;
        acc += "\nWithered: " + this.hasWithered;
        acc += "\nHas Rocks: " + this.hasRocks;

        return acc;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
