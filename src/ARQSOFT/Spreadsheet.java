package ARQSOFT;

import java.util.ArrayList;
import java.util.Collections;

import static ARQSOFT.Checker.*;

public class Spreadsheet {
    // Attributes
    private static Spreadsheet instance;
    private ArrayList<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell> >();
    //                  Rows   Columns
    private int rowsNumber=0;
    private int columnsNumber=0;

    // Constructor and getInstance()
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
    public int[] coordinateTranslator(String coordinates){ // AS20 -> [column,row]
        int[] toReturn = new int[2];
        int i = 0;
        for (char ch: coordinates.toCharArray()) {
            if (Character.isDigit(ch)){
                break;
            }else{
                i++;
            }
        }
        char[] ch  = coordinates.substring(0,i).toCharArray();
        int j= ch.length-1;
        for(char c : ch){
            int temp = Character.getNumericValue(c)-9;
            toReturn[0]+=temp*Math.pow(26, j);
            j--;
        }
        toReturn[1]=Integer.parseInt(coordinates.substring(i));
        return toReturn;
    }
    public String coordinateTranslator(int[] coordinates){ // [column,row] -> AS20
        String toReturn="";
        ArrayList<Integer> b =new ArrayList<Integer>();
        int a=coordinates[0];
        while (a>26){
            if(a%26!=0){
                b.add(a%26);
                a=a/26;
            }else {
                b.add(26);
                a=a/26-1;
            }
        }
        char c=Character.forDigit(a+9,36);
        toReturn+=Character.toUpperCase(c);
        Collections.reverse(b);
        for (int i:b){
            c=Character.forDigit(i+9,36);
            toReturn+=Character.toUpperCase(c);
        }
        toReturn+=String.valueOf(coordinates[1]);
        return toReturn;
    }
    public boolean insideRange(String coordinates){
        boolean toReturn = true;
        int[] coord = coordinateTranslator(coordinates);
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
                tmp.setCoordinates(coordinateTranslator(coord));
                column.add(tmp);
            }
            row++;
        }
    }

    public void modifyCellContent(String coordinates,String content){ // modifies the content of the cell of the coordiantes
        int[] coord = coordinateTranslator(coordinates); // [column,row]
        addRowsColums(coord);  // creates rows and columns if the spreadsheet is not big enough
        Cell tmp = findCellAndReturn(coordinates); // returns the cell in that coordinates
        if (content.equals("")){
            cells.get(coord[1]-1).set(coord[0]-1,new Cell(coordinates));
        }else if (IsDouble(content)){  // if content is a number, creates a numerical cell
            cells.get(coord[1]-1).set(coord[0]-1,new NumericalCell(coordinates, content));
        }else if(content.charAt(0) == '='){
            FormulaCell tmpFormulaCell = new FormulaCell(coordinates, content);
            cells.get(coord[1]-1).set(coord[0]-1,tmpFormulaCell);
        }else{
            cells.get(coord[1]-1).set(coord[0]-1,new TextCell(coordinates, content));

        }

    }

    public Cell findCellAndReturn(String coordinates){ // gets the cell class by coordinates
        int[] coord = coordinateTranslator(coordinates); // [column,row]
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
    public ArrayList<String> showSpreadsheetValue(Spreadsheet spreadsheet){ // Returns a list of strings with the coordinate and the value of each cell
        ArrayList<String> toReturn = new ArrayList<>();
        for (ArrayList<Cell> rows:cells){
            for (Cell cell:rows){
                toReturn.add(cell.getCoordinates().toUpperCase() +": "+cell.textValue());
            }
        }
        return toReturn;
    }


    // Getters & Setters
    public ArrayList<ArrayList<Cell>> getCells() {
        return cells;
    }

    public void setCells(ArrayList<ArrayList<Cell>> cells) {
        this.cells = cells;
    }

    public double getRowsNumber() {
        return rowsNumber;
    }

    public void setRowsNumber(int rowsNumber) {
        this.rowsNumber = rowsNumber;
    }

    public double getColumnsNumber() {
        return columnsNumber;
    }

    public void setColumnsNumber(int columnsNumber) {
        this.columnsNumber = columnsNumber;
    }
}
