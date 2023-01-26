package MainPackage;

import java.util.ArrayList;

public class Cell{
    // Attributes
    protected String coordinates;
    protected String content="";
    protected ArrayList<Observer> subscribers=new ArrayList<>();


    // Constructors
    public Cell(){};
    public Cell(String coordinates, ArrayList<Observer> subscribers){
        this.coordinates=coordinates;
        this.subscribers=subscribers;
    }

    // Methods
    public void subscribe(Observer subscriber){
        subscribers.add(subscriber);
    }
    public void unsubscribe(Observer subscriber){
        subscribers.remove(subscriber);
    }
    public void notifySubscribers(){
        for(Observer subscriber:subscribers){
            subscriber.update();
        }
    }
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

    public ArrayList<Observer> getSubscribers() {
        return subscribers;
    }

}
