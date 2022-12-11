package model.crops;

public class Potato extends Crop{
    public Potato() {
        this.name = "Potato";
        this.cost = 20.0;
        this.type = "Root Crop";
        this.waterLevel = 0;
        this.waterMinimum = 3;
        this.waterLimit = 4;
        this.fertilizerLevel = 0;
        this.fertilizerMinimum = 1;
        this.fertilizerLimit = 2;
        this.basePrice = 3.0;
        this.expGain = 12.5;
        this.harvestTimer = 5;
        this.harvestYieldMin = 1;
        this.harvestYieldMax = 10;
        this.isHarvestable = false;
        this.isWithered = false;
    }
}
