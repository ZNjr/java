class Expression{

    private Operator[] operators;
    Operator[] operatorsInExp;
    int amountOfOperators;
    String expression;
    StringBuilder result;

    Expression(Operator[] operators, String expression){
        this.operators = operators;
        this.operatorsInExp = new Operator[expression.length()];
        this.expression = expression;
        this.result = new StringBuilder();
        this.amountOfOperators = 0;
    }

    boolean isOperand(char character){
        return character >= 'a' && character <= 'z';
    }

    void cleanUpExpression(){
        StringBuilder tmpExpression = new StringBuilder();
        int j=0;
        for(int i=0;i<expression.length();i++){
            char character = expression.charAt(i);
            Operator operator = toOperator(character);
            if(operator!=null){
                operatorsInExp[j++] = operator;
                tmpExpression.append(character);
            }
            else if(isOperand(character)) tmpExpression.append(character);
        }
        expression = tmpExpression.toString();
        amountOfOperators = j;
   }
   private Operator toOperator(char character){
        for (Operator operator : operators) {
            if (operator.getChar() == character) return operator;
        }
        return null;
    }
    @Override public String toString() { return result.toString();}
}

