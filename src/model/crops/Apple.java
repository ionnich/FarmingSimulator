package model.crops;

public class Apple extends Crop{
    public Apple() {
        this.name = "Apple";
        this.cost = 200.0;
        this.type = "Fruit Tree";
        this.waterLevel = 0;
        this.waterMinimum = 7;
        this.waterLimit = 7;
        this.fertilizerLevel = 0;
        this.fertilizerMinimum = 5;
        this.fertilizerLimit = 5;
        this.basePrice = 5.0;
        this.expGain = 25.0;
        this.harvestTimer = 10;
        this.harvestYieldMin = 10;
        this.harvestYieldMax = 15;
        this.isHarvestable = false;
        this.isWithered = false;
    }
}
