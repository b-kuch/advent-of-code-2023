package day7.partone;


import day7.Hand;
import day7.HandTypeComparator;

public class HighHandComparator implements HandTypeComparator {

    @Override
    public boolean inThisHand(Hand hand) {
        return true;
    }
}
