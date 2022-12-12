package view;

import model.tiles.*;
import view.factories.TileAssetFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The TileView class is responsible for displaying the tiles.
 */
public class TileView {

    private JLayeredPane tileViewContainer;
    private JLabel tileViewLabel;

    private int x;
    private int y;

    private boolean hasFarmer;

    /**
     * Instantiates a new TileView.
     *
     * @param tile the tile
     */
    public TileView(Tile tile) {
        this.tileViewLabel = new JLabel();
        this.tileViewContainer = new JLayeredPane();
        this.hasFarmer = false;

        this.tileViewContainer.setSize(80, 80);
        this.tileViewLabel.setSize(80, 80);
        this.tileViewLabel.setOpaque(false);

        updateView(tile);
    }

    /**
     * Sets has farmer.
     *
     * @param hasFarmer boolean value that tracks if the farmer is on the tile
     */
    public void setHasFarmer(boolean hasFarmer){
        this.hasFarmer = hasFarmer;
    }

    /**
     * Updates the view.
     *
     * @param tile the tile
     */
    public void updateView(Tile tile){

        if(tile == null){
            System.out.println("tile is null");
        }

        // empty tileview
        this.tileViewContainer.removeAll();

        TileAssetFactory tileAssetFactory = new TileAssetFactory();


        Image asset = tileAssetFactory.fetch(tile).getImage();

        Image scaled = asset.getScaledInstance(64, 64, Image.SCALE_SMOOTH);

        this.tileViewLabel.setIcon(new ImageIcon(scaled));
        this.tileViewContainer.add(this.tileViewLabel, 0, 0);

        if(this.hasFarmer){

            BufferedImage src;
            try {
                String farmerAssetDir = "/resources/graphics/farmer/farmerbase.png";
                src = ImageIO.read(getClass().getResource(farmerAssetDir));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            JLabel farmerLabel = new JLabel();
            farmerLabel.setBounds(10, 0, 80, 80);
            ImageIcon farmerAsset = new ImageIcon(src.getScaledInstance(48, 48, Image.SCALE_SMOOTH));
            farmerLabel.setIcon(farmerAsset);
            farmerLabel.setOpaque(false);

            // add farmerLabel to the next layer
            this.tileViewContainer.add(farmerLabel, 1, 0);
        }

        if(!hasFarmer){
            // check if farmer asset is on tileview
            if(this.tileViewContainer.getComponentCount() > 1){
                tileViewContainer.remove(1);
            }
        }

        this.tileViewContainer.revalidate();
        this.tileViewContainer.repaint();

    }

    /**
     * Gets tile view.
     *
     * @return the tile view
     */
    public JLayeredPane getTileView() {
        return this.tileViewContainer;
    }

    /**
     * Sets the x-coordinate.
     *
     * @param x the x-coordinate
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Sets the y-coordinate.
     *
     * @param y the y-coordinate
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Gets the x-coordinate.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate.
     *
     * @return the y-coordinate
     */
    public int getY(){
       return y;
    }
}

