package ARQSOFT;

import java.util.LinkedList;

import static ARQSOFT.FormulaParser.*;


public class FormulaCell extends Cell{
    // Attributes
    private OperationOperator tree;

    // Constructor
    public FormulaCell(String coordinates, String content){
        this.setCoordinates(coordinates);
        this.setContent(content);
        createFormulaTree();
    }

    // Methods
    public void createFormulaTree(){
        String formula=getContent().substring(1);
        int i = 0;
        LinkedList<String> values = new LinkedList<>();
        LinkedList<String> operators = new LinkedList<>();
        String value = "";
        for (char ch:formula.toCharArray()){
            if (Character.isDigit(ch)){
                value+=String.valueOf(ch);
            }else if(Character.isLetter(ch)){
                value+=String.valueOf(ch);
            }else{
                values.add(value);
                operators.add(String.valueOf(ch));
                value = "";
            }
        }
        values.add(value);

        OperationOperator tree = new OperationOperator();
        tree.setOperator(operators.pollLast());
        OperationInterface[] leaves = new OperationInterface[2];
        value = values.pollLast();
        if (IsCoordinates(value)){
            leaves[0]= new OperationCell(value);
        }else if (IsDouble(value)){
            leaves[0]= new OperationNumber(Double.parseDouble(value));
        }
        value = values.pollLast();
        if (IsCoordinates(value)){
            leaves[1]= new OperationCell(value);
        }else if (IsDouble(value)){
            leaves[1]= new OperationNumber(Double.parseDouble(value));
        }
        tree.setLeaves(leaves);

        this.tree=tree;
    }

    public double numericalValue(Spreadsheet spreadsheet) {
        return tree.getValue(spreadsheet);
    }
    public String textValue(Spreadsheet spreadsheet){
        return tree.getValue(spreadsheet)+"";
    }


    // Getters & Setters

    public OperationOperator getTree() {
        return tree;
    }

    public void setTree(OperationOperator tree) {
        this.tree = tree;
    }
}

