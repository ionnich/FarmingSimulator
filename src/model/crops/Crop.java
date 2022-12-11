package model.crops;

public abstract class Crop {
    protected String name;
    protected double cost;
    protected String type;

    protected int waterLevel;
    protected int waterMinimum;
    protected int waterLimit;

    protected int fertilizerMinimum;

    protected int fertilizerLevel;
    protected int fertilizerLimit;
    protected int fertilizerBonus;

    protected double basePrice;
    protected double expGain;

    protected int harvestTimer;
    protected int harvestYieldMin;
    protected int harvestYieldMax;

    protected boolean isHarvestable;
    protected boolean isWithered;

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getType(){
        return type;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public int getWaterMinimum() {
        return waterMinimum;
    }

    public int getWaterLimit() {
        return waterLimit;
    }

    public int getFertilizerLevel() {
        return fertilizerLevel;
    }

    public int getFertilizerBonus() {
        return fertilizerBonus;
    }

    public double getExpGain() {
        return expGain;
    }

    public int getHarvestTimer() {
        return harvestTimer;
    }

    public int getHarvestYield() {
        int yield = (int) (Math.random() * (harvestYieldMax - harvestYieldMin + 1) + harvestYieldMin);
        return yield;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setWithered(){
        isWithered = true;
    }

    public boolean checkHarvest(){
        if (harvestTimer == 0){
            isHarvestable = true;
        }
        return isHarvestable;
    }

    public void grow(){
        harvestTimer--;
        if(harvestTimer == 0){
            if(waterLevel < waterMinimum)
                isWithered = true;
            if(fertilizerLevel < fertilizerLimit)
                isWithered = true;

            isHarvestable = true;
        }

        if(harvestTimer < 0)
            isWithered = true;
    }

    public boolean getWithered(){
        return isWithered;
    }

    public void water(int waterBonus) {
        if(waterLevel < waterLimit + waterBonus)
            waterLevel++;
    }

    public void fertilize(int fertilizerBonus) {
        if(fertilizerLevel < fertilizerLimit + fertilizerBonus)
            fertilizerLevel++;
    }

    public boolean isHarvestable() {
        return isHarvestable;
    }

    public boolean isWatered() {
        if(waterLevel >= waterMinimum)
            return true;
        else
            return false;
    }

    public boolean isFertilized(){
        if(fertilizerLevel >= fertilizerMinimum)
            return true;
        else
            return false;
    }
}
