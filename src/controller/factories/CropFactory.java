package controller.factories;

import model.crops.*;

public class CropFactory implements EntityFactory<model.crops.Crop, String> {

    public double getCropPrice(String selectedSeed) {
        return create(selectedSeed).getCost();
    }

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
