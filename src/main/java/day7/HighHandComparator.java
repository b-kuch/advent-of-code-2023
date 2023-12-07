package day7;

import java.util.HashMap;

public class HighHandComparator implements HandTypeComparator {
    private final ValueComparator valueComparator = new ValueComparator();

    @Override
    public boolean inThisHand(HashMap<String, Integer> hand) {
        return true;
    }

    @Override
    public int compare(HashMap<String, Integer> hand1, HashMap<String, Integer> hand2) {
        var sortedHand1 = hand1.keySet().stream().sorted(valueComparator).toList();
        var sortedHand2 = hand2.keySet().stream().sorted(valueComparator).toList();
        for (int i = 0; i < 5; i++) {
            if (valueComparator.compare(sortedHand1.get(i), sortedHand2.get(i)) != 0) {
                return valueComparator.compare(sortedHand1.get(i), sortedHand2.get(i));
            }
        }
        ;
        return 0;
    }
}
