import java.util.Random;

public class WindowMaster {
    public static void main(String[] args) {
        String input = "";
        double windowHeight = 0;
        double windowWidth = 0;
        double area = 0;

        Random fixedSeed = new Random(112358);
        Random timedSeed = new Random();
        System.out.println(fixedSeed.nextInt());
        System.out.println(timedSeed.nextInt());

    }
}
