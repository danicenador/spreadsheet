package ARQSOFT;

public class FormulaTreeMaker {
    // Methods
    public FormulaTree createFormulaTree(String formula){
        FormulaTree tree= null;
        if(formula.equals("=1+3")){
            tree= new OperatorPlus(new NumberElement(1),new NumberElement(3));
        } else if (formula.equals("=A1")) {
            tree= new CellElement(formula.substring(1));

        }else if (formula.equals("=PROMEDIO(5;7;28)")) {
            FormulaTree[] inputs = new FormulaTree[3];
            inputs[0]=new NumberElement(5);
            inputs[1]=new NumberElement(7);
            inputs[2]=new NumberElement(28);
            tree= new Promedio(inputs);

        }

        return tree;
    }
}
