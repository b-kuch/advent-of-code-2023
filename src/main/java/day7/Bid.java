package day7;

public record Bid(Hand hand, int value) {
    Bid(String hand, int value) {
        this(new Hand(hand), value);
    }
}
