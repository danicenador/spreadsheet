package FormulaComposite;

public class OperatorPlus extends Operator {
    // Attributes

    // Constructor
    public OperatorPlus(FormulaTree left, FormulaTree right){
        this.left=left;
        this.right=right;
    }

    // Methods
    @Override
    public double getValue() {
        return left.getValue()+right.getValue();
    }
}
