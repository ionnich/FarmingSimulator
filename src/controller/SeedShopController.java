package controller;

import controller.factories.CropFactory;
import model.SeedShopModel;
import model.crops.Crop;
import view.SeedShopView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The SeedShopController class is responsible for interactions between the SeedShopModel and SeedShopView.
 */
public class SeedShopController {

    private SeedShopView seedShopView;
    private SeedShopModel seedShopModel;
    private FarmerViewController fvc;

    /**
     * Instantiates a new SeedShopController.
     *
     * @param farmerViewController the farmer view controller
     */
    public SeedShopController(FarmerViewController farmerViewController) {

        this.fvc = farmerViewController;
        seedShopView = new SeedShopView(fvc.getFarmerModel().getBalance());
        seedShopModel = new SeedShopModel();
        seedShopView.createCropPanel(seedShopModel.getCatalogue());
        seedShopView.createActionPanel(
                new buySeedAction(),
                new backListener()
        );
        seedShopView.initialize();
    }

    /**
     * Gets the cheapest seed.
     *
     * @return the cheapest seed
     */
    public double getCheapestSeed() {
        return seedShopModel.getCheapestSeed();
    }

    /**
     * Hides the seed shop view when the back button is clicked.
     */
    public class backListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            seedShopView.hide();
        }
    }

    /**
     * Allows the farmer to buy a seed when the buy button is clicked.
     */
    public class buySeedAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String seedName = seedShopView.getSelectedSeed();
            Crop crop = seedShopModel.getCrop(seedName);
            System.out.println(crop.getName());
            if(crop == null){
                System.out.println("Invalid seed name");
                return;
            }

            if(fvc.getFarmerModel().getBalance() < crop.getCost()){
                System.out.println("Not enough money");
                return;
            }

            if(fvc.getCropFromPockets() != null){
                System.out.println("You already have a seed in your pocket");
                return;
            }
            fvc.getFarmerModel().spendMoney(crop.getCost());
            seedShopView.updateBalance(fvc.getFarmerModel().getBalance());
            seedShopView.updateBag(seedName);

            CropFactory cf = new CropFactory();
            fvc.addCropToPockets(cf.create(crop.getName()));
            fvc.updateFarmerView();
        }
    }


    /**
     * Shows the seed shop.
     */
// show the seed shop in another window
    public void showSeedShop() {
        this.seedShopView.show();
    }

}
