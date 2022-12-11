package view;

import model.FarmerModel;
import model.tiles.Tile;

import javax.swing.*;

/**
 * The FarmerView class is responsible for displaying the farmer and the farmer stats.
 */
public class FarmerView {
    JPanel farmerPanel;
    private int day;

    /**
     * Initializes the farmer view.
     * @param startingTileView the starting tile view of the farmer
     * @param startingTile the starting tile of the farmer
     */
    public FarmerView(TileView startingTileView, Tile startingTile) {
        this.farmerPanel = new JPanel();
        this.farmerPanel.setSize(150, 150);
        this.farmerPanel.setLayout(new BoxLayout(this.farmerPanel, BoxLayout.Y_AXIS));
        this.farmerPanel.setOpaque(false);

        startingTileView.setHasFarmer(true);
        startingTileView.updateView(startingTile);
    }

    /**
     * Gets the farmer panel.
     * @return the farmer panel
     */
    public JPanel getFarmerPanel() {
        return this.farmerPanel;
    }

    /**
     * Updates the farmer view.
     * @param farmerModel the farmer model
     */
    public void updateFarmerView(FarmerModel farmerModel) {

        System.out.println("adding farmer to tile");

        this.farmerPanel.removeAll();
        this.farmerPanel.add(new JLabel("Name: " + farmerModel.getName()));
        this.farmerPanel.add(new JLabel("Money: " + farmerModel.getBalance()));
        this.farmerPanel.add(new JLabel("Exp: " + farmerModel.getExp()));
        this.farmerPanel.add(new JLabel("Level: " + farmerModel.getLevel()));
        this.farmerPanel.add(new JLabel("Grade: " + farmerModel.getGrade()));

        System.out.println(farmerPanel.getComponentCount());

        this.farmerPanel.revalidate();
        this.farmerPanel.repaint();
    }
}
