package ARQSOFT;

public class OperationOperator implements OperationInterface{
    // Attributes
    private OperationInterface[] leaves = new OperationInterface[2];
    private String operator;

    // Methods
    @Override
    public double getValue(Spreadsheet spreadsheet){
        double a=leaves[1].getValue( spreadsheet);
        double b=leaves[0].getValue( spreadsheet);
        if(operator.equals("+")){
            return a+b;
        } else if (operator.equals("-")) {
            return a-b;
        }else if (operator.equals("*")) {
            return a*b;
        }else if (operator.equals("/")) {
            return a/b;
        }else{
            return -1;
        }

    };

    // Setters & Getters
    public OperationInterface[] getLeaves() {
        return leaves;
    }

    public void setLeaves(OperationInterface[] leaves) {
        this.leaves = leaves;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
