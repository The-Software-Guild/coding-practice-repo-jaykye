import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Reading {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("OutFile.txt")));
        // go through the file line by line
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            System.out.println(currentLine);
        }
    }
}
