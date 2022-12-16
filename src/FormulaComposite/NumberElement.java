package FormulaComposite;

public class NumberElement implements FormulaTree {
    // Attributes
    private double number;

    // Constructor
    public NumberElement(double number){
        this.number=number;
    }
    // Method
    @Override
    public double getValue(){
        return number;
    };

    // Setter
    public void setNumber(double number) {
        this.number = number;
    }
}
