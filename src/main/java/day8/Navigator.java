package day8;

import java.util.Map;

public class Navigator {
    protected final String directions;
    protected final Map<String, Direction> map;
    protected int nextDirectionIndex;
    protected String currentRoom;

    public Navigator(String directions, Map<String, Direction> map) {

        this.directions = directions;
        this.map = map;
        this.currentRoom = "AAA";
        this.nextDirectionIndex = 0;
    }

    public String next() {
        return currentRoom = go(map.get(currentRoom));
    }

    private String go(Direction direction) {
        if (goLeft()) {
            return direction.left();
        } else {
            return direction.right();
        }
    }

    private boolean goLeft() {
        var direction = directions.charAt(nextDirectionIndex) == 'L';
        nextDirectionIndex = (nextDirectionIndex + 1)  % directions.length();
        return direction;
    }
}
