package ARQSOFT;

public class OperationCell implements OperationInterface{
    // Attributes
    private String coordinates;

    // Constructor
    public OperationCell(String coordinates){
        this.coordinates=coordinates;
    }
    // Methods
    @Override
    public double getValue(Spreadsheet spreadsheet){

        double toReturn = spreadsheet.findCellAndReturn(coordinates).numericalValue(spreadsheet);
        return toReturn;
    }
}
