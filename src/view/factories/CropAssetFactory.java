package view.factories;

/**
 * The CropAssetFactory class is used to assign crop assets for the game.
 */
public class CropAssetFactory implements AssetFactory<String, String> {

    /**
     * Fetches the location of the crop asset.
     *
     * @param asset the crop asset
     * @return the location of the crop asset
     */
    @Override
    public String fetch(String asset) {
        System.out.println("Fetching asset: " + asset);
        return "/resources/graphics/tiles/croptiles/" + asset + ".png";
    }
}
