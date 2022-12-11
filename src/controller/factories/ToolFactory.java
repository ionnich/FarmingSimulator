package controller.factories;

import model.tools.*;

public class ToolFactory implements EntityFactory<ToolModel, String> {

    @Override
    public ToolModel create(String material) {
        return switch (material.toUpperCase()) {
            case "PICKAXE" -> new Pickaxe();
            case "FERTILIZER" -> new Fertilizer();
            case "PLOW" -> new Plow();
            case "WATERINGCAN" -> new WateringCan();
            default -> null;
        };
    }
}
