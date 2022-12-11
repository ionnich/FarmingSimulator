package model.tiles;

import model.crops.Crop;

public class CropTile extends Tile {
    public CropTile(Crop crop, int x, int y) {
        this.hasRocks = false;
        this.isOccupied = true;
        this.isPlowed = false;
        this.hasWithered = false;
        this.activeCrop = crop;
        this.x = x;
        this.y = y;
     }

     public void growCrop() {
         this.activeCrop.grow();
     }

     public Crop getCrop() {
         return this.activeCrop;
     }

     @Override
    public String toString(){
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
}

