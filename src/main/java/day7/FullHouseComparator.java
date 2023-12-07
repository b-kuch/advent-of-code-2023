package day7;

import java.util.HashMap;
import java.util.Map;

public class FullHouseComparator implements HandTypeComparator {
    private static final SecondRuleComparator secondRuleComparator = new SecondRuleComparator();
    private ValueComparator valueComparator = new ValueComparator();
    private HighHandComparator highHandComparator = new HighHandComparator();

    @Override
    public boolean inThisHand(HashMap<String, Integer> hand) {
        var twosAndThrees = hand.values().stream().filter(count -> count == 2 || count == 3).toList();
        return twosAndThrees.contains(3) && twosAndThrees.contains(2);
    }

    @Override
    public int compare(HashMap<String, Integer> hand1, HashMap<String, Integer> hand2) {
        return compareThrees(hand1, hand2);
    }

    private int compareThrees(HashMap<String, Integer> hand1, HashMap<String, Integer> hand2) {

        var firstThree  = getThree(hand1).getKey();
        var secondThree = getThree(hand1).getKey();
        var compared = valueComparator.compare(firstThree, secondThree);
        return compared != 0 ? compared : comparePair(hand1, hand2);
    }

    private int comparePair(HashMap<String, Integer> hand1, HashMap<String, Integer> hand2) {
        var firstThree  = getPair(hand1).getKey();
        var secondThree = getPair(hand1).getKey();
        var compared = valueComparator.compare(firstThree, secondThree);
        return compared != 0 ? compared : highHandComparator.compare(hand1, hand2);
    }

    private static Map.Entry<String, Integer> getPair(HashMap<String, Integer> hand) {
        return hand.entrySet().stream().filter(entry -> entry.getValue() == 2).findAny().get();
    }

    private static Map.Entry<String, Integer> getThree(HashMap<String, Integer> hand) {
        return hand.entrySet().stream().filter(entry -> entry.getValue() == 3).findAny().get();
    }
}
