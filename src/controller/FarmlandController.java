package controller;

import model.FarmlandModel;
import model.tiles.Tile;
import view.FarmlandView;
import view.TileView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FarmlandController {
    FarmlandView farmlandView;
    FarmlandModel farmlandModel;

    public FarmlandController(){
        this.farmlandModel = new FarmlandModel(10, 5);
        this.farmlandView = new FarmlandView(
                this.farmlandModel
        );
        this.addTileEvents();
    }

    public Tile getTile(int x, int y){
        if(x < 0) x = 0;
        if(x > this.farmlandModel.getWidth() - 1) x = this.farmlandModel.getWidth() - 1;

        if(y < 0) y = 0;
        if(y > this.farmlandModel.getHeight() - 1) y = this.farmlandModel.getHeight() - 1;
        return farmlandModel.getTile(x, y);
    }

    public TileView getTileView(int x, int y){
        if(x < 0) x = 0;
        if(x > this.farmlandModel.getWidth() - 1) x = this.farmlandModel.getWidth() - 1;

        if(y < 0) y = 0;
        if(y > this.farmlandModel.getHeight() - 1) y = this.farmlandModel.getHeight() - 1;
        return this.farmlandView.getTileView(x, y);
    }

    public void mutateTile(boolean hasFarmer, Tile newtTile, int x, int y){
        this.farmlandModel.mutateTile(newtTile, x, y);
        this.farmlandView.updateTileView(hasFarmer, newtTile, x, y);
    }

    public void updateTileViews(){
        for (int i = 0; i < this.farmlandModel.getWidth(); i++) {
            for (int j = 0; j < this.farmlandModel.getHeight(); j++) {
                this.farmlandView.updateTileView(this.farmlandModel.getTile(i, j), i, j);
            }
        }
    }

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

    public FarmlandView getFarmlandView() {
        return farmlandView;
    }

    public void addToAnchor(JFrame anchor){
        anchor.add(farmlandView.getFarmlandPanel());
    }
    public void removeFromAnchor(JFrame anchor){
        anchor.remove(farmlandView.getFarmlandPanel());
    }

    public void advanceDay() {
        this.farmlandModel.advanceDay();
        this.updateTileViews();
    }

    public void replaceTile(Tile tile) {
        this.farmlandModel.replaceTile(tile);
        this.updateTileViews();
    }

    public Tile[] getAdjacentTiles(Tile currentTile) {
        return this.farmlandModel.getAdjacentTiles(currentTile);
    }

    public Tile[] getDiagonalTiles(Tile currentTile) {
        return this.farmlandModel.getDiagonalTiles(currentTile);
    }

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
