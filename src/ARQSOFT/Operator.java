package ARQSOFT;

public abstract class Operator implements FormulaTree {
    // Attributes
    private FormulaTree left;
    private FormulaTree right;

    // Methods
    @Override
    public abstract double getValue();

    // Getters and Setters

    public FormulaTree getLeft() {
        return left;
    }

    public void setLeft(FormulaTree left) {
        this.left = left;
    }

    public FormulaTree getRight() {
        return right;
    }

    public void setRight(FormulaTree right) {
        this.right = right;
    }
}
