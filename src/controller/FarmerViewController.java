package controller;

import controller.entitycontrollers.ToolController;
import model.FarmerModel;
import model.crops.Crop;
import model.grades.FarmerGrade;
import model.tiles.*;
import view.FarmerView;
import view.TileView;

import javax.swing.*;

/**
 * The FarmerViewController class is responsible for handling the interactions between the farmer view and the farmer model.
 */
public class FarmerViewController {

    private FarmerView farmerView;
    private FarmerModel farmerModel;
    private Tile currentTile;
    private TileView currentTileView;
    private Crop pockets = null;
    private JPanel pocketView;
    private JLabel dayCounter;
    private int day;

    /**
     * Instantiates a new FarmerViewController.
     *
     * @param startingTileView the starting tile view
     * @param startingTile     the starting tile
     */
    public FarmerViewController(TileView startingTileView, Tile startingTile) {
        day = 0;
        this.farmerModel = new FarmerModel();
        this.currentTile = startingTile;
        this.currentTileView = startingTileView;
        this.farmerView = new FarmerView(startingTileView, startingTile);
        this.farmerView.updateFarmerView(this.farmerModel);
        this.pocketView = new JPanel();
        this.pocketView.setLayout(new BoxLayout(pocketView, BoxLayout.Y_AXIS));
        this.pocketView.setBounds(0, 100, 100, 100);
        this.pocketView.setOpaque(false);

        this.dayCounter = new JLabel("Day: " + day);
    }

    /**
     * Gets the farmer inventory.
     *
     * @return the inventory
     */
// TODO: Rework to support multiple seeds
    public Crop getInventory() {

        if(pockets == null) {
            return null;
        }
        // get crops from pockets
//        ArrayList<String> crops = new ArrayList<>();
//        crops.add(pockets.getName());
        return pockets;
    }

    /**
     * Updates the inventory view.
     */
    public void updateInventoryView(){
        this.pocketView.removeAll();
        this.pocketView.add(new JLabel("Pockets: "));
        // TODO: Rework to support multiple crops
//        for (String crop : this.getInventory()) {
//            this.pocketView.add(new JLabel(crop));
//        }
        if(this.getInventory() != null) {
            this.pocketView.add(new JLabel(this.getInventory().getName()));
        }
        this.pocketView.revalidate();
        this.pocketView.repaint();
    }

    /**
     * Gets the current tile.
     *
     * @return the current tile
     */
    public Tile getCurrentTile() {
        return currentTile;
    }

    /**
     * Adds crop to pockets.
     *
     * @param crop the crop to be added to the farmer's pockets
     */
    public void addCropToPockets(Crop crop) {
        this.pockets = crop;
    }

    /**
     * Gets crop from pockets.
     *
     * @return the crop to be taken from the farmer's pockets
     */
    public Crop getCropFromPockets() {
        return pockets;
    }

    /**
     * Changes the current tile.
     *
     * @param nextTile     the next tile
     * @param nextTileView the next tile view
     */
    public void changeCurrentTile(Tile nextTile, TileView nextTileView){
        // unset current tile
        this.currentTileView.setHasFarmer(false);
        System.out.println(this.currentTile.toString());
        this.currentTileView.updateView(this.currentTile);

        nextTileView.setHasFarmer(true);
        nextTileView.updateView(nextTile);

        // change to new tile
        this.currentTile = nextTile;
        this.currentTileView = nextTileView;
    }

    /**
     * Gives exp to the farmer.
     *
     * @param exp the exp to be given to the farmer
     */
    public void giveFarmerExp(double exp){
        this.farmerModel.gainExp(exp);
    }

    /**
     * Gives money to the farmer.
     *
     * @param money the money to be given to the farmer
     */
    public void giveFarmerMoney(double money){
        this.farmerModel.addMoney(money);
        this.updateFarmerView();
    }

    /**
     * Reduces the farmer's money given the amount to be subtracted.
     *
     * @param money the money
     */
    public void subtractFarmerMoney(double money){
        this.farmerModel.spendMoney(money);
    }

    /**
     * Remove from anchor.
     *
     * @param mainFrame the main frame
     */
    public void removeFromAnchor(JFrame mainFrame){
        mainFrame.remove(farmerView.getFarmerPanel());
    }

    /**
     * Gets the farmer model.
     *
     * @return the farmer model
     */
    public FarmerModel getFarmerModel() {
        return farmerModel;
    }

    /**
     * Updates the farmer view.
     */
    public void updateFarmerView() {
        updateInventoryView();
        this.farmerView.updateFarmerView(this.farmerModel);
    }

    /**
     * Adds the view to the anchor.
     *
     * @param mainFrame the main frame
     */
    public void addToAnchor(JFrame mainFrame) {
        mainFrame.add(this.farmerView.getFarmerPanel());
        this.dayCounter.setBounds(0, 400, 100, 100);
        this.pocketView.setBounds(0, 100, 100, 100);

        mainFrame.add(this.pocketView);
        mainFrame.add(this.dayCounter);
    }

    /**
     * Sets the current tile.
     *
     * @param tile the tile
     */
    public void setCurrentTile(Tile tile) {
        this.currentTile = tile;
    }

    /**
     * Empties the pockets of the farmer.
     */
    public void emptyPockets() {
        this.pockets = null;
        this.updateInventoryView();
        this.updateFarmerView();
    }

    /**
     * Plants a seed to a given tile.
     *
     * @param tile the tile where the seed will be planted on.
     * @return the tile
     */
    public Tile plantSeed(Tile tile) {
        if (this.pockets != null) {
            Crop crop = this.getCropFromPockets();
            tile = new CropTile(crop, tile.getX(), tile.getY());

            this.emptyPockets();
            this.updateFarmerView();
        }

        return tile;
    }

    /**
     * Makes the farmer sleep to advance to the next day.
     */
    public void sleepFarmer() {
        day += 1;
        this.dayCounter.setText("Day: " + day);

        this.dayCounter.revalidate();
        this.dayCounter.repaint();
    }

    /**
     * Levels up the farmer.
     */
    public void levelUp() {

        FarmerGrade next = this.farmerModel.getNextGrade();
        // show JOPtionPane to ask if they want to level up
        int choice = JOptionPane.showConfirmDialog(null, "Upgrade to " + next.getClass().getSimpleName() + " for $" + next.getUpgradeCost() + "?", "Level Up", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {

            // check if farmer meets balance requirements
            if(farmerModel.getBalance() >= next.getUpgradeCost()){

                // check if farmer meets level requirements
                if(farmerModel.getLevel() >= next.getLevelRequirement()){
                    this.farmerModel.setGrade(next);
                    this.farmerModel.spendMoney(next.getUpgradeCost());
                    this.updateFarmerView();
                } else {
                    JOptionPane.showMessageDialog(null, "You do not meet the level requirement");
                }
            } else {
                JOptionPane.showMessageDialog(null, "You do not meet the balance requirement");
            }


        }
    }
}
