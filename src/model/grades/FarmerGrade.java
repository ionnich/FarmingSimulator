package model.grades;

/**
 * The FarmerGrade class is an abstract class that represents the grade or level of a farmer.
 */
public abstract class FarmerGrade {
    protected FarmerGrade nextGrade;
    protected int wateringCanLevel;
    protected int fertilizerLevel;
    protected double marketBonus;
    protected double seedDiscount;
    protected int levelRequirement;
    protected double upgradeCost;

    /**
     * Instantiates a new FarmerGrade.
     */
    protected FarmerGrade() {
        this.nextGrade = null;
        this.wateringCanLevel = 0;
        this.fertilizerLevel = 0;
        this.marketBonus = 0;
        this.seedDiscount = 0;
        this.levelRequirement = 0;
        this.upgradeCost = 0;
    }

    /**
     * Gets the level requirement.
     *
     * @return the level requirement
     */
    public int getLevelRequirement() {
        return levelRequirement;
    }

    /**
     * Gets the upgrade cost.
     *
     * @return the upgrade cost
     */
    public double getUpgradeCost() {
        return upgradeCost;
    }

    /**
     * Gets the watering can level.
     *
     * @return the watering can level
     */
    public int getWateringCanLevel(){
        return wateringCanLevel;
    }

    /**
     * Get the fertilizer level.
     *
     * @return the fertilizer level
     */
    public int getFertilizerLevel(){
        return fertilizerLevel;
    }

    /**
     * Gets the market bonus.
     *
     * @return the market bonus
     */
    public double marketBonus(){
        return marketBonus;
    }

    /**
     * Gets the seed discount.
     *
     * @return the seed discount
     */
    public double seedDiscount(){
        return seedDiscount;
    }

    /**
     * Gets the next grade of the current farmer grade.
     *
     * @return the next farmer grade
     */
    public FarmerGrade nextGrade(){
        return nextGrade;
    }

    /**
     * Gets the name of the farmer grade.
     *
     * @return the name of the farmer grade
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
