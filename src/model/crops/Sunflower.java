package model.crops;

/**
 * The Sunflower class is a subclass of the Crop class. It represents a sunflower crop.
 */
public class Sunflower extends Crop{

    /**
     * Instantiates a new Sunflower.
     */
    public Sunflower() {
        this.name = "Sunflower";
        this.cost = 20.0;
        this.type = "Flower";
        this.waterLevel = 0;
        this.waterMinimum = 2;
        this.waterLimit = 3;
        this.fertilizerLevel = 0;
        this.fertilizerMinimum = 1;
        this.fertilizerLimit = 2;
        this.basePrice = 19.0;
        this.expGain = 7.5;
        this.harvestTimer = 3;
        this.harvestYieldMin = 1;
        this.harvestYieldMax = 1;
        this.isHarvestable = false;
        this.isWithered = false;
    }
}
