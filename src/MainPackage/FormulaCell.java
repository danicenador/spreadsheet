package MainPackage;

import FormulaComposite.ErrorTree;
import FormulaComposite.Factory;
import FormulaComposite.FormulaTree;
import parser.AbstractNode;
import parser.ExpressionBuilder;

import java.util.ArrayList;
import java.util.List;


public class FormulaCell extends Cell implements Observer{
    // Attributes
    private FormulaTree tree;
    private List<String> references;
    private String value;

    // Constructor
    public FormulaCell(String coordinates, String content, ArrayList<Observer> subscribers){
        this.coordinates=coordinates;
        this.content=content;
        this.subscribers=subscribers;
        Factory factory = new Factory();
        try {
            ExpressionBuilder builder = new ExpressionBuilder(factory);
            builder.buildExpression(content.substring(1));
            AbstractNode expression = builder.getExpression();
            List<String> references = builder.getCellReferences();
            this.references = references;
            this.tree = (FormulaTree) expression;
            getSubscriptions();
            update();
        }catch (NullPointerException e){
            this.references = new ArrayList<>();
            this.tree = new ErrorTree();
            update();
        }
    }

    // Methods
    public double numericalValue( ) {
        return Double.parseDouble(value);
    }
    public String textValue( ){
        return value;
    }

    @Override
    public void update() {
        boolean errorFound=false;
        Spreadsheet spreadsheet = Spreadsheet.GetInstance();
        for (String c:references){
            if (spreadsheet.findCellAndReturn(c) instanceof FormulaCell){
                if (spreadsheet.findCellAndReturn(c).textValue().equals("Error Formula")){
                    errorFound=true;
                }
            }
        }
        if (circularDependency(this.coordinates)) {
            value = "Error Circular Dependency";
        }else if(this.tree instanceof ErrorTree){
            value = "Error Formula";
        }else if(errorFound){
            value = "Error Formula";
        }else {
            value = tree.getValue() + "";
        }
        if (!circularDependency(this.coordinates)) {
            notifySubscribers();
        }
    }

    public boolean circularDependency(String coordinates){
        Spreadsheet spreadsheet = Spreadsheet.GetInstance();
        boolean toReturn = false;
        for(String c:references){
            if (c.equals(coordinates)){
                toReturn = true;
                break;
            } else if (spreadsheet.findCellAndReturn(c) instanceof FormulaCell) {
                FormulaCell cell = (FormulaCell) spreadsheet.findCellAndReturn(c);
                if (cell.circularDependency(coordinates)){
                    toReturn = true;
                    break;
                }
            }
        }
        return toReturn;
    }
    public void getSubscriptions(){
        Spreadsheet spreadsheet = Spreadsheet.GetInstance();
        for (String coord:references){
            spreadsheet.findCellAndReturn(coord).subscribe(this);
        }
    }
    public void removeSubscriptions(){
        Spreadsheet spreadsheet = Spreadsheet.GetInstance();
        for (String coord:references){
            spreadsheet.findCellAndReturn(coord).unsubscribe(this);
        }
    }
    // Getters & Setters

    public FormulaTree getTree() {
        return tree;
    }

    public void setTree(FormulaTree tree) {
        this.tree = tree;
    }

}

