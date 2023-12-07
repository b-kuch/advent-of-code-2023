package day7;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueComparator implements Comparator<String> {
    private static final int FIGURE_COUNT = 13;

    private final static Map<String, Integer> figures =
            Stream.of("A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2")
                    .collect(Collectors.toMap(Function.identity(), new StreamIndexer(idx -> FIGURE_COUNT - idx)));

    public int compare(String thisCard, String thatCard) {
        return figures.getOrDefault(thisCard, 0) - figures.getOrDefault(thatCard, 0);
    }
}
