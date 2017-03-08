import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Mail mail2 = new Mail("mail_2",Mail.Format.HTML);
        System.out.println(mail2.toString());

        Scanner scanner = new Scanner(System.in);
        String nazwaPliku = scanner.nextLine();
        int rozmiarPliku = scanner.nextInt();

        Attachment attachment = new Attachment(nazwaPliku,rozmiarPliku);
        System.out.println(attachment.toString());
    }
}
