package day7;

import java.util.Comparator;
import java.util.HashMap;

public interface HandTypeComparator extends Comparator<HashMap<String, Integer>> {
    boolean inThisHand(HashMap<String, Integer> hand);

    int compare(HashMap<String, Integer> hand1, HashMap<String, Integer> hand2);
}
