package model.tiles;

import model.crops.Crop;

/**
 * The RootTile represents the tile with a root from a growing tree.
 */
public class RootTile extends Tile {

    private int dissolveTimer;

    /**
     * Instantiates a new Root tile.
     *
     * @param treeRoot the tree root
     * @param x        the x-coordinate
     * @param y        the y-coordinate
     */
    public RootTile(Crop treeRoot, int x, int y){
        this.hasRocks = false;
        this.isOccupied = true;
        this.isPlowed = false;
        this.hasWithered = false;
        this.x = x;
        this.y = y;
        this.dissolveTimer = treeRoot.getHarvestTimer();
    }

    /**
     * Grows the root.
     */
    public void growRoot(){
        this.dissolveTimer--;
    }

    /**
     * Tracks if the root has dissolved.
     *
     * @return the boolean value of whether the root has dissolved
     */
    public boolean isDissolved(){
        return this.dissolveTimer <= 0;
    }

    /**
     * Gets the dissolved timer.
     *
     * @return the value of the dissolved timer
     */
    public int getDissolveTimer(){
        return this.dissolveTimer;
    }


    @Override
    public String toString(){
        return "This tile is overran by roots!";
    }

}
