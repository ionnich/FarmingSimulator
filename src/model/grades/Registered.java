package model.grades;

/**
 * The Registered class is a subclass of FarmerGrade. It represents the Registered grade of a farmer.
 */
public class Registered extends FarmerGrade {

    /**
     * Instantiates a new Registered grade.
     */
    public Registered() {
        this.marketBonus = 1f;
        this.seedDiscount = 1f;
        this.levelRequirement = 5;
        this.upgradeCost = 200;
        this.nextGrade = new Distinguished();
    }
}
