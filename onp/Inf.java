class Inf extends Expression {
    private class Action{
        Action(char character){
            this.string = new StringBuilder();
            string.append(character);
        }

        Action(Operator operator,String string){
            this.string = new StringBuilder();
            this.string.append(string);
            this.highestPriorytyOperator = operator;
        }

        private StringBuilder string;
        private Operator highestPriorytyOperator;

        void addBrackets(){
            String tmp = string.toString();
            string = new StringBuilder();
            string.append("(").append(tmp).append(")");
        }

        int getPriority(){
            return highestPriorytyOperator.getPriority();
        }

        void addMinus(){
            String tmp = string.toString();
            string = new StringBuilder();
            string.append("~").append(tmp);
        }
        @Override public String toString() {
            return string.toString();
        }
    }


    Inf(String exprassion) {
        super(new Operator[]{
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
        }, exprassion);
        this.stack = new Stack<>();
        toInf();
    }

    private Stack<Action> stack;

    private void toInf() {
        cleanUpExpression();
        if (isCorrect()) {
            int indexOfCurrentOperator = 0;
            for (int i = 0; i < expression.length(); i++){
                if(isOperand(expression.charAt(i))) stack.push(new Action(expression.charAt(i)));
                else if(expression.charAt(i)=='~'){
                    if(stack.top().highestPriorytyOperator!=null)
                        stack.top().addBrackets();
                    stack.top().highestPriorytyOperator = operatorsInExp[indexOfCurrentOperator];
                    stack.top().addMinus();
                    indexOfCurrentOperator++;
                } else{
                    Action rightAction = stack.pop();
                    Action leftAction = stack.pop();
                    if(rightAction.highestPriorytyOperator!= null && operatorsInExp[indexOfCurrentOperator].getPriority()>=rightAction.getPriority()){
                        rightAction.addBrackets();
                    }
                    if(leftAction.highestPriorytyOperator!= null && operatorsInExp[indexOfCurrentOperator].getPriority()>leftAction.getPriority()){
                        leftAction.addBrackets();
                    }
                    stack.push(new Action(operatorsInExp[indexOfCurrentOperator], leftAction.toString()+operatorsInExp[indexOfCurrentOperator++].toString()+rightAction.toString()));
                }
            }
            result.append(stack.top().toString());
        } else result.append("error");
    }

    private boolean isCorrect() {
        int operandsToUse = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (isOperand(expression.charAt(i))) operandsToUse++;
            else {
                if (expression.charAt(i) == '~' && operandsToUse < 1) return false;
                else if (expression.charAt(i) != '~' && operandsToUse < 2) return false;
                else if (expression.charAt(i) != '~') {
                    operandsToUse -= 1;
                }
            }
        }
        return isEqCorrect() && operandsToUse == 1;
    }

    private boolean isEqCorrect(){
        boolean canBeEqNext = true;
        boolean lastWasEq = false;
        boolean wasOperandBetweenEq = false;
        int indexOfCurrentOperator = amountOfOperators-1;
        for(int i=expression.length()-1;i>=0;i--){
            if(isOperand(expression.charAt(i)) || (expression.charAt(i)!='=' && wasOperandBetweenEq)){
                if(lastWasEq && canBeEqNext && indexOfCurrentOperator>=0 && !operatorsInExp[indexOfCurrentOperator].toString().equals("=")) return false;
                wasOperandBetweenEq = true;
                // TODO: 2017-04-06 ONP: abcd*ab=/+=
            }else{
                if (!canBeEqNext && operatorsInExp[indexOfCurrentOperator].getChar() == '=' ) return false;
                else if (operatorsInExp[indexOfCurrentOperator].getChar() != '=' && wasOperandBetweenEq) canBeEqNext = false;
                lastWasEq = operatorsInExp[indexOfCurrentOperator].getChar()=='=';
                indexOfCurrentOperator--;
            }
        }
        return true;
    }
}
