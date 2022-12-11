package model.tiles;

public class WitherTile extends Tile{

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
