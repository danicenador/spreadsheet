package ARQSOFT;

import java.util.ArrayList;

public class SpreadsheetIterator implements Iterator {
    // Attributes
    private ArrayList<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell>>();
    private int maxRows;
    private int maxColumns;
    private int row=1;
    private int column=1;

    // Constructor
    public SpreadsheetIterator(ArrayList<ArrayList<Cell>> cells, int maxRows, int maxColumns){
        this.cells=cells;
        this.maxColumns=maxColumns;
        this.maxRows=maxRows;
    }

    // Methods
    @Override
    public Cell getCurrent() {
        return cells.get(row - 1).get(column - 1);
    }

    @Override
    public boolean hasNext() {
        if (row < maxRows && column < maxColumns) {
            return true;
        } else if (row < maxRows && column == maxColumns) {
            return true;
        } else if (row == maxRows && column < maxColumns) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void next() {
        if (row < maxRows && column < maxColumns) {
            column++;
        } else if (row < maxRows && column == maxColumns) {
            row++;
            column = 1;
        } else if (row == maxRows && column < maxColumns) {
            column++;
        }
    }
}

