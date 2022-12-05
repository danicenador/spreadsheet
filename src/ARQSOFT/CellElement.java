package ARQSOFT;

public class CellElement implements FormulaTree {
    // Attributes
    private String coordinates;

    // Constructor
    public CellElement(String coordinates){
        this.coordinates=coordinates;
    }
    // Methods
    @Override
    public double getValue(){
        Spreadsheet spreadsheet = Spreadsheet.GetInstance();
        double toReturn = spreadsheet.findCellAndReturn(coordinates).numericalValue();
        return toReturn;
    }
}
