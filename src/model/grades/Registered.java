package model.grades;

public class Registered extends FarmerGrade {

    public Registered() {
        this.marketBonus = 1f;
        this.seedDiscount = 1f;
        this.levelRequirement = 5;
        this.upgradeCost = 200;
        this.nextGrade = new Distinguished();
    }
}
