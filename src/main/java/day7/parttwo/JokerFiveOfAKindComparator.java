package day7.parttwo;

import day7.Hand;
import day7.HandTypeComparator;

public class JokerFiveOfAKindComparator implements HandTypeComparator {
    @Override
    public boolean inThisHand(Hand hand) {
        return JokerHelper.getBestJokerHand(hand, 5);
    }
}
