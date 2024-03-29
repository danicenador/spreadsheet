package MainPackage;

import java.util.ArrayList;

import static MainPackage.Utilities.IsDouble;
import static MainPackage.Utilities.CoordinateTranslator;

public class Spreadsheet {
    // Attributes
    private static Spreadsheet instance;
    private ArrayList<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell> >();
    //                  Rows   Columns
    private int rowsNumber=0;
    private int columnsNumber=0;

    // Constructor and GetInstance()
    private Spreadsheet(){}
    public static Spreadsheet GetInstance(){
        if(instance==null){
            instance = new Spreadsheet();
        }
        return instance;
    }

    // Methods
    public void reset(){
        instance = new Spreadsheet();
    }

    public boolean insideRange(String coordinates){
        boolean toReturn = true;
        int[] coord = CoordinateTranslator(coordinates);
        if (coord[1]> rowsNumber) {
            toReturn = false;
        }
        if (coord[0]> columnsNumber){
            toReturn = false;
        }
        return toReturn;
    }
    public void addRowsColums(int[] coordinates){  // checks if the spreadsheet is big enough for a cell, and adds rows and colums if isn't
        if (coordinates[1]> rowsNumber) {
            while (cells.size()<coordinates[1]){
                cells.add(new ArrayList<Cell>());
            }
            rowsNumber=coordinates[1];
        }
        if (coordinates[0]> columnsNumber){
            columnsNumber=coordinates[0];
        }
        int row = 0;
        for (ArrayList<Cell> column:cells){
            while (column.size()<columnsNumber){
                Cell tmp=new Cell();
                int[] coord=new int[2];
                coord[0]=column.size()+1;
                coord[1]=row+1;
                tmp.setCoordinates(Utilities.CoordinateTranslator(coord));
                column.add(tmp);
            }
            row++;
        }
    }

    public void modifyCellContent(String coordinates,String content){ // modifies the content of the cell of the coordiantes
        int[] coord = CoordinateTranslator(coordinates); // [column,row]
        addRowsColums(coord);  // creates rows and columns if the spreadsheet is not big enough
        Cell tmp = findCellAndReturn(coordinates); // returns the cell in that coordinates
        if (tmp instanceof FormulaCell){
            FormulaCell tmpFC = (FormulaCell) tmp;
            tmpFC.removeSubscriptions();
        }
        if (content.equals("")){
            cells.get(coord[1]-1).set(coord[0]-1,new Cell(coordinates,tmp.getSubscribers()));
            cells.get(coord[1]-1).get(coord[0]-1).notifySubscribers();
        }else if (IsDouble(content)){  // if content is a number, creates a numerical cell
            cells.get(coord[1]-1).set(coord[0]-1,new NumericalCell(coordinates, content,tmp.getSubscribers()));
            cells.get(coord[1]-1).get(coord[0]-1).notifySubscribers();
        }else if(content.charAt(0) == '='){
            cells.get(coord[1]-1).set(coord[0]-1,new FormulaCell(coordinates, content,tmp.getSubscribers()));
            cells.get(coord[1]-1).get(coord[0]-1).notifySubscribers();
        }else{
            cells.get(coord[1]-1).set(coord[0]-1,new TextCell(coordinates, content,tmp.getSubscribers()));
            cells.get(coord[1]-1).get(coord[0]-1).notifySubscribers();
        }
    }

    public Cell findCellAndReturn(String coordinates){ // gets the cell class by coordinates
        int[] coord = CoordinateTranslator(coordinates); // [column,row]
        addRowsColums(coord);
        return cells.get(coord[1]-1).get(coord[0]-1);
        //               row             column
    }
    public String showCellContent(String coordinates){ // shows the content of the cell with those coordiantes
        Cell tmp = findCellAndReturn(coordinates);
        return tmp.getContent();
    }

    public ArrayList<String> showSpreadsheetContent(){ // Returns a list of strings with the coordinate and the content of each cell
        ArrayList<String> toReturn = new ArrayList<>();
        for (ArrayList<Cell> rows:cells){
            for (Cell cell:rows){
                toReturn.add(cell.getCoordinates() +": "+cell.getContent());
            }
        }
        return toReturn;
    }
    public ArrayList<String> showSpreadsheetValue(){ // Returns a list of strings with the coordinate and the value of each cell
        ArrayList<String> toReturn = new ArrayList<>();
        for (ArrayList<Cell> rows:cells){
            for (Cell cell:rows){
                toReturn.add(cell.getCoordinates().toUpperCase() +": "+cell.textValue());
            }
        }
        return toReturn;
    }

    public Iterator createIterator(){
        return new SpreadsheetIterator(cells, rowsNumber,columnsNumber);
    }


    // Getters & Setters
    public ArrayList<ArrayList<Cell>> getCells() {
        return cells;
    }


    public void setRowsNumber(int rowsNumber) {
        this.rowsNumber = rowsNumber;
    }

    public void setColumnsNumber(int columnsNumber) {
        this.columnsNumber = columnsNumber;
    }
}
