package ARQSOFT;

public class NumericalCell extends Cell{
    // Attributes
    private double value;

    // Constructor
    public NumericalCell(String coordinates, String content){
        this.setCoordinates(coordinates);
        this.setContent(content);
        value=Double.parseDouble(content);
    }

    // Methods
    @Override
    public String textValue(Spreadsheet spreadsheet){
        return value+"";
    }
    @Override
    public double numericalValue(Spreadsheet spreadsheet){
        return value;
    }

    // Setters & Getters

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}