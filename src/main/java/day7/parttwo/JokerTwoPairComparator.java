package day7.parttwo;

import day7.Hand;
import day7.HandTypeComparator;
import day7.partone.TwoPairComparator;

import java.util.HashMap;

public class JokerTwoPairComparator implements HandTypeComparator {
    private final TwoPairComparator twoPairComparator = new TwoPairComparator();
    @Override
    public boolean inThisHand(Hand hand) {
        switch (hand.figureCount().getOrDefault("J", 0)) {
            case 0:
                return twoPairComparator.inThisHand(hand);
            case 1:
                return hand.figureCount().containsValue(2);
        }
        return true;
    }

}
