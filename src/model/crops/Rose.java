package model.crops;

/**
 * The Rose class is a subclass of the Crop class. It represents a rose crop.
 */
public class Rose extends Crop{

    /**
     * Instantiates a new Rose.
     */
    public Rose() {
        this.name = "Rose";
        this.cost = 5.0;
        this.type = "Flower";
        this.waterLevel = 0;
        this.waterMinimum = 1;
        this.waterLimit = 2;
        this.fertilizerLevel = 0;
        this.fertilizerMinimum = 0;
        this.fertilizerLimit = 1;
        this.basePrice = 5.0;
        this.expGain = 2.5;
        this.harvestTimer = 1;
        this.harvestYieldMin = 1;
        this.harvestYieldMax = 1;
        this.isHarvestable = false;
        this.isWithered = false;
    }
}
