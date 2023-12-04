package day4;

import util.Files;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class Four {
    public static void main(String[] args) {
        System.out.print(partOne());
    }

    static int partOne() {
        var cards = loadCards();
        return sum(cards.stream().map(Four::getPrize).toList());
    }

    private static int sum(List<Integer> numbers) {
        var accumulator = 0;
        for (Integer number :
            numbers) {
            accumulator += number;
        }
        return accumulator;
    }

    static List<Card> loadCards() {
        return Files.readFile("in4", Four::mapCard);
    }

    private static final Pattern idPattern = Pattern.compile("\\d+(?=:)");
    private static final Pattern winningNumbersPattern = Pattern.compile("(?<=:).*(?=\\|)");
    private static final Pattern playerNumbersPattern = Pattern.compile("(?<=\\| ).*");

    private static String find(Pattern pattern, String input) {
        var match = pattern.matcher(input);
        if (match.find()) {
            return match.group();
        }
        return "";
    }
    static Card mapCard(String card) {
        var cardId = find(idPattern, card);
        var winningNumbers = Arrays.stream(find(winningNumbersPattern,card).split("\\s+"))
            .filter(s -> !s.isBlank())
            .map(Integer::valueOf).toList();
        var playerNumbers = Arrays.stream(find(playerNumbersPattern, card).split("\\s+"))
            .filter(s -> !s.isBlank())
            .map(Integer::valueOf).toList();
        return new Card(Integer.parseInt(cardId), winningNumbers, playerNumbers);
    }

    static int getPrize(Card card) {
        var playerWins = getPlayerWins(card);
        return 1 << playerWins - 1;
    }

    static int getPlayerWins(Card card) {
        return (int) card.playerNumbers().stream().filter(num -> card.winningNumbers().contains(num)).count();
    }
}
