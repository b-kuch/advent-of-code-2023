package day7;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HighHandComparatorTest {
    HighHandComparator highHandComparator = new HighHandComparator();
    @Test
    void test() {
        var ace = ByCardComparator.handByFigures("A");
        var king = ByCardComparator.handByFigures("K");

        assertThat(ace).usingComparator(highHandComparator).is
    }
}