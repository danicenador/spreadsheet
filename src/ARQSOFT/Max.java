package ARQSOFT;

public class Max extends Function{
    // Constructor
    public Max(FormulaTree[] inputs) {
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
