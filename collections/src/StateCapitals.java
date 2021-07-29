import java.util.Map;
import java.util.HashMap;

public class StateCapitals {
    public static void main(String[] args) {
        Map<String, String> stateCapitals = new HashMap<>();
        stateCapitals.put("Alabama", "Montgomery");
        stateCapitals.put("Alaska", "Juneau");
        stateCapitals.put("Arizona", "Phoenix");
        stateCapitals.put("Arkansas", "Little Rock");

        System.out.println("STATES:");
        System.out.println("=======");
        for(String k: stateCapitals.keySet()){
            System.out.println(k);
        }
        System.out.println("\nCAPITALS:");
        System.out.println("=======");
        for(String v: stateCapitals.values()){
            System.out.println(v);
        }
        System.out.println("\nTATE/CAPITAL PAIRS:");
        System.out.println("==============");
        String v;
        for(String k: stateCapitals.keySet()){
            System.out.println(k + " - " + stateCapitals.get(k));
        }
    }
}
