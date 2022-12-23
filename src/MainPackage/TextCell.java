package MainPackage;

import java.util.ArrayList;

public class TextCell extends Cell{
    // Attributes
    private String value;

    // Constructor
    public TextCell(String coordinates, String content, ArrayList<Observer> subscribers){
        this.subscribers=subscribers;
        this.setCoordinates(coordinates);
        this.setContent(content);
        value=content;
    }

    // Methods
    @Override
    public String textValue( ){
        return value;
    }
    @Override
    public double numericalValue( ){
        return 999999999.1212126547384748437383738373837383734217238947123841239478129;
    }
}
