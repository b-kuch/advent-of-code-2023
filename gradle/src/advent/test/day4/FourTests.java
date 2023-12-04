package four;

import org.junit.jupiter.api.Test;

import java.util.List;

import static four.Four.*;

public class FourTests {
    @Test
    void testGetPrize() {
        var card = new Card(1, List.of(41, 48, 83, 86, 17), List.of(83, 86, 6, 31, 17, 9, 48, 53));
        Assert.that(getPrize(card)).equalTo(8);
    }

    @Test
    void testGetPlayerWins() {
        var card = new Card(1, List.of(41, 48, 83, 86, 17), List.of(83, 86, 6, 31, 17, 9, 48, 53));
        Assert.that(getPlayerWins(card)).equalTo(4L);
    }

    @Test
    void testPartOne() {
        Assert.that(partOne()).equalTo(0);
    }

    static class Assert {
        private final Object actual;

        private Assert(Object actual) {
            this.actual = actual;
        }

        static Assert that(Object actual) {
            return new Assert(actual);
        }

        void equalTo(Object expected) {
            if (!expected.equals(actual)) {
                throw new AssertionError("Expected: %s, Actual: %s".formatted(expected, actual));
            }
        }
    }
}
