package model;

import model.grades.FarmerGrade;
import model.grades.Unregistered;

public class FarmerModel {

    private final String name;
    private double money;
    private double exp;

    private int level;

    private FarmerGrade grade;
    public FarmerModel() {
        // init default values

        this.name = "John Smith";
        this.money = 100;
        this.exp = 0;
        this.level = 0;
        this.grade = new Unregistered();
    }

    public int getLevel() {
        return level;
    }

    public String getGrade() {
        return grade.toString();
    }

    public void setGrade(FarmerGrade grade) {
        this.grade = grade;
    }

    public void gainExp(double exp) {
        this.level = (int) (this.exp / 100);
        this.exp += exp;
    }

    public int getWateringCanLevel() {
        return grade.getWateringCanLevel();
    }

    public int getFertilizerLevel(){
        return grade.getFertilizerLevel();
    }

    public double marketBonus(){
        return grade.marketBonus();
    }

    public double seedDiscount(){
        return grade.seedDiscount();
    }

    public FarmerGrade getNextGrade(){
        return grade.nextGrade();
    }
    public double getBalance() {
        return money;
    }

    public void spendMoney(double cost) {
        this.money -= cost;
    }

    public void addMoney(double money) {
        this.money += money;
    }

    public String getName() {
        return name;
    }
    public double getExp() {
        return exp;
    }

    public void levelUp() {
        this.grade = grade.nextGrade();
    }
}
