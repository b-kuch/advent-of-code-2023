package day7;

import org.junit.jupiter.api.Test;

import static day7.DaySeven.partOne;
import static org.assertj.core.api.Assertions.assertThat;

public class DaySevenPartOneTests {
    @Test
    void testSample() {
        assertThat(partOne("sample7")).isEqualTo(6440);
    }
    @Test
    void testPartOne() {
        assertThat(partOne("in7")).isEqualTo(252052080L);
    }
}
