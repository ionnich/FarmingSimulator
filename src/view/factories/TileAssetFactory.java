package view.factories;

import model.crops.Crop;
import model.tiles.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class TileAssetFactory implements AssetFactory<Tile, ImageIcon>{

    // get current directory
    private String tileAssetDir = "/resources/graphics/tiles/";
    int unplowedTileCount = 2;
    @Override
    public ImageIcon fetch(Tile asset) {
        BufferedImage src = null;
        Random randomizer = new Random();

        if (asset instanceof EmptyTile) {
            // get random unplowed tile from unplowedtiles asset folder
            int unplowedIndex = randomizer.nextInt(unplowedTileCount) + 1;
            String unplowedTileAssetDir = "";
            tileAssetDir += unplowedTileAssetDir + unplowedIndex + ".png";
        }
        else if(asset instanceof RockyTile){
            tileAssetDir = tileAssetDir + "rocks.png";
        }
        else if(asset instanceof PlowedTile){
            tileAssetDir = tileAssetDir + "plowedtiles/plowed.png";
        }
        else if(asset instanceof WitherTile){
            tileAssetDir = tileAssetDir + "withered.png";
        }
        else if(asset instanceof RootTile){
            if(((RootTile) asset).isDissolved()){
                String unplowedTileAssetDir = "";
                tileAssetDir += unplowedTileAssetDir + 1 + ".png";
            }
            else {
                tileAssetDir = tileAssetDir + "roots.png";
            }
        }
        else if(asset instanceof CropTile){
            if(((CropTile) asset).getCrop().isHarvestable()){
                CropAssetFactory cap = new CropAssetFactory();
                tileAssetDir = cap.fetch(((CropTile) asset).getCrop().getName());
            }
            else{
                if(((CropTile) asset).getCrop().isWatered())
                    tileAssetDir = tileAssetDir + "croptiles/basecropwatered.png";
                else
                    tileAssetDir = tileAssetDir + "croptiles/basecrop.png";
            }
        }
        tileAssetDir = tileAssetDir.toLowerCase();
        try {
            src = ImageIO.read(getClass().getResource(tileAssetDir));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ImageIcon(src);
    }
}
