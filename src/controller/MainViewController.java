package controller;

import model.Report;
import model.crops.Crop;
import model.tiles.*;
import view.MainView;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


/**
 * The MainViewController class is the controller of the MainView class.
 */
public class MainViewController {
    private JFrame mainFrame;
    private JPanel buttonPanel;
    private FarmlandController farmlandController;
    private FarmerViewController farmerViewController;
    private ToolViewController toolViewController;
    private SeedShopController seedShopController;
    private FarmerKeyListener farmerListener;

    /**
     * Instantiates a new MainViewController.
     */
    public MainViewController() throws LineUnavailableException, UnsupportedAudioFileException, IOException {

        Boolean isRunning = true;
        MainView mainView = new MainView();
        this.mainFrame = mainView.getMainFrame();

        initializeControllers();

        JLabel mainBackground = new JLabel();
        // initialize the background
        ImageIcon backgroundIcon = new ImageIcon("resources/Grass.png");
        Image src = backgroundIcon.getImage();
        backgroundIcon = new ImageIcon(
                src.getScaledInstance(1280, 800, Image.SCALE_SMOOTH));

        // set the background
        mainBackground.setIcon(backgroundIcon);
        mainBackground.setBounds(0, 0, 1280, 800);


        initGame();

        // initialize music
        Clip clip = AudioSystem.getClip();
        // getAudioInputStream() also accepts a File or InputStream
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src/resources/sounds/bgmusic.wav"));
        clip.open(inputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        SwingUtilities.invokeLater(() -> {
            // A GUI element to prevent the Clip's daemon Thread
            // from terminating at the end of the main()
            JOptionPane.showMessageDialog(null, "Close to exit the music <3");
        });
    }
    /**
     * Initializes all controllers.
     */
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

    /**
     * Initializes the game.
     */
    private void initGame() {
        this.farmlandController.updateTileViews();
        this.farmerListener = new FarmerKeyListener(
                this.farmerViewController,
                this.farmlandController
        );

        this.initFarmerMovement();

        this.mainFrame.setVisible(true);
    }

    /**
     * Instantiates the buttons.
     */
    public void instantiateButtons() {
        this.buttonPanel = new JPanel();
        // place panel to the right
        buttonPanel.setBounds(1100, 50, 100, 400);
        buttonPanel.setLayout(new GridLayout(8, 1));
        buttonPanel.setOpaque(false);

        JButton useToolBtn = new JButton("Use Tool");
        useToolBtn.setSize(100, 50);
        useToolBtn.addActionListener(e -> {
            System.out.println("Tool use invoked");
            useToolWrapper(
                    this.farmerViewController.getCurrentTile()
            );
        });
        buttonPanel.add(useToolBtn);

        JButton storeBtn = new JButton("Store");
        useToolBtn.setSize(100, 50);
        storeBtn.addActionListener(e -> {
            System.out.println("Store button clicked");
            this.seedShopController.showSeedShop();
        });
        buttonPanel.add(storeBtn);

        JButton plantBtn = new JButton("Plant");
        plantBtn.setSize(100, 50);
        plantBtn.addActionListener(e -> {
            System.out.println("Plant button clicked");
            this.plantWrapper(
                    this.farmerViewController.getCurrentTile()
            );
        });
        buttonPanel.add(plantBtn);

        JButton discardSeedBtn = new JButton("Discard Seed");
        discardSeedBtn.setSize(100, 50);
        discardSeedBtn.addActionListener(e -> {
            System.out.println("Discard Seed button clicked");
            farmerViewController.emptyPockets();
        });
        buttonPanel.add(discardSeedBtn);

        JButton sleepBtn = new JButton("Sleep");
        sleepBtn.setSize(100, 50);
        sleepBtn.addActionListener(e -> {
            System.out.println("Sleep button clicked");
            Report dayReport = this.advanceDay();

            JOptionPane.showMessageDialog(
                    this.mainFrame,
                    dayReport.getStatus(),
                    "Day Report",
                    JOptionPane.INFORMATION_MESSAGE
            );


            if(!dayReport.isSuccess()){

                AudioInputStream failureStream;
                Clip failureClip;

                try {
                    failureStream = AudioSystem.getAudioInputStream(new File("src/resources/sounds/youfailed.wav"));
                    failureClip = AudioSystem.getClip();
                    failureClip.open(failureStream);
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }

                failureClip.loop(Clip.LOOP_CONTINUOUSLY);
                SwingUtilities.invokeLater(() -> {
                    // A GUI element to prevent the Clip's daemon Thread
                    // from terminating at the end of the main()
                    JOptionPane.showMessageDialog(null, "Don't close this, please be sad </3");
                    System.out.println("You failed music playing");
                });


                JOptionPane.showMessageDialog(
                        this.mainFrame,
                        "You have lost the game!",
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // play failure music to make the player sad
                // end the game
                System.exit(0);
            }

        });
        buttonPanel.add(sleepBtn);

        JButton harvestBtn = new JButton("Harvest");
        harvestBtn.setSize(100, 50);
        harvestBtn.addActionListener(e -> {
            System.out.println("Harvest button clicked");
            // check if the farmer is on a tile with a crop
            Tile currentTile = this.farmerViewController.getCurrentTile();
            if (currentTile instanceof CropTile pendingHarvest) {
                // check if the crop is ready to harvest

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

                    // give farmer money
                    this.farmerViewController.giveFarmerMoney(
                            gains
                    );
                    // give farmer exp
                    this.farmerViewController.giveFarmerExp(
                            pendingHarvest.getCrop().getExpGain()
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
        buttonPanel.add(harvestBtn);

        JButton levelupBtn = new JButton("Level Up");
        levelupBtn.setSize(100, 50);
        levelupBtn.addActionListener(e -> {
            System.out.println("Level Up button clicked");
            this.farmerViewController.levelUp();
        });
        buttonPanel.add(levelupBtn);
    }

    /**
     * Advances the day of the game.
     * @return the report which contains the message that will be displayed to the user
     */
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

    /**
     * Wrapper method for planting a crop.
     * @param currentTile the tile that the farmer is currently on
     */
    private void plantWrapper(Tile currentTile) {
        if (currentTile instanceof PlowedTile) {
            if (this.farmerViewController.getCropFromPockets() != null) {

                // check if the crop is a tree
                Crop crop = this.farmerViewController.getCropFromPockets();
                if(crop.getType().contains("Fruit Tree")){
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

    /**
     * Gets all the tiles in the farmland.
     *
     * @return the 2d tile array which holds all tiles in the farmland.
     */
    public Tile[][] getFarmland(){
        return this.farmlandController.farmlandModel.getTiles();
    }

    /**
     * Wrapper for using tools.
     *
     * @param tile the tile where the farmer is currently on
     */
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
        }
            JOptionPane.showMessageDialog(
                    this.mainFrame,
                    attempt.getStatus(),
                    "MAILMAN",
                    JOptionPane.INFORMATION_MESSAGE
            );
    }

    /**
     * Initializes farmer movement.
     */
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

