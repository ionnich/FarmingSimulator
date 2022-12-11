package controller;

import controller.factories.CropFactory;
import model.SeedShopModel;
import model.crops.Crop;
import view.SeedShopView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeedShopController {

    private SeedShopView seedShopView;
    private SeedShopModel seedShopModel;
    private FarmerViewController fvc;

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

    public double getCheapestSeed() {
        return seedShopModel.getCheapestSeed();
    }

    public class backListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            seedShopView.hide();
        }
    }

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


    // show the seedshop in another window
    public void showSeedShop() {
        this.seedShopView.show();
    }

}
