package view.factories;

public class CropAssetFactory implements AssetFactory<String, String> {
    @Override
    public String fetch(String asset) {
        System.out.println("Fetching asset: " + asset);
        return "/resources/graphics/tiles/croptiles/" + asset + ".png";
    }
}
