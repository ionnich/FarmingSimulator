package model.tiles;

public class RockyTile extends Tile{

    public RockyTile(int x, int y){
        super.activeCrop = null;
        super.isPlowed = false;
        super.isOccupied = true;
        super.isWatered = false;
        super.hasWithered = false;
        super.hasRocks = true;
        super.x = x;
        super.y = y;
    }
}
