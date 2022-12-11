package model.grades;

public class Unregistered extends FarmerGrade{

    public Unregistered() {
        this.nextGrade = new Registered();
    }
}
