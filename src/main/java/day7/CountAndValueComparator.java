package day7;

import java.util.Comparator;
import java.util.Map;

public class CountAndValueComparator implements Comparator<Map.Entry<String, Integer>> {
    private static final ValueComparator valueComparator = new ValueComparator();
    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        var countCompare = o1.getValue() - o2.getValue();
        return countCompare != 0 ? countCompare : valueComparator.compare(o1.getKey(), o2.getKey());
    }
}
