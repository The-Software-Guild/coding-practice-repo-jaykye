import java.util.Scanner;

public class Factorizer {
    public static void main(String[] args) {
        String inputVal;
        int target;
        int[] targetTable;
        int sum;
        int numberOfFactors;
        // perfect number -> all factors adds up to itself.
        // prime number

        Scanner myScanner = new Scanner(System.in);
        System.out.println("What number would you like to factor?");
        target = Integer.parseInt(myScanner.nextLine());
        sum =0;
        numberOfFactors =0;
        targetTable = new int[target];  // ignore 0 and ignore target. index == factor

        System.out.println("The factors of 6 are:");
        for (int i=1; i<target-1; i++){
            if (target % i == 0){
                targetTable[i] = 1;
                System.out.print(i + " ");
                sum += i;
                numberOfFactors+=1;
            }
        }

        System.out.println("\n" + target + " has " + numberOfFactors + " factors.");
        if (sum == target) {
            System.out.println(target + " is a perfect number.");
        }
        else{
            System.out.println(target + " is not a perfect number.");
        }

        if (numberOfFactors == 1) {
            System.out.println(target + " is a prim number.");
        }
        else{
            System.out.println(target + " is not a prime number.");
        }


    }
}
