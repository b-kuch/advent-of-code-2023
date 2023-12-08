package day7;


public abstract class OfAKindComparator implements HandTypeComparator {
    @Override
    public boolean inThisHand(Hand hand) {
        return hand.figureCount().entrySet().stream().filter(card -> card.getValue() == getKind()).count() == 1;
    }
    protected abstract int getKind();
}
