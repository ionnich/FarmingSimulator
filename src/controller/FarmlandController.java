package controller;

import model.FarmlandModel;
import model.tiles.Tile;
import view.FarmlandView;
import view.TileView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The FarmLandController class is responsible for handling the interactions in the farmland.
 */
public class FarmlandController {
    FarmlandView farmlandView;
    FarmlandModel farmlandModel;

    /**
     * Instantiates a new FarmlandController.
     */
    public FarmlandController(){
        this.farmlandModel = new FarmlandModel(10, 5);
        this.farmlandView = new FarmlandView(
                this.farmlandModel
        );
        this.addTileEvents();
    }

    /**
     * Gets a specific tile from the farmland given its coordinates.
     *
     * @param x the x-coordinate of the tile
     * @param y the y-coordinate of the tile
     * @return the tile
     */
    public Tile getTile(int x, int y){
        if(x < 0) x = 0;
        if(x > this.farmlandModel.getWidth() - 1) x = this.farmlandModel.getWidth() - 1;

        if(y < 0) y = 0;
        if(y > this.farmlandModel.getHeight() - 1) y = this.farmlandModel.getHeight() - 1;
        return farmlandModel.getTile(x, y);
    }

    /**
     * Gets the tile view of a specific tile from the farmland given its coordinates.
     *
     * @param x the x-coordinate of the tile
     * @param y the y-coordinate of the tile
     * @return the tile view
     */
    public TileView getTileView(int x, int y){
        if(x < 0) x = 0;
        if(x > this.farmlandModel.getWidth() - 1) x = this.farmlandModel.getWidth() - 1;

        if(y < 0) y = 0;
        if(y > this.farmlandModel.getHeight() - 1) y = this.farmlandModel.getHeight() - 1;
        return this.farmlandView.getTileView(x, y);
    }

    /**
     * Mutates tile that the farmer is on.
     *
     * @param hasFarmer tracks whether the farmer is on the tile
     * @param newTile  the new tile
     * @param x         the x-coordinate of the tile to be mutated
     * @param y         the y-coordinate of the tile to be mutated
     */
    public void mutateTile(boolean hasFarmer, Tile newTile, int x, int y){
        this.farmlandModel.mutateTile(newTile, x, y);
        this.farmlandView.updateTileView(hasFarmer, newTile, x, y);
    }

    /**
     * Updates tile views.
     */
    public void updateTileViews(){
        for (int i = 0; i < this.farmlandModel.getWidth(); i++) {
            for (int j = 0; j < this.farmlandModel.getHeight(); j++) {
                this.farmlandView.updateTileView(this.farmlandModel.getTile(i, j), i, j);
            }
        }
    }

    /**
     * Adds tile events.
     */
    public void addTileEvents(){
        for (int i = 0; i < this.farmlandModel.getWidth(); i++) {
            for (int j = 0; j < this.farmlandModel.getHeight(); j++) {
                this.farmlandView.getTilePane(i, j).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("clicked");
                    }
                });
            }
        }
    }

    /**
     * Gets the farmland view.
     *
     * @return the farmland view
     */
    public FarmlandView getFarmlandView() {
        return farmlandView;
    }

    /**
     * Adds to the anchor.
     *
     * @param anchor the anchor
     */
    public void addToAnchor(JFrame anchor){
        anchor.add(farmlandView.getFarmlandPanel());
    }

    /**
     * Removes from the anchor.
     *
     * @param anchor the anchor
     */
    public void removeFromAnchor(JFrame anchor){
        anchor.remove(farmlandView.getFarmlandPanel());
    }

    /**
     * Advances day.
     */
    public void advanceDay() {
        this.farmlandModel.advanceDay();
        this.updateTileViews();
    }

    /**
     * Replaces a given tile.
     *
     * @param tile the tile to be replaced
     */
    public void replaceTile(Tile tile) {
        this.farmlandModel.replaceTile(tile);
        this.updateTileViews();
    }

    /**
     * Gets the tiles adjacent to a given tile.
     *
     * @param currentTile the current tile
     * @return the tile array that holds all adjacent tiles
     */
    public Tile[] getAdjacentTiles(Tile currentTile) {
        return this.farmlandModel.getAdjacentTiles(currentTile);
    }

    /**
     * Gets the tiles diagonal to a given tile.
     *
     * @param currentTile the current tile
     * @return the tile array that holds all diagonal tiles
     */
    public Tile[] getDiagonalTiles(Tile currentTile) {
        return this.farmlandModel.getDiagonalTiles(currentTile);
    }

    /**
     * Checks if the farm has no crops planted.
     *
     * @return the boolean value that tracks whether the farm has no crops planted
     */
    public boolean isStaleFarm() {
        // true if no crops are planted
        for (int i = 0; i < this.farmlandModel.getWidth(); i++) {
            for (int j = 0; j < this.farmlandModel.getHeight(); j++) {
                if(this.farmlandModel.getTile(i, j) instanceof model.tiles.CropTile){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if the farm only has withered crops.
     *
     * @return the boolean value that tracks whether the farm only has withered crops
     */
    public boolean isWitheredFarm() {
        // true if all crops are withered
        for (int i = 0; i < this.farmlandModel.getWidth(); i++) {
            for (int j = 0; j < this.farmlandModel.getHeight(); j++) {
                if(this.farmlandModel.getTile(i, j) instanceof model.tiles.CropTile){
                    if(!((model.tiles.CropTile) this.farmlandModel.getTile(i, j)).getCrop().getWithered()){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Checks if the farm only has occupied tiles.
     *
     * @return the boolean value that tracks whether the farm only has occupied tiles
     */
    public boolean isFullFarm() {
        for (int i = 0; i < this.farmlandModel.getWidth(); i++) {
            for (int j = 0; j < this.farmlandModel.getHeight(); j++) {
                if(!this.farmlandModel.getTile(i, j).isOccupied()){
                    return false;
                }
            }
        }

        return true;

    }
}
