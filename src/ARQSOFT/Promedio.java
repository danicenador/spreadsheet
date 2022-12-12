package ARQSOFT;

public class Promedio extends Function{
    // Constructor
    public Promedio(FormulaTree[] inputs) {
        super(inputs);
    }

    // Methods
    @Override
    public double getValue() {
        double toReturn = 0;
        for (FormulaTree value:getInputs()){
            toReturn+=value.getValue();
        }
        return toReturn/getInputs().length;
    }
}
