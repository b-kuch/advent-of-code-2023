package day7;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ByCardComparator implements java.util.Comparator<Bid> {
    private static final List<HandTypeComparator> handTypeComparators =
            List.of(
                    new OnePairComparator(),
                    new HighHandComparator()
            );

    // negative when first is smaller
    @Override
    public int compare(Bid bid1, Bid bid2) {
        var hand1 = handByFigures(bid1.hand());
        var hand2 = handByFigures(bid2.hand());

        for (var handType :
                handTypeComparators) {
            boolean isHandOneOfCurrentType = handType.inThisHand(hand1);
            boolean isHandTwoOfThisType = handType.inThisHand(hand2);
            if (isHandOneOfCurrentType && isHandTwoOfThisType) {
               return handType.compare(hand1, hand2);
            }
            if (isHandOneOfCurrentType) {
                return 1;
            }
            if (isHandTwoOfThisType) {
                return -1;
            }
        }
        return 0;
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
