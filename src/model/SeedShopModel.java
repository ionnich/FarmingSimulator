package model;

import controller.factories.CropFactory;
import model.crops.Crop;

import java.util.ArrayList;
import java.util.HashMap;

public class SeedShopModel {


    private HashMap<String, Crop> catalogue = new HashMap<>();

    public SeedShopModel() {
        this.initializeCatalogue();
    }

    public Crop getCrop(String seedName) {

        // guard clause
        if (!this.catalogue.containsKey(seedName)) {
            return null;
        }

        return catalogue.get(seedName);
    }

    public ArrayList<String> getCatalogue() {
        return new ArrayList<>(this.catalogue.keySet());
    }

    public double getCheapestSeed() {
        double cheapestSeed = Double.MAX_VALUE;
        for (Crop crop : this.catalogue.values()) {
            if (crop.getCost() < cheapestSeed) {
                cheapestSeed = crop.getCost();
            }
        }
        return cheapestSeed;
    }

    private void initializeCatalogue() {
        CropFactory cf = new CropFactory();
        // initialize catalogue
        catalogue.put("turnip", cf.create("turnip"));
        catalogue.put("tulips", cf.create("tulips"));
        catalogue.put("carrot", cf.create("carrot"));
        catalogue.put("apple", cf.create("apple"));
        catalogue.put("potato", cf.create("potato"));
        catalogue.put("rose", cf.create("rose"));
        catalogue.put("sunflower", cf.create("sunflower"));
        catalogue.put("mango", cf.create("mango"));
    }
}
