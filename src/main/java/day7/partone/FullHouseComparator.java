package day7.partone;

import day7.Hand;
import day7.HandTypeComparator;

public class FullHouseComparator implements HandTypeComparator {
    @Override
    public boolean inThisHand(Hand hand) {
        var figures = hand.figureCount().values();
        return figures.contains(3) && figures.contains(2);
    }
}
