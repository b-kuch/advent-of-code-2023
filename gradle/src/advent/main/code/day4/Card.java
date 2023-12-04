package four;

import java.util.List;

public record Card(int id, List<Integer> winningNumbers, List<Integer> playerNumbers) {
}
