package day7.parttwo;

import day7.Hand;

import java.util.Map;


public class JokerHelper {
    static boolean getBestJokerHand(Hand hand, int requiredReps) {
        var jokers = hand.figureCount().getOrDefault("J", 0);
        var biggestExceptJoker = getBiggestExceptJoker(hand);

        return jokers + biggestExceptJoker >= requiredReps;
    }

    static Integer getBiggestExceptJoker(Hand hand) {
        return hand.figureCount().entrySet().stream()
                .filter(e -> !e.getKey().equals("J"))
                .map(Map.Entry::getValue)
                .max(Integer::compareTo)
                .orElse(0);
    }
}
