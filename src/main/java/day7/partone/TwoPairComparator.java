package day7.partone;


import day7.Hand;
import day7.HandTypeComparator;

public class TwoPairComparator implements HandTypeComparator {
    @Override
    public boolean inThisHand(Hand hand) {
        return hand.figureCount().values().stream().filter(h -> h==2).count() == 2;
    }
}
