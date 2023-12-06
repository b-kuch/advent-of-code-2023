package day6;

public class Car {
    private final long time;

    public Car(long time) {
        this.time = time;
    }

    public static Car pumpingCarFor(long time) {
        return new Car(time);
    }

    public boolean canBeatRace(Race race) {
        return maxTravelDistance(race.record() - time) > race.distance() && time < race.record();
    }

    private long maxTravelDistance(long time) {
        var speed = this.time;
        return speed*time;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Car otherCar)
            return this.time == otherCar.time;
        return false;
    }

    long getTime() {
        return time;
    }
}
