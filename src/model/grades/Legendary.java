package model.grades;

public class Legendary extends FarmerGrade {
    public Legendary() {
        this.marketBonus = 3f;
        this.seedDiscount = 3f;
        this.wateringCanLevel = 2;
        this.fertilizerLevel = 1;

        this.levelRequirement = 15;
        this.upgradeCost = 400;
        this.nextGrade = null;
    }
}
