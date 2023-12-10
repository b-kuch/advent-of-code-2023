package day10;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class PartOne {
    private final List<String> data;
    private final Position start;

    public static int solve(List<String> data) {
        var solver = new PartOne(data);
        return solver.solve();
    }

    public PartOne(List<String> data) {
        this.data = data;
        this.start = getStart(data);
    }

    public int solve() {
        var length = 0;

        for (var position :
                walkThrough(data, start)) {
            if (isStart(position) && length != 0) {
                return Math.floorDiv(length+1, 2);
            }
            length++;
        }
        throw new IllegalArgumentException("Start is not in a loop, length is: %d".formatted(
                length));
    }

    private boolean isStart(Position position) {
        return data.get(position.vertical()).charAt(position.horizontal()) == 'S';
    }

    private Position getStart(List<String> data) {
        var foundVertical = 0;
        for (var row :
                data) {
            var foundHorizontal = row.indexOf("S");
            if (foundHorizontal >= 0) {
                return new Position(foundVertical, foundHorizontal);
            }
            foundVertical++;
        }
        throw new IllegalArgumentException("Start not found");
    }

    private static Iterable<Position> walkThrough(List<String> data, Position start) {
        return () -> new WalkThrough(data, start);
    }

    static class WalkThrough implements Iterator<Position> {

        private final List<String> data;
        private final Position start;
        private Position previous;
        private Position current;

        WalkThrough(List<String> data, Position start) {
            this.data = data;
            this.start = start;
            this.current = start;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Position next() {
            var next = getNext();
//            if (next.equals(start))
//                throw new NoSuchElementException("Returned to start");
            previous = current;
            current = next;
            return current;
        }

        private Position getNext() {
            return switch (getCurrentPipe()) {
                case 'S' -> handleStart();
                case 'F' -> handleF();
                case 'J' -> handleJ();
                case '7' -> handle7();
                case 'L' -> handleL();
                case '|' -> handleVertical();
                case '-' -> handleHorizontal();
                default -> throw new IllegalArgumentException(
                        "Current symbol is not handled: %c".formatted(getCurrentPipe()));
            };
        }

        private Position handleHorizontal() {
            if (previous.equals(current.goRight()))
                return current.goLeft();
            else
                return current.goRight();
        }

        private Position handleVertical() {
            if (previous.equals(current.goUp()))
                return current.goDown();
            else
                return current.goUp();
        }

        record Pipe(char type, Position position) {
        }
        private Position handleF() {
            if (previous.equals(current.goRight()))
                return current.goDown();
            else
                return current.goRight();
            }

        private Position handleJ() {
            if (previous.equals(current.goLeft()))
                return current.goUp();
            else
                return current.goLeft();
        }

        private Position handle7() {
            if (previous.equals(current.goLeft()))
                return current.goDown();
            else
                return current.goLeft();
        }

        private Position handleL() {
            if (previous.equals(current.goRight()))
                return current.goUp();
            else
                return current.goRight();
        }

        private Position handleStart() {
            return Stream.of(
                            new Pipe('7', current.goUp()),
                            new Pipe('F', current.goUp()),
                            new Pipe('|', current.goUp()),

                            new Pipe('J', current.goRight()),
                            new Pipe('7', current.goRight()),
                            new Pipe('-', current.goRight()),

                            new Pipe('J', current.goDown()),
                            new Pipe('L', current.goDown()),
                            new Pipe('|', current.goDown()),

                            new Pipe('F', current.goLeft()),
                            new Pipe('L', current.goLeft()),
                            new Pipe('-', current.goLeft()))
                    .filter(candidate -> candidate.type() == getPipeType(candidate.position()))
                    .map(Pipe::position)
                    .findFirst().get();
        }

        private char getPipeType(Position pos) {
            return data.get(pos.vertical()).charAt(pos.horizontal());
        }

        private char getCurrentPipe() {
            return getPipeType(current);
        }

    }
}