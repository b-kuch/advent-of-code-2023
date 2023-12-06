package day6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.function.Function;

import static day6.PartOne.partOne;
import static day6.Car.pumpingCarFor;
import static day6.PartOne.parseRaces;
import static day6.WinningCars.winningCars;
import static org.assertj.core.api.Assertions.assertThat;
import static util.Files.readFileByLines;

public class DaySixTests {
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    void testPassingPumpingTimes(int pumpingTime) {
        var race = new Race(7, 9);

        var car = pumpingCarFor(pumpingTime);
        assertThat(car.canBeatRace(race)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 6, 7, 8})
    void testNonPassingPumpingTimes(int pumpingTime) {
        var race = new Race(7, 9);

        var car = pumpingCarFor(pumpingTime);
        assertThat(car.canBeatRace(race)).isFalse();
    }

    @Test
    void testMinimalAndMaximalPumpingTime() {
        var race = new Race(7, 9);

        var cars = winningCars(race);
        assertThat(cars.minPumpCar()).isEqualTo(new Car(2));
        assertThat(cars.maxPumpCar()).isEqualTo(new Car(5));
    }

    @Test
    void testNumberOfWinningRaces() {
        var race = new Race(7, 9);
        assertThat(winningCars(race).numberOfWinningRaces()).isEqualTo(4);
    }

    @Test
    void testParsingInput() {
        var races = parseRaces("sample6");

        assertThat(races[0]).isEqualTo(new Race(7, 9));
        assertThat(races[1]).isEqualTo(new Race(15, 40));
        assertThat(races[2]).isEqualTo(new Race(30, 200));
    }

    @Test
    void testPartOneSample() {
        assertThat(partOne("sample6")).isEqualTo(288);
    }

    @Test
    void testPartOne() {
        assertThat(partOne("in6")).isEqualTo(303600);
    }


}
