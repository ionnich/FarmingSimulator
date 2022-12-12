package view;

import controller.factories.CropFactory;
import model.SeedShopModel;
import model.crops.Crop;
import view.factories.CropAssetFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * The SeedShopView class is responsible for displaying the seed shop.
 */
public class SeedShopView {

    private JFrame seedShopFrame;
    private JPanel cropPanel;

    private JPanel infoPanel;
    private JPanel actionPanel;

    private JLabel balanceLabel;
    private JLabel shoppingCart;

    private JLabel bag;

    private JButton buyButton;
    private JButton backButton;

    private String selectedSeed;
    private double balance;

    /**
     * Instantiates a new SeedShopView.
     *
     * @param farmerBalance the farmer balance
     */
    public SeedShopView(double farmerBalance) {
        seedShopFrame = new JFrame();
        seedShopFrame.setVisible(false);
        seedShopFrame.setTitle("Seed Shop");
        seedShopFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        seedShopFrame.setSize(800, 800);
        seedShopFrame.setResizable(false);
        seedShopFrame.setLayout(null);

        cropPanel = new JPanel();
        actionPanel = new JPanel();
        balanceLabel = new JLabel("Balance: " + farmerBalance);
        shoppingCart = new JLabel("Shopping Cart: ");
        bag = new JLabel("Bag: ");

        balanceLabel.setBounds(0, 0, 50, 50);
        shoppingCart.setBounds(0, 50, 50, 50);
        bag.setBounds(0, 100, 50, 50);

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 1));
        infoPanel.setBounds(500, 0, 200, 100);
        infoPanel.add(balanceLabel);
        infoPanel.add(shoppingCart);
        infoPanel.add(bag);

        // set layouts as column
        cropPanel.setLayout(new GridLayout(8, 1));
        // place cropPanel to the left
        cropPanel.setBounds(0, 0, 250, 500);

        // place actionPanel to the right
        actionPanel.setBounds(250, 0, 250, 500);
        actionPanel.setLayout(new GridLayout(3, 1));
    }

    /**
     * Updates the cart.
     */
    public void updateCart() {
        if(this.getSelectedSeed() == null) {
            this.shoppingCart.setText("Shopping Cart: ");
        }
        else {
            double cropPrice = new CropFactory().getCropPrice(this.getSelectedSeed());
            this.shoppingCart.setText("Shopping Cart: " + this.getSelectedSeed() + " - $" + cropPrice);
        }
    }

    /**
     * Updates the bag.
     *
     * @param crop the bought crop
     */
    public void updateBag(String crop) {
        if(crop == null) {
            this.bag.setText("Pockets: ");
        }
        else {
            this.bag.setText("Pockets: " + crop);
        }
    }

    /**
     * Updates farmer balance.
     *
     * @param balance the balance
     */
    public void updateBalance(double balance) {
        this.balance = balance;
        this.balanceLabel.setText("Balance: " + balance);

        this.balanceLabel.revalidate();
        this.balanceLabel.repaint();
    }

    /**
     * Creates a crop panel.
     *
     * @param catalogue the catalogue of seeds
     */
    public void createCropPanel(ArrayList<String> catalogue) {

        for (String crop : catalogue) {
            JButton cropButton = new JButton(crop);
            cropButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    setSelectedSeed(crop);
                    System.out.println("Selected seed: " + crop);
                }
            });
            cropPanel.add(cropButton);
        }
    }

    /**
     * Creates the action panel.
     *
     * @param buyListener  the listener for the buy button
     * @param backListener the listener for the back button
     */
    public void createActionPanel(
            ActionListener buyListener,
            ActionListener backListener) {
        buyButton = new JButton("Buy");
        buyButton.setSize(50, 50);
        buyButton.setOpaque(false);


        backButton = new JButton("Back");
        backButton.setSize(50, 50);
        backButton.setOpaque(false);

        buyButton.addActionListener(buyListener);
        backButton.addActionListener(backListener);

        actionPanel.add(buyButton);
        actionPanel.add(backButton);
    };

    /**
     * Initializes the seed shop.
     */
    public void initialize() {
        seedShopFrame.setLayout(null);
        seedShopFrame.add(cropPanel);
        seedShopFrame.add(infoPanel);
        seedShopFrame.add(actionPanel);
    }

    /**
     * Displays the seed shop.
     */
    public void show() {
        seedShopFrame.setVisible(true);
    }

    /**
     * Hides the seed shop.
     */
    public void hide() {
        seedShopFrame.setVisible(false);
    }

    /**
     * Gets the selected seed.
     *
     * @return the name of the selected seed
     */
    public String getSelectedSeed() {
        return this.selectedSeed;
    }

    /**
     * Sets the selected seed.
     *
     * @param seedName the seed name
     */
    public void setSelectedSeed(String seedName) {
        this.selectedSeed = seedName;
        updateCart();
    }
}
