package day6;

import static day6.Car.pumpingCarFor;

public class WinningCars {

    private final Race race;

    public WinningCars(Race race) {
        this.race = race;
    }

    static WinningCars winningCars(Race race) {
        return new WinningCars(race);
    }

    public Car minPumpCar() {
        var min = 0;
        var max = race.record();

        int i = 0;
        while (max > min && i < 10) {
            var curr = (max + min)/2;
            var car = pumpingCarFor(curr);

            if (car.canBeatRace(race)) {
                max = curr;
            } else {
                min = curr;
            }
            i++;
        }

        return linearCarSearch(min, max, 1);
    }

    private Car linearCarSearch(int min, int max, int step) {
        for (int i = min; i <= max; i+=step) {
            var car = pumpingCarFor(i);
            if (car.canBeatRace(race))
                return car;
        }
        throw new RuntimeException("no car found in race %s".formatted(race));
    }

    public Car maxPumpCar() {
        var max = race.distance();
        var min = race.record();

        int i = 0;
        while (max > min && i < 12) {
            var curr = (max + min)/2;
            var car = pumpingCarFor(curr);

            if (car.canBeatRace(race)) {
                min = curr;
            } else {
                max = curr;
            }
            i++;
        }

        return linearCarSearch(max, min, -1);
    }

    public int numberOfWinningRaces() {
        var first = minPumpCar();
        var last = maxPumpCar();
        return last.getTime() - first.getTime() + 1;
    }
}
