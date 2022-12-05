package ARQSOFT;

public class OperatorMinus extends Operator {
    // Attributes

    // Constructor
    OperatorMinus(FormulaTree left, FormulaTree right){
        this.setLeft(left);
        this.setRight(right);
    }

    // Methods
    @Override
    public double getValue() {
        return getLeft().getValue()-getRight().getValue();
    }
}
