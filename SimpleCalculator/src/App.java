import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        boolean run = true;
        Scanner inputScanner = new Scanner(System.in);
        String input = "";

        while (run) {
                System.out.println("Chose operation to perform.");
                input = inputScanner.nextLine();
                // one operation is exit.

                // ask for two operands.  -- gonna be numbers.
                input = inputScanner.nextLine();
                input = inputScanner.nextLine();

        }
    }
}
