package day7;

import day7.partone.*;
import day7.parttwo.*;

import java.util.List;

public class ByCardComparator implements java.util.Comparator<Bid> {
    private List<HandTypeComparator> handTypeComparators;

    private SecondRuleComparator secondRuleComparator;

    public ByCardComparator(List<HandTypeComparator> comparators, SecondRuleComparator secondRuleComparator) {
        this.handTypeComparators = comparators;
        this.secondRuleComparator = secondRuleComparator;
    }

    public static ByCardComparator withPartOneRules() {
        return new ByCardComparator(List.of(
                new FiveOfAKindComparator(),
                new FourOfAKindComparator(),
                new FullHouseComparator(),
                new ThreeOfAKindComparator(),
                new TwoPairComparator(),
                new OnePairComparator(),
                new HighHandComparator()
        ),
                new SecondRuleComparator(ValueComparator.partOneOrder()));
    }

    public static ByCardComparator withPartTwoRules() {
        return new ByCardComparator(List.of(
                new JokerFiveOfAKindComparator(),
                new JokerFourOfAKindComparator(),
                new JokerFullHouseComparator(),
                new JokerThreeOfAKindComparator(),
                new JokerTwoPairComparator(),
                new JokerOnePairComparator(),
                new JokerHighHandComparator()
        ),
                new SecondRuleComparator(ValueComparator.partTwoOrder()));
    }

    private ByCardComparator() {}

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
