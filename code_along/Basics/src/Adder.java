import java.util.Scanner;

public class Adder {
    public static void main(String[] args) {
        int sum = 0;
        int firstNum = 0;
        int secondNum = 0;

        Scanner myScanner = new Scanner(System.in);
        System.out.println("Input the first number");
        firstNum  = Integer.parseInt(myScanner.nextLine());
        // 여기서 바로 type casting 사용해서 (int) 못쓴다. 이거는 python에서나 자동으로 해주는 것.
        // 모든 primitive type들은 string을 parse할 수 있는 method가 있다.
        System.out.println("Input the second number");
        secondNum  = Integer.parseInt(myScanner.nextLine());

        sum = firstNum + secondNum;

        System.out.println("Sum: " + sum);
    }
}
