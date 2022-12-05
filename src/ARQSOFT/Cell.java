package ARQSOFT;

public class Cell {
    // Attributes
    private String coordinates;
    private String content="";

    // Constructors
    public Cell(){};
    public Cell(String coordinates){
        this.coordinates=coordinates;
    }

    // Methods
    public void textContent(){}
    public void numericalContent(){}
    public String textValue( ){
        return "";
    }
    public double numericalValue( ){
        return 0;
    }


    // Getters & Setters
    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getContent() {
        return content;
    }

    protected void setContent(String content) {
        this.content = content;
    }
}
