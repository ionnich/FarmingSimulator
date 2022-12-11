package model.tiles;

public class PlowedTile extends Tile{

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
}
