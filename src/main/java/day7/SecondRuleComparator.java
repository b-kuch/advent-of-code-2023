package day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SecondRuleComparator {
    private ValueComparator valueComparator = new ValueComparator();

    int compare(HashMap<String, Integer> hand1, HashMap<String, Integer> hand2) {
        var hand1Cards = hand1.entrySet().stream().map(this::toString).collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
        var hand2Cards = hand2.entrySet().stream().map(this::toString).collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);

        for (int i = 0; i < hand1Cards.size(); i++) {
            var compared = valueComparator.compare((String)hand1Cards.get(i), (String)hand2Cards.get(i));
            if (compared != 0) {
                return compared;
            }
        }
        return 0;
    }

    List<String> toString(Map.Entry<String, Integer> cards) {
        var result = new ArrayList<String>();
        for (int i = 0; i < cards.getValue(); i++) {
                result.add(cards.getKey());
        }
        return result;
    }
}
