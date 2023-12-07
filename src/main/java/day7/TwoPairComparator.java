package day7;

import java.util.HashMap;
import java.util.Map;

public class TwoPairComparator implements HandTypeComparator {
    private static final CountAndValueComparator countAndValueComparator = new CountAndValueComparator();
    private static final ValueComparator valueComparator = new ValueComparator();
    private static final SecondRuleComparator secondRuleComparator = new SecondRuleComparator();

    @Override
    public boolean inThisHand(HashMap<String, Integer> hand) {
        return hand.values().stream().filter(h -> h==2).count() == 2;
    }

    @Override
    public int compare(HashMap<String, Integer> hand1, HashMap<String, Integer> hand2) {
        var strongerPair1 = hand1.entrySet().stream().filter(entry -> entry.getValue() == 2).max(countAndValueComparator).map(Map.Entry::getKey).get();
        var strongerPair2 = hand2.entrySet().stream().filter(entry -> entry.getValue() == 2).max(countAndValueComparator).map(Map.Entry::getKey).get();

        var firstPairResult = valueComparator.compare(strongerPair1, strongerPair2);
        return firstPairResult != 0 ? firstPairResult : secondRuleComparator.compare(hand1, hand2);
    }


}
