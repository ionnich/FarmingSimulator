package controller;


import controller.entitycontrollers.*;
import model.FarmerModel;
import model.tiles.*;
import model.tools.ToolModel;
import view.ToolView;
import model.Report;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * The ToolViewController class is responsible for interactions between the ToolView and the ToolController.
 */
public class ToolViewController {

    private ToolController activeTool;
    private JPanel toolViewContainer;
    private HashMap<String, ToolController> toolControllerMap;


    /**
     * Instantiates a new ToolViewController.
     */
    public ToolViewController(){

        this.activeTool = null;
        this.toolControllerMap = new HashMap<>();
        this.toolViewContainer = new JPanel();

        initializeTools();
        initializeToolViewContainer();
        initializeClickEvents();
    }

    /**
     * Initializes all the tools.
     */
    public void initializeTools(){

        toolControllerMap.put("shovel", new ShovelController());
        toolControllerMap.put("plow", new PlowController());
        toolControllerMap.put("wateringcan", new WateringCanController());
        toolControllerMap.put("pickaxe", new PickaxeController());
        toolControllerMap.put("fertilizer", new FertilizerController());
    }

    /**
     * Initialize the tool view container JPanel.
     *
     * @return the JPanel
     */
    public JPanel initializeToolViewContainer(){

        this.toolViewContainer.setLayout(new GridLayout(1, 1, 10,0));
        this.toolViewContainer.setBounds(420, 670, 440, 85);
        // for each tool in the toolControllerMap, add the toolView to the toolViewContainer
        for (String toolName : toolControllerMap.keySet()) {
            toolViewContainer.add(toolControllerMap.get(toolName).getToolView());
        }
        return toolViewContainer;
    }

    /**
     * Initializes the click events.
     */
    public void initializeClickEvents(){
            // for each tool in the toolControllerMap, set equipTool as the click event
            for (String toolName : toolControllerMap.keySet()) {
                // add action listener
                toolControllerMap.get(toolName).getToolView().addActionListener(e -> equipTool(toolName));
            }
    }


    /**
     * Changes the active tool to the tool with the given name.
     *
     * @param toolName the tool name
     * @return the report that contains the result of the action
     */
// on click event that changes the active tool
    public Report equipTool(String toolName){

        // check if tool is in the toolControllerMap
        if (!toolControllerMap.containsKey(toolName)){
            System.out.println("Active tool unchanged");
            return new Report("Tool not found", false);
        }


        // unset border
        if (activeTool != null){
            activeTool.getToolView().setBorder(null);
        }
        this.activeTool = toolControllerMap.get(toolName);

        // darken the toolView of the active tool
        activeTool.getToolView().setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        System.out.println("Active tool is now: " + activeTool.getToolName());
        return new Report ("Equipped " + toolName, true);
    }

    /**
     * Adds to the anchor.
     *
     * @param anchor the anchor
     */
    public void addToAnchor(JFrame anchor){
        anchor.add(toolViewContainer);
    }

    /**
     * Removes from the anchor.
     *
     * @param anchor the anchor
     */
    public void removeFromAnchor(JFrame anchor){
        anchor.remove(toolViewContainer);
    }

    /**
     * Uses a tool on a tile.
     *
     * @param tile        the tile
     * @param farmerModel the farmer model
     * @return the resulting tile
     */
    public Tile useTool(Tile tile, FarmerModel farmerModel){

        if(activeTool instanceof WateringCanController){
            return ((WateringCanController) activeTool).waterTile(tile, farmerModel.getWateringCanLevel());
        }

        if(activeTool instanceof FertilizerController){
            return ((FertilizerController) activeTool).fertilizeTile(tile, farmerModel.getFertilizerLevel());
        }

        return activeTool.useTool(tile);
    }

    /**
     * Gets the view of the active tool.
     *
     * @return the tool view
     */
    public ToolView getActiveToolView(){
        return activeTool.getToolView();
    }

    /**
     * Gets the model of the active tool.
     *
     * @return the tool model
     */
    public ToolModel getActiveToolModel(){
        return activeTool.getToolModel();
    }

    /**
     * Tracks whether the attempt to use a tool is successful.
     *
     * @param tile          the tile where the tool will be used
     * @param farmerBalance the farmer balance
     * @return the report that contains the result of the action
     */
    public Report attemptToolUse(Tile tile, double farmerBalance){

        // guard class
        if(tile == null)
            return new Report("Tile is null", false);

        // check if farmer balance is sufficient
        if(farmerBalance < activeTool.getToolModel().getCost())
            return new Report("Insufficient funds", false);

        // check if pickaxe is equipped and tile is a rock
        if(tile instanceof RockyTile && activeTool instanceof PickaxeController)
            return new Report("Pickaxe appropriate for tile", true);

        // check if plow is equipped and tile is empty
        if(tile instanceof EmptyTile && activeTool instanceof PlowController)
            return new Report("Plow appropriate for tile", true);

        // check if watering can is equipped and tile is a crop tile
        if(tile instanceof CropTile && activeTool instanceof WateringCanController)
            return new Report("Watering can appropriate for tile", true);

        // check if fertilizer is equipped and tile is a crop tile
        if(!(tile instanceof CropTile) && activeTool instanceof FertilizerController)
            return new Report("Fertilizer can only be used on crop tile", false);

        // check if fertilizer is equipped and tile is a crop tile
        if(!(tile instanceof CropTile) && activeTool instanceof WateringCanController)
            return new Report("Watering can can only be used on crop tile", false);

        // check if watering can is equipped and tile is a crop tile
        if(tile instanceof CropTile && activeTool instanceof WateringCanController)
            return new Report("Watering can appropriate for tile", true);

        // check if fertilizer is equipped and tile is a crop tile
        if(tile instanceof CropTile && activeTool instanceof FertilizerController)
            return new Report("Fertilizer appropriate for tile", true);

        // do not check the shovel as it can be used on any tile
        return new Report("Attempt success, proceed", true);
    }

    /**
     * Gets the controller of the active tool.
     *
     * @return the tool controller of the active tool
     */
    public ToolController getActiveTool(){
        return activeTool;
    }

}
