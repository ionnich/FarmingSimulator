package model.grades;

/**
 * The Distinguished class is a subclass of FarmerGrade. It represents the Distinguished grade of a farmer.
 */
public class Distinguished extends FarmerGrade {

    /**
     * Instantiates a new Distinguished grade.
     */
    public Distinguished() {
        this.marketBonus = 2f;
        this.seedDiscount = 2f;
        this.wateringCanLevel = 1;

        this.levelRequirement = 10;
        this.upgradeCost = 300;
        this.nextGrade = new Legendary();
    }

}
