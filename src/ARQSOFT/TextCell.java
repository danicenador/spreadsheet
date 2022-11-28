package ARQSOFT;

import java.io.IOException;

public class TextCell extends Cell{
    // Attributes
    private String value;

    // Constructor
    public TextCell(String coordinates, String content){
        this.setCoordinates(coordinates);
        this.setContent(content);
        value=content;
    }

    // Methods
    @Override
    public String textValue(Spreadsheet spreadsheet){
        return value;
    }
    @Override
    public double numericalValue(Spreadsheet spreadsheet){
        return 999999999.1212126547384748437383738373837383734217238947123841239478129;
    }
}
