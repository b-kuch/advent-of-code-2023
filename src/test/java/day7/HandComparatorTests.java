package day7;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandComparatorTests {
    ByCardComparator byCardComparator = ByCardComparator.withPartOneRules();

    @Test
    void testHighHandComparator() {
        var ace = HandHelper.getBidByFigures("A2345");
        var king = HandHelper.getBidByFigures("KQJT9");

        assertAreEqual(ace, ace);
        assertAreEqual(king, king);
        assertFirstIsBigger(ace, king);
        assertFirstIsSmaller(king, ace);
    }

    @Test
    void testOnePairComparatorWithDifferentPair() {
        var aPair = HandHelper.getBidByFigures("22345");
        var otherPair = HandHelper.getBidByFigures("22A34");

        assertAreEqual(otherPair, otherPair);
        assertFirstIsBigger(otherPair, aPair);
        assertFirstIsSmaller(aPair, otherPair);
    }
    @Test
    void testOnePairComparator() {
        var aPair = HandHelper.getBidByFigures("22345");
        var ace = HandHelper.getBidByFigures("A2345");

        assertAreEqual(aPair, aPair);
        assertFirstIsBigger(aPair, ace);
        assertFirstIsSmaller(ace, aPair);
    }

    @Test
    void testTwoPairsComparator() {
        var twoPairs = HandHelper.getBidByFigures("22334");
        var aPair = HandHelper.getBidByFigures("22345");

        assertAreEqual(twoPairs, twoPairs);
        assertFirstIsBigger(twoPairs, aPair);
        assertFirstIsSmaller(aPair, twoPairs);
    }

    @Test
    void testThreeOfAKindComparator() {
        var threeOfAKind = HandHelper.getBidByFigures("22234");
        var twoPairs = HandHelper.getBidByFigures("AAKKQ");

        assertAreEqual(threeOfAKind, threeOfAKind);
        assertFirstIsBigger(threeOfAKind, twoPairs);
        assertFirstIsSmaller(twoPairs, threeOfAKind);
    }

    @Test
    void testFullHouseComparator() {
        var fullHouse = HandHelper.getBidByFigures("22233");
        var threeOfAKind = HandHelper.getBidByFigures("AAAKQ");

        assertAreEqual(fullHouse, fullHouse);
        assertFirstIsBigger(fullHouse, threeOfAKind);
        assertFirstIsSmaller(threeOfAKind, fullHouse);
    }

    @Test
    void testFourOfAKindComparator() {
        var fourOfAKind = HandHelper.getBidByFigures("22223");
        var fullHouse = HandHelper.getBidByFigures("AAAKK");

        assertAreEqual(fullHouse, fullHouse);
        assertFirstIsBigger(fourOfAKind, fullHouse);
        assertFirstIsSmaller(fullHouse, fourOfAKind);
    }

    @Test
    void testFourOfAKindComparatorWithSameHandType() {
        var strongerFourOfAKind = HandHelper.getBidByFigures("22224");
        var fourOfAKind = HandHelper.getBidByFigures("22223");

        assertAreEqual(strongerFourOfAKind, strongerFourOfAKind);
        assertFirstIsBigger(strongerFourOfAKind, fourOfAKind);
        assertFirstIsSmaller(fourOfAKind, strongerFourOfAKind);
    }

    @Test
    void testFiveOfAKindComparator() {
        var fiveOfAKind = HandHelper.getBidByFigures("22222");
        var fourOfAKind = HandHelper.getBidByFigures("AAAAK");

        assertAreEqual(fiveOfAKind, fiveOfAKind);
        assertFirstIsBigger(fiveOfAKind, fourOfAKind);
        assertFirstIsSmaller(fourOfAKind, fiveOfAKind);
    }

    @Test
    void testTwoPairsFromSample() {
        var hand1 = HandHelper.getBidByFigures("KK677");
        var hand2 = HandHelper.getBidByFigures("KTJJT");

        assertFirstIsBigger(hand1, hand2);
    }

    @Test
    void testFourOfAKindBug() {
        var hand1 = HandHelper.getBidByFigures("QQQQ4");
        var hand2 = HandHelper.getBidByFigures("7777A");

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
}
