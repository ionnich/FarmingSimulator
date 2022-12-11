package controller.factories;

import controller.entitycontrollers.*;
import model.tools.*;
import model.tools.ToolModel;

public class ToolControllerFactory implements EntityFactory<ToolController, ToolModel> {

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
