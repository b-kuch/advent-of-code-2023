package day10;

import java.util.*;

import static day10.WalkThrough.walkThrough;

public class PartTwo {
    private final List<String> data;

    public PartTwo(List<String> data) {
        this.data = data;
    }

    public static int solve(List<String> data) {
        var solver = new PartTwo(data);
        return solver.solve();
    }

    private Set<Position> getPipeLoop() {
        var result = new HashSet<Position>();
        var pipeMap = new PipeMap(data);
        for (var position :
                walkThrough(pipeMap, pipeMap.getStart())) {
            if (!result.add(position))
                return result;

        }
        return result;
    }


    enum Belonging {
        OUTSIDE,
        INSIDE,
        BORDER
    }
    public int solve() {

        var borderLoop = getPipeLoop();

        int area = 0;

        for (int ver = 0; ver<data.size(); ver++) {
            var previousIs = Belonging.OUTSIDE;
            var inside = false;
            var row = data.get(ver);
            for (int hor = 0; hor<row.length(); hor++) {
                // TODO handle --- border and || border
                inside = previousIs.equals(Belonging.BORDER) | previousIs.equals(Belonging.INSIDE);
                var current = new Position(ver, hor);
                previousIs =
                        borderLoop.contains(current) ?
                                Belonging.BORDER : inside ?
                                Belonging.INSIDE : Belonging.OUTSIDE;

                if (previousIs.equals(Belonging.INSIDE)) {
                    area++;
                }
            }
        }
        return area;
    }

    private Iterable<Character> getRowIterable(String row) {
        return () -> new CharacterIterator(row);
    }

    static class CharacterIterator implements Iterator<Character> {

        private final String str;
        private int pos = 0;

        public CharacterIterator(String str) {
            this.str = str;
        }

        public boolean hasNext() {
            return pos < str.length();
        }

        public Character next() {
            return str.charAt(pos++);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
