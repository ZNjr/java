//Zbigniew Najder -1
import java.util.Scanner;

public class Source {
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int amountOfExpresions = Integer.parseInt(in.nextLine().trim());
        while (amountOfExpresions-- !=0){
            String expression = in.nextLine();
            String expressionType = expression.substring(0,4);
            expression = expression.substring(5);
            switch (expressionType){
                case "INF:":
                    Onp onp = new Onp(expression);
                    System.out.println("ONP: "+onp);
                    break;
                case "ONP:":
                    Inf inf = new Inf(expression);
                    System.out.println("INF: "+inf);
                    break;
            }
        }
    }
}

