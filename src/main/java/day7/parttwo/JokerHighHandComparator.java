package day7.parttwo;

import day7.Hand;
import day7.HandTypeComparator;

public class JokerHighHandComparator implements HandTypeComparator {
    @Override
    public boolean inThisHand(Hand hand) {
        return true;
    }
}
