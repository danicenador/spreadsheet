package FormulaComposite;

public class OperatorMinus extends Operator {
    // Attributes

    // Constructor
    public OperatorMinus(FormulaTree left, FormulaTree right){
        this.left=left;
        this.right=right;
    }

    // Methods
    @Override
    public double getValue() {
        return left.getValue()-right.getValue();
    }
}
