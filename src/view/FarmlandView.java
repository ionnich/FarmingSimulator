package view;

import model.FarmlandModel;
import model.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The FarmlandView class is responsible for displaying the farmland.
 */
public class FarmlandView {
    FarmlandModel farmlandModel;
    JPanel farmlandPanel;
    TileView[][] TileViewIndex;
    int farmViewWidth = 772;
    int farmViewHeight = 382;
    int farmViewLeftPadding = 250;
    int farmViewBottomPadding = 260;


    /**
     * Instantiates a new FarmlandView.
     *
     * @param farmlandModel the farmland model
     */
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

    /**
     * Gets tile pane.
     *
     * @param x the x-coordinate of the tile pane
     * @param y the y-coordinate of the tile pane
     * @return the tile pane
     */
    public JLayeredPane getTilePane(int x, int y){
        int coord = x * this.farmlandModel.getHeight() + y;
        return (JLayeredPane) this.farmlandPanel.getComponent(coord);
    }


    /**
     * Updates the tile view.
     *
     * @param hasFarmer        boolean value that tracks if the tile is the current tile of the farmer
     * @param tile             the tile
     * @param widthcoordinate  the x-coordinate
     * @param heightcoordinate the y-coordinate
     */
    public void updateTileView(boolean hasFarmer, Tile tile, int widthcoordinate, int heightcoordinate){
        TileView tileView = this.getTileView(widthcoordinate, heightcoordinate);
        tileView.setHasFarmer(hasFarmer);
        tileView.updateView(tile);
    }

    /**
     * Updates the tile view without the farmer.
     *
     * @param tile             the tile
     * @param widthcoordinate  the x-coordinate
     * @param heightcoordinate the y-coordinate
     */
    public void updateTileView(Tile tile, int widthcoordinate, int heightcoordinate){
        TileView tileView = this.getTileView(widthcoordinate, heightcoordinate);
        tileView.updateView(tile);
    }

    /**
     * Gets the panel of the farmland.
     *
     * @return the farmland panel
     */
    public JPanel getFarmlandPanel(){
        return this.farmlandPanel;
    }

    /**
     * Gets farmland model.
     *
     * @return the farmland model
     */
    public FarmlandModel getFarmlandModel() {
        return farmlandModel;
    }
}
