class Operator {
    enum Association{Left,Right}
    private Association association;
    private String operator;
    private int priority;

    Operator(String operator,int priority,Association association){
        this.association = association;
        this.operator = operator;
        this.priority = priority;
    }
    Association getAssociation(){ return association; }
    int getPriority(){ return priority; }
    char getChar(){ return operator.charAt(0); }

    @Override public String toString() { return operator;}
}

