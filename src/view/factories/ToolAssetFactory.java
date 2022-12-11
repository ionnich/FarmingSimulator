package view.factories;

import model.tools.ToolModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class ToolAssetFactory implements AssetFactory<ToolModel, ImageIcon> {
    @Override
    public ImageIcon fetch(ToolModel tool) {
        BufferedImage src = null;

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
