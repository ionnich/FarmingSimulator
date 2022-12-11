package model.grades;

/**
 * The Unregistered class is a subclass of FarmerGrade. It represents the Unregistered grade of a farmer.
 */
public class Unregistered extends FarmerGrade{

    /**
     * Instantiates a new Unregistered grade.
     */
    public Unregistered() {
        this.nextGrade = new Registered();
    }
}
