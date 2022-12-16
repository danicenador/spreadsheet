package FormulaComposite;

public class Min extends Function{
    // Constructor
    public Min(FormulaTree[] inputs) {
        super(inputs);
    }
    // Methods
    @Override
    public double getValue() {
        double toReturn = 0;
        toReturn = getInputs()[0].getValue();
        for (FormulaTree value:getInputs()){
            if (toReturn>value.getValue()){
                toReturn=value.getValue();
            }
        }
        return toReturn;
    }
}
