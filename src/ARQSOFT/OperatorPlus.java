package ARQSOFT;

public class OperatorPlus extends Operator {
    // Attributes

    // Constructor
    OperatorPlus(FormulaTree left, FormulaTree right){
        this.setLeft(left);
        this.setRight(right);
    }

    // Methods
    @Override
    public double getValue() {
        return getLeft().getValue()+getRight().getValue();
    }
}
