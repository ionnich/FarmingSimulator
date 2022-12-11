package model.tools;

public abstract class ToolModel {

    protected double toolCost;
    protected double expGain;

    public ToolModel(double toolCost, double expGain){
        this.toolCost = toolCost;
        this.expGain = expGain;
    }

    public double getCost() {
        return toolCost;
    }

    public double getExp() {
        return expGain;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }

}
