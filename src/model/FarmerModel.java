package model;

import model.grades.FarmerGrade;
import model.grades.Unregistered;

/**
 * The FarmerModel class represents the farmer of the game. It contains the farmer's stats and inventory.
 */
public class FarmerModel {

    private final String name;
    private double money;
    private double exp;

    private int level;

    private FarmerGrade grade;

    /**
     * Instantiates a new Farmer model.
     */
    public FarmerModel() {
        // init default values

        this.name = "John Smith";
        this.money = 100;
        this.exp = 0;
        this.level = 0;
        this.grade = new Unregistered();
    }

    /**
     * Gets the level of the farmer.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the grade of the farmer.
     *
     * @return the grade
     */
    public String getGrade() {
        return grade.toString();
    }

    /**
     * Sets the grade of the farmer.
     *
     * @param grade the grade
     */
    public void setGrade(FarmerGrade grade) {
        this.grade = grade;
    }

    /**
     * Adds the gained exp of the farmer.
     *
     * @param exp the exp
     */
    public void gainExp(double exp) {
        this.level = (int) (this.exp / 100);
        this.exp += exp;
    }

    /**
     * Gets the watering can level.
     *
     * @return the watering can level
     */
    public int getWateringCanLevel() {
        return grade.getWateringCanLevel();
    }

    /**
     * Gets the fertilizer level.
     *
     * @return the fertilizer level
     */
    public int getFertilizerLevel(){
        return grade.getFertilizerLevel();
    }

    /**
     * Gets the market bonus.
     *
     * @return the market bonus
     */
    public double marketBonus(){
        return grade.marketBonus();
    }

    /**
     * Gets the seed discount.
     *
     * @return the double
     */
    public double seedDiscount(){
        return grade.seedDiscount();
    }

    /**
     * Gets the next grade of the current farmer grade.
     *
     * @return the farmer grade
     */
    public FarmerGrade getNextGrade(){
        return grade.nextGrade();
    }

    /**
     * Gets the amount of money that a farmer has.
     *
     * @return the balance of the farmer
     */
    public double getBalance() {
        return money;
    }

    /**
     * Reduces the money of the  farmer.
     *
     * @param cost the amount of money to spent
     */
    public void spendMoney(double cost) {
        this.money -= cost;
    }

    /**
     * Adds money.
     *
     * @param money the amounr to be added
     */
    public void addMoney(double money) {
        this.money += money;
    }

    /**
     * Gets the name of the farmer.
     *
     * @return the farmer name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the exp.
     *
     * @return the exp of the farmer
     */
    public double getExp() {
        return exp;
    }

}
