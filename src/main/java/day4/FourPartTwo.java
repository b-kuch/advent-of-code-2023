package day4;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;

import static day4.Four.getPlayerWins;
import static day4.Four.loadCards;

@Slf4j
public class FourPartTwo {

    static int partTwo() {
        var cards = loadCards();

        return play(cards);
    }
    static int play(List<Card> cards) {
        var extraCards = new HashMap<Integer, Integer>();
        var allCards = 0;
        for (var card :
            cards) {
            allCards += 1 + extraCards.getOrDefault(card.id(), 0);
            var reward = playOne(card);
            log.info("Card {} won cards {}-{}", card.id(), reward.start(), reward.end());
            for (int i = reward.start(); i <= reward.end(); i++) {
                extraCards.put(i, extraCards.getOrDefault(i, 0)+(extraCards.getOrDefault(card.id(), 0)+1));
            }
        }
        return allCards;
    }

    static Range playOne(Card card) {
        var matchingNumbers = getPlayerWins(card);
        return new Range(card.id() + 1, card.id() + matchingNumbers);
    }


}
