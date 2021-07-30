package StateCapitals3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class StateCapitalsApp {

    public static void main(String[] args) throws Exception{
        Scanner fileScanner = new Scanner(new BufferedReader(new FileReader("MoreStateCapitals.txt")));
        Map<String, Capital> stateCapital = new HashMap<>();

        while (fileScanner.hasNextLine()){
            String currLine = fileScanner.nextLine();
            String[] parts = currLine.split("::");
            stateCapital.put(parts[0], new Capital(parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3])));
        }

        for (String key: stateCapital.keySet()) {
            String name = stateCapital.get(key).getName();
            int population = stateCapital.get(key).getPopulation();
            double squareMileage = stateCapital.get(key).getSquareMileage();
            System.out.println(key + " - " + name + " | Pop:" + population + " | Area:" + squareMileage + " sq mi");
        }
        findStatesWithMinPopOver(stateCapital);
    }

    public static void findStatesWithMinPopOver(Map<String, Capital> stateCapital){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a population number");
        int popLimit = Integer.parseInt(sc.nextLine());
        for (String key: stateCapital.keySet()) {
            String name = stateCapital.get(key).getName();
            int population = stateCapital.get(key).getPopulation();
            if (population > popLimit) {
                System.out.println(key + " - " + name + " | Pop:" + population);
            }
        }
    }
}
