package model.crops;

/**
 * The Crop is an abstract class that represents a crop.
 */
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

    /**
     * Gets the cost of the crop.
     *
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Gets the crop name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the crop.
     *
     * @return the crop type
     */
    public String getType(){
        return type;
    }

    /**
     * Gets the water level of the crop.
     *
     * @return the crop water level
     */
    public int getWaterLevel() {
        return waterLevel;
    }

    /**
     * Gets water minimum.
     *
     * @return the water minimum
     */
    public int getWaterMinimum() {
        return waterMinimum;
    }

    /**
     * Gets water limit.
     *
     * @return the water limit
     */
    public int getWaterLimit() {
        return waterLimit;
    }

    /**
     * Gets fertilizer level.
     *
     * @return the fertilizer level
     */
    public int getFertilizerLevel() {
        return fertilizerLevel;
    }

    /**
     * Gets fertilizer bonus.
     *
     * @return the fertilizer bonus
     */
    public int getFertilizerBonus() {
        return fertilizerBonus;
    }

    /**
     * Gets exp gain.
     *
     * @return the exp gain
     */
    public double getExpGain() {
        return expGain;
    }

    /**
     * Gets harvest timer.
     *
     * @return the harvest timer
     */
    public int getHarvestTimer() {
        return harvestTimer;
    }

    /**
     * Gets harvest yield.
     *
     * @return the harvest yield
     */
    public int getHarvestYield() {
        int yield = (int) (Math.random() * (harvestYieldMax - harvestYieldMin + 1) + harvestYieldMin);
        return yield;
    }

    /**
     * Gets base price.
     *
     * @return the base price
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Set withered.
     */
    public void setWithered(){
        isWithered = true;
    }

    /**
     * Check harvest boolean.
     *
     * @return the boolean
     */
    public boolean checkHarvest(){
        if (harvestTimer == 0){
            isHarvestable = true;
        }
        return isHarvestable;
    }

    /**
     * Makes the crop grow when the day advances.
     */
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

    /**
     * Tracks whether the crop is withered.
     *
     * @return the boolean value of whether the crop is withered
     */
    public boolean getWithered(){
        return isWithered;
    }

    /**
     * Waters the crop.
     *
     * @param waterBonus the water bonus
     */
    public void water(int waterBonus) {
        if(waterLevel < waterLimit + waterBonus)
            waterLevel++;
    }

    /**
     * Fertilizes the crop.
     *
     * @param fertilizerBonus the fertilizer bonus
     */
    public void fertilize(int fertilizerBonus) {
        if(fertilizerLevel < fertilizerLimit + fertilizerBonus)
            fertilizerLevel++;
    }

    /**
     * Tracks whether the crop is harvestable.
     *
     * @return the boolean value of whether the crop is harvestable
     */
    public boolean isHarvestable() {
        return isHarvestable;
    }

    /**
     * Tracks whether the crop is watered.
     *
     * @return the boolean value of whether the crop is watered
     */
    public boolean isWatered() {
        if(waterLevel >= waterMinimum)
            return true;
        else
            return false;
    }

    /**
     * Tracks whether the crop is fertilized.
     *
     * @return the boolean value of whether the crop is fertilized
     */
    public boolean isFertilized(){
        if(fertilizerLevel >= fertilizerMinimum)
            return true;
        else
            return false;
    }
}
