package controller.factories;

import model.crops.*;

/**
 * The CropFactory class is a factory that creates CropModel objects.
 */
public class CropFactory implements EntityFactory<model.crops.Crop, String> {

    /**
     * Gets the crop price.
     *
     * @param selectedSeed the selected seed
     * @return the crop price of the selected seed
     */
    public double getCropPrice(String selectedSeed) {
        return create(selectedSeed).getCost();
    }

    /**
     * Creates a CropModel object.
     *
     * @param material the name of the crop to be created
     * @return the created crop model
     */
    @Override
    public model.crops.Crop create(String material) {
        return switch(material.toLowerCase()){
            case "turnip" -> new Turnip();
            case "tulips" -> new Tulips();
            case "carrot" -> new Carrot();
            case "apple" -> new Apple();
            case "potato" -> new Potato();
            case "rose" -> new Rose();
            case "sunflower" -> new Sunflower();
            case "mango" -> new Mango();
            default -> null;
        };
    }
}
