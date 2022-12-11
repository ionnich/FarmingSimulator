package controller.factories;

import controller.entitycontrollers.*;
import model.tools.*;
import model.tools.ToolModel;

/**
 * The ToolControllerFactory class is a factory class that creates ToolControllers.
 */
public class ToolControllerFactory implements EntityFactory<ToolController, ToolModel> {

    /**
     * Creates a ToolController.
     * @param material The ToolModel where the controller will be derived from
     * @return the created ToolController
     */
    @Override
    public ToolController create(ToolModel material) {
        if(material instanceof Shovel){
            return new ShovelController();
        }
        else if(material instanceof Plow){
            return new PlowController();
        }
        else if(material instanceof WateringCan){
            return new WateringCanController();
        }
        else if(material instanceof Pickaxe){
            return new PickaxeController();
        }
        else if(material instanceof Fertilizer){
            return new FertilizerController();
        }
        else{
            return null;
        }
    }
}
