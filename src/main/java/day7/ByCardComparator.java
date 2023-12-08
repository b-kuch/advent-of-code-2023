package day7;

import java.util.List;

public class ByCardComparator implements java.util.Comparator<Bid> {
    private static final List<HandTypeComparator> handTypeComparators =
            List.of(
                    new FiveOfAKindComparator(),
                    new FourOfAKindComparator(),
                    new FullHouseComparator(),
                    new ThreeOfAKindComparator(),
                    new TwoPairComparator(),
                    new OnePairComparator(),
                    new HighHandComparator()
            );
    private final SecondRuleComparator secondRuleComparator = new SecondRuleComparator();

    @Override
    public int compare(Bid bid1, Bid bid2) {
        for (var handType :
                handTypeComparators) {
            boolean isHandOneOfCurrentType = handType.inThisHand(bid1.hand());
            boolean isHandTwoOfThisType = handType.inThisHand(bid2.hand());

            if (isHandOneOfCurrentType && isHandTwoOfThisType) {
               return secondRuleComparator.compare(bid1.hand().cards(), bid2.hand().cards());
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
}
