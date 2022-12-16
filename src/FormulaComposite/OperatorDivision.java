package FormulaComposite;

public class OperatorDivision extends Operator {
    // Attributes

    // Constructor
    public OperatorDivision(FormulaTree left, FormulaTree right){
        this.left=left;
        this.right=right;
    }

    // Methods
    @Override
    public double getValue() {
        return left.getValue()/right.getValue();
    }
}
