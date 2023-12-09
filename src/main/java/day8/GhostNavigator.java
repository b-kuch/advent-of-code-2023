package day8;

import java.util.Map;

public class GhostNavigator extends Navigator {
    public GhostNavigator(String directions, Map<String, Direction> map, String startingRoom) {
        super(directions, map);
        this.currentRoom = startingRoom;
    }
}
