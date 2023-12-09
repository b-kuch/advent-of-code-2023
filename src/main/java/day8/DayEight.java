package day8;

import util.Files;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class DayEight {
    private final Map<String, Direction> navigationMap;
    private final String directions;
    public DayEight(List<String> data) {
        directions = data.get(0);
        navigationMap = new HashMap<>();
        data.stream().skip(2)
                .forEach(this::parseRoom);
    }

    static int partOne(String fileName) {
        var data = Files.readFileByLines(fileName, Function.identity());
        return new DayEight(data).solvePartOne();
    }

    private int solvePartOne() {
        var navigator = new Navigator(directions, navigationMap);

        var steps = 0;
        do {
            steps++;
        } while (!navigator.next().equals("ZZZ"));
        return steps;
    }

    static long partTwo(String fileName) {
        var data = Files.readFileByLines(fileName, Function.identity());
        return new DayEight(data).solvePartTwo();
    }

    private long solvePartTwo() {
        var navigators = navigationMap.keySet().stream()
                .filter(room -> room.charAt(2) =='A')
                .map(startRoom -> new GhostNavigator(directions, navigationMap, startRoom))
                .toList();

        var steps = 0L;
        do {
            steps++;
        } while (navigators.stream().map(GhostNavigator::next).anyMatch(this::isNotExitRoom));
        return steps;
    }

    private boolean isNotExitRoom(String room) {
        return room.charAt(2) != 'Z';
    }

    private static final Pattern numberRegex = Pattern.compile("\\w+");
    private void parseRoom(String input) {
        var matcher = numberRegex.matcher(input)
                .results().limit(3)
                .map(MatchResult::group)
                .toArray(String[]::new);
        navigationMap.put(matcher[0], new Direction(matcher[1], matcher[2]));
    }
}
