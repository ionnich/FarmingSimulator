package model.crops;

public class Turnip extends Crop{
    public Turnip() {
        this.name = "Turnip";
        this.cost = 5.0;
        this.type = "Root Crop";
        this.waterLevel = 0;
        this.waterMinimum = 1;
        this.waterLimit = 2;
        this.fertilizerLevel = 0;
        this.fertilizerMinimum = 0;
        this.fertilizerLimit = 1;
        this.basePrice = 6.0;
        this.expGain = 5.0;
        this.harvestTimer = 2;
        this.harvestYieldMin = 1;
        this.harvestYieldMax = 2;
        this.isHarvestable = false;
        this.isWithered = false;
    }
}
