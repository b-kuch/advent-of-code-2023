package day7;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ValueComparator implements Comparator<String> {
    private final Map<String, Integer> figures;

    public static ValueComparator partOneOrder() {
        return new ValueComparator(
                List.of("A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2"));
    }

    public static ValueComparator partTwoOrder() {
        return new ValueComparator(
                List.of("A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2", "J"));
    }

    public ValueComparator(List<String> cards) {
        figures = cards.stream()
                .collect(Collectors.toMap(Function.identity(), new IndexFunction(idx -> cards.size() - idx)));
    }

    public int compare(String thisCard, String thatCard) {
        return figures.get(thisCard) - figures.get(thatCard);
    }
}
