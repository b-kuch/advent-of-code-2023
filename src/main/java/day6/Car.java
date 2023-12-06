package day6;

public class Car {
    private final int time;

    public Car(int time) {
        this.time = time;
    }

    public static Car pumpingCarFor(int time) {
        return new Car(time);
    }

    public boolean canBeatRace(Race race) {
        return maxTravelDistance(race.record() - time) > race.distance();
    }

    private int maxTravelDistance(int time) {
        var speed = this.time;
        return speed*time;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Car otherCar)
            return this.time == otherCar.time;
        return false;
    }

    int getTime() {
        return time;
    }
}
