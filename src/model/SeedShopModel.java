package model;

import controller.factories.CropFactory;
import model.crops.Crop;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The SeedShopModel class represents the seed shop of the game. 
 */
public class SeedShopModel {
    private HashMap<String, Crop> catalogue = new HashMap<>();

    /**
     * Instantiates a new SeedShopModel.
     */
    public SeedShopModel() {
        this.initializeCatalogue();
    }

    /**
     * Gets the crop given  a seed.
     *
     * @param seedName the seed name
     * @return the crop
     */
    public Crop getCrop(String seedName) {

        // guard clause
        if (!this.catalogue.containsKey(seedName)) {
            return null;
        }

        return catalogue.get(seedName);
    }

    /**
     * Gets the array list that contains all the seeds.
     *
     * @return the catalogue
     */
    public ArrayList<String> getCatalogue() {
        return new ArrayList<>(this.catalogue.keySet());
    }

    /**
     * Gets the cheapest seed.
     *
     * @return the cheapest seed
     */
    public double getCheapestSeed() {
        double cheapestSeed = Double.MAX_VALUE;
        for (Crop crop : this.catalogue.values()) {
            if (crop.getCost() < cheapestSeed) {
                cheapestSeed = crop.getCost();
            }
        }
        return cheapestSeed;
    }

    /**
     * Initializes the catalogue of the seed shop.
     */
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
