package day7;

public class SecondRuleComparator {
    private final ValueComparator valueComparator = new ValueComparator();

    int compare(String hand1, String hand2) {
        for (int i = 0; i < hand1.length(); i++) {
            var compared = valueComparator.compare(hand1.substring(i, i+1), hand2.substring(i, i+1));
            if (compared != 0) {
                return compared;
            }
        }
        return 0;
    }
}
