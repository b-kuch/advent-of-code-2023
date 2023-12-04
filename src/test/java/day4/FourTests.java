package day4;

import org.junit.jupiter.api.Test;

import java.util.List;

import static day4.Four.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FourTests {
    @Test
    void testGetPrize() {
        var card = new Card(1, List.of(41, 48, 83, 86, 17), List.of(83, 86, 6, 31, 17, 9, 48, 53));
        assertThat(getPrize(card)).isEqualTo(8);
    }

    @Test
    void testGetPlayerWins() {
        var card = new Card(1, List.of(41, 48, 83, 86, 17), List.of(83, 86, 6, 31, 17, 9, 48, 53));
        assertThat(getPlayerWins(card)).isEqualTo(4L);
    }

    @Test
    void testPartOne() {
        assertThat(partOne()).isEqualTo(15268);
    }
}
