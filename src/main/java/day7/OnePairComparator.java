package day7;

import java.util.HashMap;

public class OnePairComparator implements HandTypeComparator {

    private ValueComparator cardValueComparator = new ValueComparator();
    private HighHandComparator highHandComparator;

    @Override
    public boolean inThisHand(HashMap<String, Integer> hand) {
        var pairCount = hand.values().stream().filter(count -> count != 2).map(count -> count == 1).count();
        return pairCount == 1;
    }

    @Override
    public int compare(HashMap<String, Integer> hand1, HashMap<String, Integer> hand2) {
        var handOnePair = getPair(hand1);
        var handTwoPair = getPair(hand2);
        var pairComparison = cardValueComparator.compare(handOnePair, handTwoPair);
        return pairComparison != 0 ? pairComparison : highHandComparator.compare(hand1, hand2);
    }

    private String getPair(HashMap<String, Integer> hand) {
        return hand.entrySet().stream().filter(cardType -> cardType.getValue() == 2).findAny().get().getKey();
    }
}
