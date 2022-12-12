package view.factories;

import model.tools.ToolModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The ToolAssetFactory class is used to assign tool assets for the game.
 */
public class ToolAssetFactory implements AssetFactory<ToolModel, ImageIcon> {

    /**
     * Fetches the location of the tool asset.
     * @param tool the tool assigned to the asset
     * @return the asset
     */
    @Override
    public ImageIcon fetch(ToolModel tool) {
        BufferedImage src;

        try {
            String toolAssetDir = "/resources/graphics/tools/";
            String toolAssetName = tool.getClass().getSimpleName().toLowerCase() + ".png";
            src = ImageIO.read(getClass().getResource(toolAssetDir + toolAssetName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ImageIcon(src);
    }
}
