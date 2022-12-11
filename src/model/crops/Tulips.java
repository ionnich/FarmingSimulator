package model.crops;

public class Tulips extends Crop{
    public Tulips() {
        this.name = "Tulips";
        this.cost = 10.0;
        this.type = "Flower";
        this.waterLevel = 0;
        this.waterMinimum = 2;
        this.waterLimit = 3;
        this.fertilizerLevel = 0;
        this.fertilizerMinimum = 0;
        this.fertilizerLimit = 1;
        this.basePrice = 9.0;
        this.expGain = 5.0;
        this.harvestTimer = 2;
        this.harvestYieldMin = 1;
        this.harvestYieldMax = 1;
        this.isHarvestable = false;
        this.isWithered = false;
    }
}
