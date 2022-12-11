package controller.factories;

import model.tools.*;

/**
 * The ToolFactory class is a factory class that creates tools.
 */
public class ToolFactory implements EntityFactory<ToolModel, String> {

    /**
     * Creates a tool based on the given tool name.
     * @param material the name of the tool to be created
     * @return the created tool
     */
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
