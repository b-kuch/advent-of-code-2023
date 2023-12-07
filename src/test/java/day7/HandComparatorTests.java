package day7;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandComparatorTests {
    ByCardComparator byCardComparator = new ByCardComparator();

    @Test
    void testHighHandComparator() {
        var ace = getBidByFigures("A2345");
        var king = getBidByFigures("KQJT9");

        assertAreEqual(ace, ace);
        assertAreEqual(king, king);
        assertFirstIsBigger(ace, king);
        assertFirstIsSmaller(king, ace);
    }

    @Test
    void testOnePairComparatorWithDifferentPair() {
        var aPair = getBidByFigures("22345");
        var otherPair = getBidByFigures("22A34");

        assertAreEqual(otherPair, otherPair);
        assertFirstIsBigger(otherPair, aPair);
        assertFirstIsSmaller(aPair, otherPair);
    }
    @Test
    void testOnePairComparator() {
        var aPair = getBidByFigures("22345");
        var ace = getBidByFigures("A2345");

        assertAreEqual(aPair, aPair);
        assertFirstIsBigger(aPair, ace);
        assertFirstIsSmaller(ace, aPair);
    }

    @Test
    void testTwoPairsComparator() {
        var twoPairs = getBidByFigures("22334");
        var aPair = getBidByFigures("22345");

        assertAreEqual(twoPairs, twoPairs);
        assertFirstIsBigger(twoPairs, aPair);
        assertFirstIsSmaller(aPair, twoPairs);
    }

    @Test
    void testThreeOfAKindComparator() {
        var threeOfAKind = getBidByFigures("22234");
        var twoPairs = getBidByFigures("AAKKQ");

        assertAreEqual(threeOfAKind, threeOfAKind);
        assertFirstIsBigger(threeOfAKind, twoPairs);
        assertFirstIsSmaller(twoPairs, threeOfAKind);
    }

    @Test
    void testFullHouseComparator() {
        var fullHouse = getBidByFigures("22233");
        var threeOfAKind = getBidByFigures("AAAKQ");

        assertAreEqual(fullHouse, fullHouse);
        assertFirstIsBigger(fullHouse, threeOfAKind);
        assertFirstIsSmaller(threeOfAKind, fullHouse);
    }

    @Test
    void testFourOfAKindComparator() {
        var fourOfAKind = getBidByFigures("22223");
        var fullHouse = getBidByFigures("AAAKK");

        assertAreEqual(fullHouse, fullHouse);
        assertFirstIsBigger(fourOfAKind, fullHouse);
        assertFirstIsSmaller(fullHouse, fourOfAKind);
    }

    @Test
    void testFourOfAKindComparatorWithSameHandType() {
        var strongerFourOfAKind = getBidByFigures("22224");
        var fourOfAKind = getBidByFigures("22223");

        assertAreEqual(strongerFourOfAKind, strongerFourOfAKind);
        assertFirstIsBigger(strongerFourOfAKind, fourOfAKind);
        assertFirstIsSmaller(fourOfAKind, strongerFourOfAKind);
    }

    @Test
    void testFiveOfAKindComparator() {
        var fiveOfAKind = getBidByFigures("22222");
        var fourOfAKind = getBidByFigures("AAAAK");

        assertAreEqual(fiveOfAKind, fiveOfAKind);
        assertFirstIsBigger(fiveOfAKind, fourOfAKind);
        assertFirstIsSmaller(fourOfAKind, fiveOfAKind);
    }

    @Test
    void testTwoPairsFromSample() {
        var hand1 = getBidByFigures("KK677");
        var hand2 = getBidByFigures("KTJJT");

        assertFirstIsBigger(hand1, hand2);
    }

    @Test
    void testFourOfAKindBug() {
        var hand1 = getBidByFigures("QQQQ4");
        var hand2 = getBidByFigures("7777A");

        assertFirstIsBigger(hand1, hand2);
    }

    private void assertAreEqual(Bid bid1, Bid bid2) {
        assertThat(byCardComparator.compare(bid1, bid2)).isZero();
    }

    private void assertFirstIsBigger(Bid bid1, Bid bid2) {
        assertThat(byCardComparator.compare(bid1, bid2)).isPositive();
    }

    private void assertFirstIsSmaller(Bid bid1, Bid bid2) {
        assertThat(byCardComparator.compare(bid1, bid2)).isNegative();
    }


    private static Bid getBidByFigures(String hand) {
        return ByCardComparator.bidByFigures(hand);
    }

}