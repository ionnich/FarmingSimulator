package model.crops;

public class Carrot extends Crop {
    public Carrot() {
        this.name = "Carrot";
        this.cost = 10.0;
        this.type = "Root Crop";
        this.waterLevel = 0;
        this.waterMinimum = 1;
        this.waterLimit = 2;
        this.fertilizerLevel = 0;
        this.fertilizerMinimum = 0;
        this.fertilizerLimit = 1;
        this.basePrice = 9.0;
        this.expGain = 7.5;
        this.harvestTimer = 3;
        this.harvestYieldMin = 1;
        this.harvestYieldMax = 2;
        this.isHarvestable = false;
        this.isWithered = false;
    }
}
