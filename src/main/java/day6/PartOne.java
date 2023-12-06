package day6;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

import static day6.WinningCars.winningCars;
import static util.Files.readFileByLines;

public class PartOne {

    static Race[] parseRaces(String fileName) {
        var data = readFileByLines(fileName, Function.identity());

        var times = data.get(0).split(":\\s+")[1].split("\s+");
        var distances = data.get(1).split(":\\s+")[1].split("\s+");

        var races = new Race[times.length];
        for (int i = 0; i < times.length; i++) {
            races[i] = new Race(Long.parseLong(times[i]), Long.parseLong(distances[i]));
        }
        return races;
    }

    static long partOne(String fileName) {
        var races = parseRaces(fileName);
        AtomicLong waysToWin = new AtomicLong(1);

        Arrays.stream(races).map(race -> winningCars(race).numberOfWinningRaces())
                .forEach(integer -> waysToWin.updateAndGet(v -> v * integer));

        return waysToWin.get();
    }
}
