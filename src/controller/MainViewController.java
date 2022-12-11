package controller;

import controller.entitycontrollers.ToolController;
import model.FarmlandModel;
import model.Report;
import model.crops.Crop;
import model.tiles.*;
import view.FarmlandView;
import view.MainView;
import view.SeedShopView;
import view.TileView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MainViewController {

    private JFrame mainFrame;
    private JLabel mainBackground;
    private JPanel buttonPanel;
    private JButton sleepBtn, plantBtn, levelupBtn, storeBtn, harvestBtn;
    private JButton discardSeedBtn;
    private JButton useToolBtn;
    private ImageIcon logo;

    private Boolean isRunning;

    private FarmlandController farmlandController;
    private FarmerViewController farmerViewController;
    private ToolViewController toolViewController;
    private SeedShopController seedShopController;

    private FarmerKeyListener farmerListener;

    public MainViewController() {

        this.isRunning = true;
        MainView mainView = new MainView();
        this.mainFrame = mainView.getMainFrame();

        initializeControllers();

        this.mainBackground = new JLabel();
        // initialize the background
        ImageIcon backgroundIcon = new ImageIcon("resources/Grass.png");
        Image src = backgroundIcon.getImage();
        backgroundIcon = new ImageIcon(
                src.getScaledInstance(1280, 800, Image.SCALE_SMOOTH));

        // set the background
        this.mainBackground.setIcon(backgroundIcon);
        this.mainBackground.setBounds(0, 0, 1280, 800);

        initGame();
    }

    public void initializeControllers() {

        this.farmlandController = new FarmlandController();
        farmlandController.addToAnchor(this.mainFrame);
        this.toolViewController = new ToolViewController();
        this.toolViewController.addToAnchor(this.mainFrame);

        // get the farmer
        this.farmerViewController = new FarmerViewController(
                this.farmlandController.getTileView(0, 0),
                this.farmlandController.getTile(0, 0));

        this.seedShopController = new SeedShopController(this.farmerViewController);

        this.farmerViewController.updateFarmerView();
        this.farmerViewController.addToAnchor(this.mainFrame);
        instantiateButtons();
        this.mainFrame.add(this.buttonPanel);
    }

    private void initGame() {
        this.farmlandController.updateTileViews();
        this.farmerListener = new FarmerKeyListener(
                this.farmerViewController,
                this.farmlandController
        );

        this.initFarmerMovement();

        this.mainFrame.setVisible(true);
    }

    public void instantiateButtons() {
        this.buttonPanel = new JPanel();
        // place panel to the right
        buttonPanel.setBounds(1100, 50, 100, 400);
        buttonPanel.setLayout(new GridLayout(8, 1));
        buttonPanel.setOpaque(false);

        this.useToolBtn = new JButton("Use Tool");
        this.useToolBtn.setSize(100, 50);
        this.useToolBtn.addActionListener(e -> {
            System.out.println("Tool use invoked");
            useToolWrapper(
                    this.farmerViewController.getCurrentTile()
            );
        });
        buttonPanel.add(this.useToolBtn);

        this.storeBtn = new JButton("Store");
        this.useToolBtn.setSize(100, 50);
        this.storeBtn.addActionListener(e -> {
            System.out.println("Store button clicked");
            this.seedShopController.showSeedShop();
        });
        buttonPanel.add(this.storeBtn);

        this.plantBtn = new JButton("Plant");
        this.plantBtn.setSize(100, 50);
        this.plantBtn.addActionListener(e -> {
            System.out.println("Plant button clicked");
            this.plantWrapper(
                    this.farmerViewController.getCurrentTile()
            );
        });
        buttonPanel.add(this.plantBtn);

        this.discardSeedBtn = new JButton("Discard Seed");
        this.discardSeedBtn.setSize(100, 50);
        this.discardSeedBtn.addActionListener(e -> {
            System.out.println("Discard Seed button clicked");
            farmerViewController.emptyPockets();
        });
        buttonPanel.add(this.discardSeedBtn);

        this.sleepBtn = new JButton("Sleep");
        this.sleepBtn.setSize(100, 50);
        this.sleepBtn.addActionListener(e -> {
            System.out.println("Sleep button clicked");
            Report dayReport = this.advanceDay();

            JOptionPane.showMessageDialog(
                    this.mainFrame,
                    dayReport.getStatus(),
                    "Day Report",
                    JOptionPane.INFORMATION_MESSAGE
            );


            if(!dayReport.isSuccess()){

                JOptionPane.showMessageDialog(
                        this.mainFrame,
                        "You have lost the game!",
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // end the game
                System.exit(0);
            }

        });
        buttonPanel.add(this.sleepBtn);

        this.harvestBtn = new JButton("Harvest");
        this.harvestBtn.setSize(100, 50);
        this.harvestBtn.addActionListener(e -> {
            System.out.println("Harvest button clicked");
            // check if the farmer is on a tile with a crop
            Tile currentTile = this.farmerViewController.getCurrentTile();
            if (currentTile instanceof CropTile) {
                // check if the crop is ready to harvest

                CropTile pendingHarvest = (CropTile) currentTile;

                if (((CropTile) currentTile).getCrop().isHarvestable()) {

                    int yield = pendingHarvest.getCrop().getHarvestYield();
                    double gains = yield + (pendingHarvest.getCrop().getBasePrice() + farmerViewController.getFarmerModel().marketBonus());
                    double waterBonus = gains * 0.2 * (pendingHarvest.getCrop().getWaterLevel() - 1);
                    double fertilizerBonus = gains * 0.5 * (pendingHarvest.getCrop().getFertilizerLevel());
                    gains += waterBonus + fertilizerBonus;

                    // if flower crop, add 10% of the gains to the farmer's happiness
                    if (pendingHarvest.getCrop().getType().contains("flower")) {
                        gains *= 1.1;
                    }
                    // harvest the crop
                    this.farmerViewController.giveFarmerMoney(
                            gains
                    );

                    currentTile = new EmptyTile(currentTile.getX(), currentTile.getY());
                    // set current tile to empty
                    this.farmlandController.replaceTile(
                            currentTile
                    );

                    // update the tile view
                    this.farmlandController.updateTileViews();

                    // show JOptionPane
                    JOptionPane.showMessageDialog(
                            this.mainFrame,
                            "You harvested " + yield + " crop for $" + gains,
                            "Harvest",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
        buttonPanel.add(this.harvestBtn);

        this.levelupBtn = new JButton("Level Up");
        this.levelupBtn.setSize(100, 50);
        this.levelupBtn.addActionListener(e -> {
            System.out.println("Level Up button clicked");
            this.farmerViewController.levelUp();
        });
        buttonPanel.add(this.levelupBtn);
    }

    private Report advanceDay() {
        // update the day counter
        // update the farmland
        this.farmlandController.advanceDay();
        this.farmerViewController.sleepFarmer();
        this.farmerViewController.updateFarmerView();

        double cheapestSeed = this.seedShopController.getCheapestSeed();
        double shovelCost = 7;
        boolean staleFarm = this.farmlandController.isStaleFarm();
        boolean witheredFarm = this.farmlandController.isWitheredFarm();
        boolean fullFarm = this.farmlandController.isFullFarm();

        boolean brokeFarmer = this.farmerViewController.getFarmerModel().getBalance() < cheapestSeed;
        boolean noShovel = this.farmerViewController.getFarmerModel().getBalance() < shovelCost;

        // check for game over
        if (brokeFarmer && staleFarm) {
            return new Report("You woke up fearing bankruptcy\nYour farm grows nothing and you are too poor to do anything", false);
        }

        if(brokeFarmer && witheredFarm) {
            return new Report("You woke up surrounded by zombie plants\nYour farm's growing deadplants. The dead don't make money.", false);
        }

        if(brokeFarmer && fullFarm && noShovel) {
            return new Report("You were woken up by shovel nightmares\nYour farm's in shatters and your shovel eludes you. So sad.", false);
        }

        return new Report("You have slept for a day", true);
    }



    private void plantWrapper(Tile currentTile) {
        if (currentTile instanceof PlowedTile) {
            if (this.farmerViewController.getCropFromPockets() != null) {

                // check if the crop is a tree
                Crop crop = this.farmerViewController.getCropFromPockets();
                if(crop.getType().contains("Fruit Tree")){
                    // perform checks
                    // check if the tile is adjacent to the edge
                    if(currentTile.getX() == 0 || currentTile.getX() >= 9 || currentTile.getY() == 0 || currentTile.getY() >= 4){
                        JOptionPane.showMessageDialog(
                                this.mainFrame,
                                "You cannot plant a tree on the edge of the farm",
                                "Plant",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    // check if adjacent tiles are empty
                    Tile[] adjacentTiles = this.farmlandController.getAdjacentTiles(currentTile);
                    for(Tile tile : adjacentTiles){
                        if(!((tile instanceof EmptyTile) || (tile instanceof PlowedTile))){
                            JOptionPane.showMessageDialog(
                                    this.mainFrame,
                                    "The tree does not have enough space to grow on this tile",
                                    "Plant",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }
                    }

                    Tile[] diamondTiles = this.farmlandController.getDiagonalTiles(currentTile);

                    for (Tile tile : diamondTiles){
                        if(!((tile instanceof EmptyTile) || (tile instanceof PlowedTile))){
                            JOptionPane.showMessageDialog(
                                    this.mainFrame,
                                    "The tree does not have enough space to grow on the diagonal",
                                    "Plant",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }
                    }
                    // plant the tree
                    for (Tile tile : adjacentTiles){
                        this.farmlandController.replaceTile(
                                new RootTile(crop, tile.getX(), tile.getY()));
                    }

                    for (Tile tile : diamondTiles){
                        this.farmlandController.replaceTile(
                                new RootTile(crop, tile.getX(), tile.getY()));
                    }
                }
                Tile tile = this.farmerViewController.plantSeed(currentTile);
                this.farmlandController.replaceTile(tile);
            } else {
                JOptionPane.showMessageDialog(this.mainFrame, "You have no seeds to plant!");
            }
        }
        else if(currentTile instanceof EmptyTile){
            JOptionPane.showMessageDialog(this.mainFrame, "You can't plant on an unplowed tile!");
        }
         else {
            JOptionPane.showMessageDialog(
                    this.mainFrame,
                    "You can't plant on a tile that already has a crop",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public Tile[][] getFarmland(){
        return this.farmlandController.farmlandModel.getTiles();
    }

    public void useToolWrapper(Tile tile){

        Report attempt = this.toolViewController.attemptToolUse(
                tile,
                this.farmerViewController.getFarmerModel().getBalance()
                );

        System.out.println(attempt.getStatus());

        if(attempt.isSuccess()){
            // update views and models

            // update farmer
            this.farmerViewController.giveFarmerExp(this.toolViewController.getActiveToolModel().getExp());
            this.farmerViewController.subtractFarmerMoney(this.toolViewController.getActiveToolModel().getCost());
            this.farmerViewController.updateFarmerView();

            // replace the tile with the new tile
            tile = toolViewController.useTool(tile, farmerViewController.getFarmerModel());
            this.farmlandController.mutateTile(true, tile, tile.getX(), tile.getY());
            this.farmerViewController.setCurrentTile(tile);
            System.out.println("Tile mutated");
        }
    }

    public void initFarmerMovement(){

        this.mainFrame.getRootPane().getActionMap().put("mvRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                farmerListener.moveFarmerRight();
            }
        });

        this.mainFrame.getRootPane().getActionMap().put("mvLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                farmerListener.moveFarmerLeft();
            }
        });

        this.mainFrame.getRootPane().getActionMap().put("mvUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                farmerListener.moveFarmerUp();
            }
        });

        this.mainFrame.getRootPane().getActionMap().put("mvDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                farmerListener.moveFarmerDown();
            }
        });

        this.mainFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "mvRight");

        this.mainFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "mvLeft");

        this.mainFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "mvDown");

        this.mainFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "mvUp");

    }

}

