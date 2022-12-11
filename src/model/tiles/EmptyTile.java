package model.tiles;

public class EmptyTile extends Tile{

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