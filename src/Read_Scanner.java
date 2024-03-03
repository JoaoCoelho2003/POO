import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Read_Scanner {
    public static int getInt(Scanner sc){
        int n = 0;
        try{
            n = sc.nextInt();
        } catch (InputMismatchException ex){
            System.out.println("A informação introduzida não é válida! Insira novamente: ");
            sc.nextLine();
            n = getInt(sc);
        }
        return n;
    }

    public static double getDouble(Scanner sc){
        double n = 0;
        try{
            n = sc.nextDouble();
        } catch (InputMismatchException ex){
            System.out.println("A informação introduzida não é válida! Insira novamente: ");
            sc.nextLine();
            n = getDouble(sc);
        }
        return n;
    }

    public static LocalDate getData(Scanner sc){
        LocalDate d = null;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String input = sc.nextLine();
        try {
            d = LocalDate.parse(input, formatter);
        } catch (DateTimeParseException ex) {
            System.out.println("A informação introduzida não é válida! Insira novamente: ");
            d = getData(sc);
        }
        return d;
    }
}
