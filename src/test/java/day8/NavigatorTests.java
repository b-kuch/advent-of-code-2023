package day8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.stream.Stream;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.*;

public class NavigatorTests {


    @ParameterizedTest
    @MethodSource("navigatorTestArgs")
    void testNavigator(int answer, String directions, Map<String, Direction> map) {
        var navigator = new Navigator(directions, map);
        var steps = 0;
        do {
            steps++;
        } while (!navigator.next().equals("ZZZ"));

        assertThat(steps).isEqualTo(answer);
    }

    public static Stream<Arguments> navigatorTestArgs() {
        return Stream.of(
                of(6, "LLR", map(
                        node("AAA", "BBB", "BBB"),
                        node("BBB", "AAA", "ZZZ"),
                        node("ZZZ", "ZZZ", "ZZZ")
                ))
        );
    }

    private static Map<String, Direction> map(Node... nodes) {
        var result = new HashMap<String, Direction>();
        for (var node :
                nodes) {
            result.put(node.room(), node.directions());
        }
        return result;
    }

    private static Node node(String room, String left, String right) {
        return new Node(room, new Direction(left, right));
    }
}
