package model.tools;

/**
 * The ToolModel is an abstract class that represents a tool.
 */
public abstract class ToolModel {
    protected double toolCost;
    protected double expGain;

    /**
     * Instantiates a new ToolModel.
     *
     * @param toolCost the tool cost
     * @param expGain  the exp gain
     */
    public ToolModel(double toolCost, double expGain){
        this.toolCost = toolCost;
        this.expGain = expGain;
    }

    /**
     * Gets the cost for using the tool.
     *
     * @return the cost
     */
    public double getCost() {
        return toolCost;
    }

    /**
     * Gets the exp gained from using the tool.
     *
     * @return the exp
     */
    public double getExp() {
        return expGain;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }

}
