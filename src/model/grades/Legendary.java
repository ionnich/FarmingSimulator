package model.grades;

/**
 * The Legendary class is a subclass of FarmerGrade. It represents the Legendary grade of a farmer.
 */
public class Legendary extends FarmerGrade {

    /**
     * Instantiates a new Legendary grade.
     */
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
