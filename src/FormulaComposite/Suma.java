package FormulaComposite;

public class Suma extends Function{
    // Constructor
    public Suma(FormulaTree[] inputs) {
        super(inputs);
    }
    // Methods
    @Override
    public double getValue() {
        double toReturn = 0;
        for (FormulaTree value:getInputs()){
            toReturn+=value.getValue();
        }
        return toReturn;
    }
}
