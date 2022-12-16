package FormulaComposite;

public abstract class Operator implements FormulaTree {
    // Attributes
    protected FormulaTree left;
    protected FormulaTree right;

    // Methods
    @Override
    public abstract double getValue();


}
