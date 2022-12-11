package model.grades;

public abstract class FarmerGrade {

    protected FarmerGrade nextGrade;
    protected int wateringCanLevel;
    protected int fertilizerLevel;
    protected double marketBonus;
    protected double seedDiscount;

    protected int levelRequirement;

    protected double upgradeCost;

    protected FarmerGrade() {
        this.nextGrade = null;
        this.wateringCanLevel = 0;
        this.fertilizerLevel = 0;
        this.marketBonus = 0;
        this.seedDiscount = 0;
        this.levelRequirement = 0;
        this.upgradeCost = 0;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }
    public double getUpgradeCost() {
        return upgradeCost;
    }
    public int getWateringCanLevel(){
        return wateringCanLevel;
    }

    public int getFertilizerLevel(){
        return fertilizerLevel;
    }
    public double marketBonus(){
        return marketBonus;
    }
    public double seedDiscount(){
        return seedDiscount;
    }
    public FarmerGrade nextGrade(){
        return nextGrade;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
