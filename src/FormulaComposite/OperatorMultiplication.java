package FormulaComposite;

public class OperatorMultiplication extends Operator {
    // Attributes

    // Constructor
    public OperatorMultiplication(FormulaTree left, FormulaTree right){
        this.left=left;
        this.right=right;
    }

    // Methods
    @Override
    public double getValue() {
        return left.getValue()*right.getValue();
    }
}

