package day10;

import java.util.List;

public record PipeMap(List<String> data) {
    boolean isStart(Position position) {
        return data().get(position.vertical()).charAt(position.horizontal()) == 'S';
    }

    Position getStart() {
        var foundVertical = 0;
        for (var row :
                data()) {
            var foundHorizontal = row.indexOf("S");
            if (foundHorizontal >= 0) {
                return new Position(foundVertical, foundHorizontal);
            }
            foundVertical++;
        }
        throw new IllegalArgumentException("Start not found");
    }
    private char getPipeType(Position pos) {
        return data.get(pos.vertical()).charAt(pos.horizontal());
    }
    Pipe getPipe(Position pos) {
        return new Pipe(getPipeType(pos), pos);
    }
}
