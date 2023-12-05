package day5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import java.util.List;

import static day5.DayFive.solvePartTwo;
import static org.assertj.core.api.Assertions.assertThat;

class DayFiveTest {
    @Test
    void testRangeMaker() {
        var redirects = new RedirectionMap();
        redirects.add(new Redirection(50, 98, 2));
        assertThat(redirects.redirect(99)).isEqualTo(51);
        assertThat(redirects.redirect(97)).isEqualTo(97);
    }

    @Test
    void testRangeMap() {
        var rangeMap = new RangeMap<Long>();
        rangeMap.put(2, 4, 10L);

        assertThat(rangeMap.getEntry(2)).isEqualTo(new Entry<>(new Range(2, 4), 10L));
        assertThat(rangeMap.getEntry(3)).isEqualTo(new Entry<>(new Range(2, 4), 10L));
        assertThat(rangeMap.getEntry(4)).isEqualTo(new Entry<>(new Range(2, 4), 10L));
        assertThat(rangeMap.getEntry(6)).isNull();
    }
    @Test
    void testSampleLoad() {
        DayFive.Almanac sections = DayFive.loadSections("sample5");
        assertThat(sections.seeds()).isEqualTo(List.of(79L, 14L, 55L, 13L));
        assertThat(sections.sections().get(0).getName()).isEqualTo("seed-to-soil map");
        assertThat(sections.sections().get(1).getName()).isEqualTo("soil-to-fertilizer map");
        assertThat(sections.sections().get(2).getName()).isEqualTo("fertilizer-to-water map");
        assertThat(sections.sections().get(3).getName()).isEqualTo("water-to-light map");
        assertThat(sections.sections().get(4).getName()).isEqualTo("light-to-temperature map");
        assertThat(sections.sections().get(5).getName()).isEqualTo("temperature-to-humidity map");
        assertThat(sections.sections().get(6).getName()).isEqualTo("humidity-to-location map");
    }

    @Test
    void testSample() {
        assertThat(DayFive.solvePartOne("sample5")).isEqualTo(35);
    }
    @Test
    void testPartOne() {
        assertThat(DayFive.solvePartOne("in5")).isEqualTo(662197086);
    }

    @Test
    void solveSampleForPartTwo() {
        assertThat(solvePartTwo("sample5")).isEqualTo(46);
    }

    @Disabled("SLOOOOOOW")
    @Test
    void testPartTwo() {
        assertThat(solvePartTwo("in5")).isEqualTo(52510809);
    }
}