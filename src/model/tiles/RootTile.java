package model.tiles;

import model.crops.Crop;

public class RootTile extends Tile {

    private int dissolveTimer;
    public RootTile(Crop treeRoot, int x, int y){
        this.hasRocks = false;
        this.isOccupied = true;
        this.isPlowed = false;
        this.hasWithered = false;
        this.x = x;
        this.y = y;
        this.dissolveTimer = treeRoot.getHarvestTimer();
    }

    public void growRoot(){
        this.dissolveTimer--;
    }

    public boolean isDissolved(){
        return this.dissolveTimer <= 0;
    }

    public int getDissolveTimer(){
        return this.dissolveTimer;
    }

}
