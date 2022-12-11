package view;

import model.FarmlandModel;
import model.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FarmlandView {

    FarmlandModel farmlandModel;
    JPanel farmlandPanel;

    TileView[][] TileViewIndex;

    int farmViewWidth = 772;
    int farmViewHeight = 382;

    int farmViewLeftPadding = 250;
    int farmViewBottomPadding = 260;

    public FarmlandView(FarmlandModel farmlandModel) {
        this.farmlandModel = farmlandModel;

        this.TileViewIndex = new TileView[10][5];
        this.farmlandPanel = new JPanel();
        this.farmlandPanel.setOpaque(false);
        this.farmlandPanel.setBounds(farmViewLeftPadding, farmViewBottomPadding, farmViewWidth, farmViewHeight);

        // set gridlayout for tiles
        this.farmlandPanel.setLayout(new GridLayout(
                        farmlandModel.getHeight(),
                        farmlandModel.getWidth(),
                        8,
                        8
        ));

        // create tile views
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 10; x++) {
                Tile tile = farmlandModel.getTile(x, y);
                TileView tileView = new TileView(tile);
                tileView.setX(x);
                tileView.setY(x);
                TileViewIndex[x][y] = tileView;
                this.farmlandPanel.add(tileView.getTileView());
            }
        }
    }

    public TileView getTileView(int x, int y){
        // get the tile on the x's and y's position
        return this.TileViewIndex[x][y];
    }

    public JLayeredPane getTilePane(int x, int y){
        int coord = x * this.farmlandModel.getHeight() + y;
        return (JLayeredPane) this.farmlandPanel.getComponent(coord);
    }


    public void updateTileView(boolean hasFarmer, Tile tile, int widthcoordinate, int heightcoordinate){
        TileView tileView = this.getTileView(widthcoordinate, heightcoordinate);
        tileView.setHasFarmer(hasFarmer);
        tileView.updateView(tile);
    }

    public void updateTileView(Tile tile, int widthcoordinate, int heightcoordinate){
        TileView tileView = this.getTileView(widthcoordinate, heightcoordinate);
        tileView.updateView(tile);
    }
    public JPanel getFarmlandPanel(){
        return this.farmlandPanel;
    }

    public FarmlandModel getFarmlandModel() {
        return farmlandModel;
    }
}
