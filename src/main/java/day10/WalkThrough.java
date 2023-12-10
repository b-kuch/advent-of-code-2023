package day10;

import java.util.Iterator;
import java.util.stream.Stream;

class WalkThrough implements Iterator<Position> {

    private final PipeMap data;
    private final Position start;
    private Position previous;
    private Position current;

    static Iterable<Position> walkThrough(PipeMap data, Position start) {
        return () -> new WalkThrough(data, start);
    }

    WalkThrough(PipeMap data, Position start) {
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
                .filter(candidate -> candidate.type() == getPipe(candidate.position()).type())
                .map(Pipe::position)
                .findFirst().get();
    }

    private Pipe getPipe(Position pos) {
        return data.getPipe(pos);
    }

    private char getCurrentPipe() {
        return getPipe(current).type();
    }

}
