package ARQSOFT;

import java.util.LinkedList;

import static ARQSOFT.Checker.*;


public class FormulaCell extends Cell{
    // Attributes
    private FormulaTree tree;

    // Constructor
    public FormulaCell(String coordinates, String content){
        this.setCoordinates(coordinates);
        this.setContent(content);
        FormulaTreeMaker FTM = new FormulaTreeMaker();
        tree = FTM.createFormulaTree(content);
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

