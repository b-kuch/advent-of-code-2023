package day10;

import java.util.List;

import static day10.WalkThrough.walkThrough;

public class PartOne {
    private final PipeMap data;

    public static int solve(List<String> data) {
        var solver = new PartOne(data);
        return solver.solve();
    }

    public PartOne(List<String> data) {
        this.data = new PipeMap(data);
    }

    public int solve() {
        var length = 0;

        for (var position :
                walkThrough(data, data.getStart())) {
            if (data.isStart(position) && length != 0) {
                return Math.floorDiv(length + 1, 2);
            }
            length++;
        }
        throw new IllegalArgumentException("Start is not in a loop, length is: %d".formatted(
                length));
    }

}