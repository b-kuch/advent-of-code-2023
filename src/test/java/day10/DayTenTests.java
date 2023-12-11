package day10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static day10.DayTen.partOne;
import static day10.DayTen.partTwo;
import static org.assertj.core.api.Assertions.assertThat;

public class DayTenTests {

    public static Stream<Arguments> fileSamples() {
        return Stream.of(
                Arguments.of("sample10.3", 4),
                Arguments.of("sample10.4", 4),
                Arguments.of("sample10.5", 8),
                Arguments.of("sample10.6", 10)
        );
    }

    @Test
    void testPartOneSampleOne() {
        assertThat(partOne("sample10.1")).isEqualTo(4);
    }

    @Test
    void testPartOneSampleTwo() {
        assertThat(partOne("sample10.2")).isEqualTo(8);
    }

    @ParameterizedTest
    @MethodSource("fileSamples")
    void testPartTwoSamples(String sampleName, int expected) {
        assertThat(partTwo(sampleName))
                .describedAs("Sample %s", sampleName)
                .isEqualTo(expected);
    }

    @Test
    void testPartOne() {
        assertThat(partOne("in10")).isEqualTo(6897);
    }

}
