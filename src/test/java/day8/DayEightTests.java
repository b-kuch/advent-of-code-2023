package day8;

import org.junit.jupiter.api.Test;

import static day8.DayEight.partOne;
import static day8.DayEight.partTwo;
import static org.assertj.core.api.Assertions.assertThat;

public class DayEightTests {
    @Test
    void testSampleOne() {
        assertThat(partOne("sample8")).isEqualTo(6);
    }
    @Test
    void testPartOne() {
        assertThat(partOne("in8")).isEqualTo(0);
    }
    @Test
    void testSampleTwo() {
        assertThat(partTwo("sample8.2")).isEqualTo(6L);
    }
    @Test
    void testPartTwo() {
        assertThat(partTwo("in8")).isEqualTo(0L);
    }
}
