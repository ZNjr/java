class Onp extends Expression {
    Onp(String expression) {
        super(new Operator[]{
                new Operator("(", -1, Operator.Association.Left),
                new Operator(")", -1, Operator.Association.Right),
                new Operator("=", 0, Operator.Association.Right),
                new Operator("<", 1, Operator.Association.Left),
                new Operator(">", 1, Operator.Association.Left),
                new Operator("+", 2, Operator.Association.Left),
                new Operator("-", 2, Operator.Association.Left),
                new Operator("*", 3, Operator.Association.Left),
                new Operator("/", 3, Operator.Association.Left),
                new Operator("%", 3, Operator.Association.Left),
                new Operator("^", 4, Operator.Association.Right),
                new Operator("~", 5, Operator.Association.Right),
        }, expression);
        this.stack = new Stack<>();
        toOnp();
    }
    private Stack<Operator> stack;
    private void toOnp(){
        cleanUpExpression();
        if (isCorrect()){
            int indexOfCurrentOperator = 0;
            for (int i = 0; i < expression.length(); i++){
                if (isOperand(expression.charAt(i)))
                    result.append(expression.charAt(i));
                else {
                    Operator currentOperator = operatorsInExp[indexOfCurrentOperator++];
                    if (stack.empty() || currentOperator.getChar() == '(')
                        stack.push(currentOperator);
                    else if (currentOperator.getChar() == ')') {
                        while (stack.top().getChar() != '(')
                            result.append(stack.pop().getChar());
                        stack.pop();
                    } else if (isPriorytyHigher(stack.top(), currentOperator)) {
                        while (!stack.empty() && isPriorytyHigher(stack.top(), currentOperator))
                            result.append(stack.pop().getChar());
                        stack.push(currentOperator);
                    } else stack.push(currentOperator);
                }
            }
            while (!stack.empty())
                result.append(stack.pop().getChar());
        } else result.append("error");
    }

    private boolean isPriorytyHigher(Operator op1, Operator op2) {
        return (op1.getAssociation() == Operator.Association.Left && op1.getPriority() >= op2.getPriority())
                || (op1.getAssociation() == Operator.Association.Right && op1.getPriority() > op2.getPriority());
    }

    private boolean isCorrect(){
        int amountOfIncorrectBrackets = 0;
        boolean isOperandNext = true;
        boolean canBeOperatorNext = true;
        for (int i = 0; i < expression.length(); i++){
            if (expression.charAt(i) == '(') amountOfIncorrectBrackets++;
            else if(expression.charAt(i) == ')' && amountOfIncorrectBrackets==0) return false;
            else if (expression.charAt(i) == ')' && isOperatorBetweenBrackets(i)) amountOfIncorrectBrackets--;
            else if (isOperandNext && isOperand(expression.charAt(i))) {
                isOperandNext = false;
                canBeOperatorNext = true;
            }
            else if (!isOperandNext && isOperand(expression.charAt(i))) return false;
            else if ((isOperandNext && expression.charAt(i)!='~') || (!canBeOperatorNext && expression.charAt(i)!='~')) return false;
            else if(expression.charAt(i)=='~') canBeOperatorNext = false;
            else
                isOperandNext = true;
        }
        return (amountOfIncorrectBrackets == 0 && isEqCorrect() && !isOperandNext);
    }

    private boolean isEqCorrect(){
        boolean wasOtherOperator = false;
        for(int i=0;i<amountOfOperators;i++){
            if(wasOtherOperator && operatorsInExp[i].getChar() == '=' && !isEqBetweenBrackets(i)) return false;
            else if(operatorsInExp[i].getChar()!='=' && operatorsInExp[i].getChar()!=')'&& operatorsInExp[i].getChar()!='(' ) wasOtherOperator = true;
        }
        return true;
    }
    private boolean isEqBetweenBrackets(int indexOfEq){
        if(indexOfEq==0) return true;
        else if(indexOfEq>0 && operatorsInExp[indexOfEq-1].toString().equals("(")) return true;
        return false;
    }
    private boolean isOperatorBetweenBrackets(int index){
        if (index>0 && expression.charAt(index-1)=='~') return false;
        while (--index >= 0 && expression.charAt(index) != '(')

            if (isOperand(expression.charAt(index))) return true;
        return false;
    }
}