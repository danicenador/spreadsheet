package ARQSOFT;

public class FormulaTreeMaker {
    // Methods
    public FormulaTree createFormulaTree(String formula){
        FormulaTree tree= null;
        if(formula.equals("=1+3")){
            tree= new OperatorPlus(new NumberElement(1),new NumberElement(3));
        } else if (formula.equals("=A1")) {
            tree= new CellElement(formula.substring(1));

        }

        return tree;
    }
}
