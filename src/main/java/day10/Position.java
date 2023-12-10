package day10;

public record Position(int vertical, int horizontal) {
    Position goLeft() {
        return new Position(vertical(), horizontal()-1);
    }
    Position goRight() {
        return new Position(vertical(), horizontal()+1);
    }
    Position goUp() {
        return new Position(vertical()-1, horizontal());
    }
    Position goDown() {
        return new Position(vertical()+1, horizontal());
    }
}
