package ARQSOFT;

public abstract class Function implements FormulaTree {
    // Attributes
    private FormulaTree[] inputs;

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

    public void setInputs(FormulaTree[] inputs) {
        this.inputs = inputs;
    }
}
