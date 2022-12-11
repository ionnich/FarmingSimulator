package model.grades;

public class Distinguished extends FarmerGrade {

    public Distinguished() {
        this.marketBonus = 2f;
        this.seedDiscount = 2f;
        this.wateringCanLevel = 1;

        this.levelRequirement = 10;
        this.upgradeCost = 300;
        this.nextGrade = new Legendary();
    }

}
