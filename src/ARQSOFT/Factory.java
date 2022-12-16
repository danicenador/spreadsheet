package ARQSOFT;

import FormulaComposite.*;
import parser.AbstractFactory;
import parser.AbstractNode;

public class Factory implements AbstractFactory {
    @Override
    public AbstractNode buildOperator(char op, AbstractNode rawLeft, AbstractNode rawRight) {
        FormulaTree left = (FormulaTree) rawLeft;
        FormulaTree right = (FormulaTree) rawRight;
        if (op=='+'){
            return new OperatorPlus(left,right);
        } else if (op=='-'){
            return new OperatorMinus(left,right);
        }else if (op=='*'){
            return new OperatorMultiplication(left,right);
        }else{
            return new OperatorDivision(left,right);
        }
    }

    @Override
    public AbstractNode buildFunction(String name, AbstractNode[] rawParameters) {

        FormulaTree[] parameters = new FormulaTree[rawParameters.length];
        int j=0;
        for(AbstractNode i:rawParameters){
            parameters[j]=(FormulaTree) i;
            j++;
        }
        if (name.equals("SUMA")){
            return new Suma(parameters);
        } else if (name.equals("MIN")){
            return new Min(parameters);
        }else if (name.equals("MAX")){
            return new Max(parameters);
        }else{
            return new Promedio(parameters);
        }
    }

    @Override
    public AbstractNode buildNumberConstant(double number) {
        return new NumberElement(number);
    }
    @Override
    public AbstractNode buildCellReference(String reference) {
        return new CellElement(reference);
    }

    @Override
    public AbstractNode buildTextConstant(String text) {
        return new AbstractNode() {
        };
    }



    @Override
    public AbstractNode buildError(String detail) {
        return new AbstractNode() {
        };
    }
}