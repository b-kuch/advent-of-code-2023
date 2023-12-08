package day7.parttwo;

import day7.Hand;
import day7.HandTypeComparator;
import day7.partone.FullHouseComparator;

import static day7.parttwo.JokerHelper.getBiggestExceptJoker;

public class JokerFullHouseComparator implements HandTypeComparator {
    private final FullHouseComparator fullHouseComparator = new FullHouseComparator();

    @Override
    public boolean inThisHand(Hand hand) {
        return switch (hand.figureCount().getOrDefault("J", 0)) {
            case 0 -> fullHouseComparator.inThisHand(hand);
            case 1 -> hand.figureCount().containsValue(3) || hand.figureCount().values().stream().filter(v -> v.equals(2)).count() == 2;
            case 2 -> getBiggestExceptJoker(hand) >= 2;
            default -> true;
        };
    }
}
