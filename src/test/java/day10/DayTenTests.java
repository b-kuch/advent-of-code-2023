package day10;

import org.junit.jupiter.api.Test;

import static day10.DayTen.partOne;
import static org.assertj.core.api.Assertions.assertThat;

public class DayTenTests {

    @Test
    void testPartOneSampleOne() {
        assertThat(partOne("sample10.1")).isEqualTo(4);
    }

    @Test
    void testPartOneSampleTwo() {
        assertThat(partOne("sample10.2")).isEqualTo(8);
    }

    @Test
    void testPartOn() {
        assertThat(partOne("in10")).isEqualTo(0);
    }

}
