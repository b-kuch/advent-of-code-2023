package day7;

public class FullHouseComparator implements HandTypeComparator {
    @Override
    public boolean inThisHand(Hand hand) {
        var figures = hand.figureCount().values();
        return figures.contains(3) && figures.contains(2);
    }
}
