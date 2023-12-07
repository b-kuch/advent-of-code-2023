package day7;

import java.util.HashMap;
import java.util.List;

public class HighHandComparator implements HandTypeComparator {
    private final ValueComparator valueComparator = new ValueComparator();

    @Override
    public boolean inThisHand(HashMap<String, Integer> hand) {
        return true;
    }

    @Override
    public int compare(HashMap<String, Integer> hand1, HashMap<String, Integer> hand2) {
        var sortedHand1 = getSortedHand(hand1);
        var sortedHand2 = getSortedHand(hand2);
        for (int i = 0; i < sortedHand1.size(); i++) {
            int compared = valueComparator.compare(sortedHand1.get(i), sortedHand2.get(i));
            if (compared != 0) {
                return compared;
            }
        }

        return 0;
    }

    private List<String> getSortedHand(HashMap<String, Integer> hand) {
        return hand.keySet().stream().sorted(valueComparator.reversed()).toList();
    }
}
