package day10;

import util.Files;

import java.util.function.Function;

public class DayTen {

    static int partOne(String fileName) {
        var data = Files.readFileByLines(fileName, Function.identity());

        return PartOne.solve(data);
    }


}
