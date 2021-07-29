import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class StateCapitals2 {
    public static void main(String[] args) throws Exception{
        Scanner fileScanner = new Scanner(new BufferedReader(new FileReader("StateCapitals.txt")));
        Map<String, String> stateCapital = new HashMap<>();

        while (fileScanner.hasNextLine()){
            String currLine = fileScanner.nextLine();
            String[] parts = currLine.split("::");
            stateCapital.put(parts[0], parts[1]);
//            System.out.println(parts[0] + ", " + parts[1]);
        }
        int sizeOfMap = stateCapital.size();
        System.out.println("There are " + sizeOfMap + "pairs in the file.");
        System.out.println("State names:");
        System.out.println(stateCapital.keySet());

        Scanner sc = new Scanner(System.in);
        int numRounds =0;
        boolean validAnswer = false;
        while (!validAnswer){
            try {
                System.out.println("How many times do you want to play guess the capital game?");
                numRounds = Integer.parseInt(sc.nextLine());
                validAnswer = true;
            }
            catch (NumberFormatException  e){
                System.out.println("Enter integer.");
            }
        }
        for (int i=0; i<numRounds; i++) {
            playGame(stateCapital);
        }
    }

    public static String selectRandomKey(Map<String, String> stateCapital){
        int sizeOfMap = stateCapital.size();
        Random randSeed = new Random();
        int selectedIdx = randSeed.nextInt(sizeOfMap);
        int i = 0;
        String out = "";
        for (String key: stateCapital.keySet()){
            out = key;
            if (i == selectedIdx){
                break;
            }
            i++;
        }
        // Should raise exception when the keyset is empty.
        return out;
    }

    public static void playGame(Map<String, String> stateCapital){
        // knowledge game.
        String selectedState = selectRandomKey(stateCapital);
        Scanner gameSacnner = new Scanner(System.in);
        System.out.println("What is the capital of " + selectedState + "?");
        String userAnswer = gameSacnner.nextLine();
        if (Objects.equals(userAnswer, stateCapital.get(selectedState))){
            System.out.println("You are correct!");
        }
        else {
            System.out.println("You are wrong!");
        }

    }
}
