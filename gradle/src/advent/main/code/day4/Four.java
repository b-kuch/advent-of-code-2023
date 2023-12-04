package four;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
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
        return readFile("C:\\dev\\advent\\four\\in4", Four::mapCard);
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

    private static <T> List<T>  readFile(String fileName, Function<String, T> mapper) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.lines().map(mapper).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int getPrize(Card card) {
        var playerWins = getPlayerWins(card);
        return 1 << playerWins - 1;
    }

    static int getPlayerWins(Card card) {
        return (int) card.playerNumbers().stream().filter(num -> card.winningNumbers().contains(num)).count();
    }
}
