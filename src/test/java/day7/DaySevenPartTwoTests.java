package day7;

import day7.parttwo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static day7.DaySeven.partTwo;
import static day7.HandHelper.getBidByFigures;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

public class DaySevenPartTwoTests {

    @Test
    void testPartTwoSample() {
        assertThat(partTwo("sample7")).isEqualTo(5905);
    }

    @Test
    void testPartTwo() {
        assertThat(partTwo("in7")).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource({
            "fiveOfAKindProvider",
            "fourOfAKindProvider",
            "threeOfAKindProvider",
            "onePairProvider",
            "fullHouseProvider",
            "twoPairsProvider"
    })
    void testCheckers(String hand, boolean answer, HandTypeComparator checker) {
        var bid = getBidByFigures(hand);

        assertThat(checker.inThisHand(bid.hand()))
                .describedAs("Hand '%s' should %s be %s", hand, answer ? "" : "not", checker.getClass().toString())
                .isEqualTo(answer);
    }

    public static Stream<Arguments> fiveOfAKindProvider() {
        return Stream.of(
                of("AAAAA", true, fiveOfAKind()),
                of("JJJJJ", true, fiveOfAKind()),
                of("JJJJ2", true, fiveOfAKind()),
                of("JJJ22", true, fiveOfAKind()),
                of("JJ222", true, fiveOfAKind()),
                of("J2222", true, fiveOfAKind()),

                of("J2333", false, fiveOfAKind()),
                of("JJ233", false, fiveOfAKind()),
                of("JJJ23", false, fiveOfAKind()),
                of("JJ234", false, fiveOfAKind()),
                of("JJ234", false, fiveOfAKind())
        );
    }

    public static Stream<Arguments> fourOfAKindProvider() {
        return Stream.of(
                of("AAAA2", true, fourOfAKind()),
                of("AAAJ2", true, fourOfAKind()),
                of("AAAJJ", true, fourOfAKind()),
                of("JJ232", true, fourOfAKind()),

                of("23455", false, fourOfAKind()),
                of("AA2J3", false, fourOfAKind()),
                of("JJ234", false, fourOfAKind())
        );
    }

    public static Stream<Arguments> threeOfAKindProvider() {
        return Stream.of(
                of("AAA34", true, threeOfAKind()),
                of("JJJ34", true, threeOfAKind()),
                of("JJ324", true, threeOfAKind()),
                of("J3324", true, threeOfAKind()),
                of("J3334", true, threeOfAKind()),

                of("AA234", false, threeOfAKind()),
                of("J2345", false, threeOfAKind()),
                of("22345", false, threeOfAKind())
        );
    }

    public static Stream<Arguments> onePairProvider() {
        return Stream.of(
                of("AA234", true, onePair()),
                of("JA234", true, onePair()),
                of("JJ234", true, onePair()),
                of("23456", false, onePair())

        );
    }

    public static Stream<Arguments> fullHouseProvider() {
        return Stream.of(
                of("AAQQQ", true, fullHouse()),
                of("AAQQJ", true, fullHouse()),
                of("AAQJJ", true, fullHouse()),
                of("AAJJJ", true, fullHouse()),
                of("2JJJ3", true, fullHouse()),
                of("AJJJJ", true, fullHouse()),
                of("JJJJJ", true, fullHouse()),
                of("QQQJA", true, fullHouse()),
                of("QQQJJ", true, fullHouse()),
                of("QQQ23", false, fullHouse()),
                of("QQK22", false, fullHouse()),
                of("JQQ23", false, fullHouse()),
                of("JJQ23", false, fullHouse())
        );
    }

    public static Stream<Arguments> twoPairsProvider() {
        return Stream.of(
                of("AAQQ2", true, twoPairs()),
                of("AAQJ2", true, twoPairs()),
                of("AAJJ2", true, twoPairs()),
                of("AKJJ2", true, twoPairs()),
                of("AJJJ2", true, twoPairs()),
                of("JJJJ2", true, twoPairs()),

                of("23456", false, twoPairs()),
                of("22345", false, twoPairs()),
                of("J2345", false, twoPairs())
        );
    }

    private static JokerFiveOfAKindComparator fiveOfAKind() {
        return new JokerFiveOfAKindComparator();
    }

    private static JokerFourOfAKindComparator fourOfAKind() {
        return new JokerFourOfAKindComparator();
    }

    private static JokerThreeOfAKindComparator threeOfAKind() {
        return new JokerThreeOfAKindComparator();
    }

    private static JokerOnePairComparator onePair() {
        return new JokerOnePairComparator();
    }

    private static JokerFullHouseComparator fullHouse() {
        return new JokerFullHouseComparator();
    }

    private static JokerTwoPairComparator twoPairs() {
        return new JokerTwoPairComparator();
    }


}
