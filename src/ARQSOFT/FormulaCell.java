package ARQSOFT;

import FormulaComposite.FormulaTree;
import parser.AbstractNode;
import parser.ExpressionBuilder;

import java.util.List;


public class FormulaCell extends Cell{
    // Attributes
    private FormulaTree tree;
    private List<String> references;

    // Constructor
    public FormulaCell(String coordinates, String content){
        this.setCoordinates(coordinates);
        this.setContent(content);
        Factory factory = new Factory();
        ExpressionBuilder builder = new ExpressionBuilder(factory);
        builder.buildExpression(content.substring(1));
        AbstractNode expression = builder.getExpression();
        List<String> references = builder.getCellReferences();
        this.references=references;
        this.tree=(FormulaTree) expression;
    }

    // Methods


    public double numericalValue( ) {
        return tree.getValue();
    }
    public String textValue( ){
        return tree.getValue()+"";
    }


    // Getters & Setters

    public FormulaTree getTree() {
        return tree;
    }

    public void setTree(FormulaTree tree) {
        this.tree = tree;
    }
}

