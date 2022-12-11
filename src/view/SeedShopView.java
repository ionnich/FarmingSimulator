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

    public SeedShopView(double farmerBalance) {
        seedShopFrame = new JFrame();
        seedShopFrame.setVisible(false);
        seedShopFrame.setTitle("Seed Shop");
        seedShopFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        seedShopFrame.setSize(800, 500);
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
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBounds(400, 0, 200, 100);
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

    public void updateCart() {
        if(this.getSelectedSeed() == null) {
            this.shoppingCart.setText("Shopping Cart: ");
        }
        else {
            double cropPrice = new CropFactory().getCropPrice(this.getSelectedSeed());
            this.shoppingCart.setText("Shopping Cart: " + this.getSelectedSeed() + " - $" + cropPrice);
        }
    }

    public void updateBag(String crop) {
        if(crop == null) {
            this.bag.setText("Pockets: ");
        }
        else {
            this.bag.setText("Pockets: " + crop);
        }
    }

    public void updateBalance(double balance) {
        this.balance = balance;
        this.balanceLabel.setText("Balance: " + balance);
    }

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

    public void createActionPanel(
            ActionListener buyListener,
            ActionListener backListener) {
        buyButton = new JButton("Buy");
        backButton = new JButton("Back");

        buyButton.addActionListener(buyListener);
        backButton.addActionListener(backListener);

        actionPanel.add(buyButton);
        actionPanel.add(backButton);
    };
    public void initialize() {
        seedShopFrame.setLayout(null);
        seedShopFrame.add(cropPanel);
        seedShopFrame.add(infoPanel);
        seedShopFrame.add(actionPanel);
    }

    public void show() {
        seedShopFrame.setVisible(true);
    }
    public void hide() {
        seedShopFrame.setVisible(false);
    }

    public String getSelectedSeed() {
        return this.selectedSeed;
    }

    public void setSelectedSeed(String seedName) {
        this.selectedSeed = seedName;
        updateCart();
    }
}
