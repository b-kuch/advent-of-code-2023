package day7;

import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Hand(String cards, HashMap<String, Integer> figureCount) {

    Hand(String hand) {
        this(hand, handByFigures(hand));
    }

    static HashMap<String, Integer> handByFigures(String hand) {
        return getStringByChar(hand)
                .collect(Collectors.toMap(Function.identity(), character -> 1, Integer::sum, HashMap::new));
    }

    private static Stream<String> getStringByChar(String hand) {
        return IntStream.range(0, hand.length())
                .mapToObj(
                        i -> hand.substring(i, i + 1));
    }
}
