package FormulaComposite;

public abstract class Function implements FormulaTree {
    // Attributes
    protected FormulaTree[] inputs;

    public Function(FormulaTree[] inputs){
        this.inputs=inputs;
    }
    // Methods
    @Override
    public abstract double getValue();

    // Getters and Setters

    public FormulaTree[] getInputs() {
        return inputs;
    }

}
