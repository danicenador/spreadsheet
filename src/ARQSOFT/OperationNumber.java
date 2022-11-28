package ARQSOFT;

public class OperationNumber implements OperationInterface{
    // Attributes
    private double number;

    // Constructor
    public OperationNumber(double number){
        this.number=number;
    }
    // Method
    @Override
    public double getValue(Spreadsheet spreadsheet){
        return number;
    };

    // Setter
    public void setNumber(double number) {
        this.number = number;
    }
}
