package model.crops;

public class Mango extends Crop{
    public Mango() {
        this.name = "Mango";
        this.cost = 100.0;
        this.type = "Fruit Tree";
        this.waterLevel = 0;
        this.waterMinimum = 7;
        this.waterLimit = 7;
        this.fertilizerLevel = 0;
        this.fertilizerMinimum = 4;
        this.fertilizerLimit = 4;
        this.basePrice = 8.0;
        this.expGain = 25.0;
        this.harvestTimer = 10;
        this.harvestYieldMin = 5;
        this.harvestYieldMax = 15;
        this.isHarvestable = false;
        this.isWithered = false;
    }
}
