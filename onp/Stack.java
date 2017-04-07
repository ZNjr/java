class Stack<T>{
    private T stack[];
    private int licznik;

    Stack(){
        this.stack = (T[]) new Object[256];
        this.licznik = -1;
    }
    boolean empty(){
        return licznik==-1;
    }
    T top(){
        return stack[licznik];
    }
    T pop(){
        return stack[licznik--];
    }
    void push(T newValue){
        stack[++licznik] = newValue;
    }

    private int size(){
        return licznik+1;
    }
    void out(){
        for(int i=0;i<size();i++)
            System.out.print(stack[i]+" ");
        System.out.println();
    }
}

