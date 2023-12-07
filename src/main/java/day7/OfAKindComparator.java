package day7;

import java.util.HashMap;

public abstract class OfAKindComparator implements HandTypeComparator {
    private static final CountAndValueComparator countAndValueComparator = new CountAndValueComparator();
    private static final ValueComparator valueComparator = new ValueComparator();
    @Override
    public boolean inThisHand(HashMap<String, Integer> hand) {
        return hand.entrySet().stream().filter(card -> card.getValue() == getKind()).count() == 1;
    }

    @Override
    public int compare(HashMap<String, Integer> hand1, HashMap<String, Integer> hand2) {
        var sortedHand1 = hand1.entrySet().stream().sorted(countAndValueComparator.reversed()).toList();
        var sortedHand2 = hand2.entrySet().stream().sorted(countAndValueComparator.reversed()).toList();

        for (int i = 0; i < sortedHand1.size(); i++) {
            int compared = valueComparator.compare(sortedHand1.get(i).getKey(), sortedHand2.get(i).getKey());
            if (compared != 0) {
                return compared;
            }
        }
        return 0;
    }

    protected abstract int getKind();
}
