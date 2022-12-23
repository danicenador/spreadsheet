package MainPackage;

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
    private double value;

    // Constructor
    public FormulaCell(String coordinates, String content, ArrayList<Observer> subscribers){
        this.coordinates=coordinates;
        this.content=content;
        this.subscribers=subscribers;
        Factory factory = new Factory();
        ExpressionBuilder builder = new ExpressionBuilder(factory);
        builder.buildExpression(content.substring(1));
        AbstractNode expression = builder.getExpression();
        List<String> references = builder.getCellReferences();
        this.references=references;
        this.tree=(FormulaTree) expression;
        getSubscriptions();
        update();
    }

    // Methods
    public double numericalValue( ) {
        return value;
    }
    public String textValue( ){
        return value+"";
    }

    @Override
    public void update() {
        value=tree.getValue();
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

