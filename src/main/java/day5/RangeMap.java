package day5;


import java.util.HashMap;
import java.util.Map;


public class RangeMap<T> {
    Map<Range, T> values;

    public RangeMap() {
        values = new HashMap<>();
    }

    void put(long start, long end, T value) {
        values.put(new Range(start, end), value);
    }

    public Entry<T> getEntry(long key) {
        for (var entry:
             values.entrySet()) {
            var range = entry.getKey();
            if (range.contains(key)) {
                return new Entry<>(entry);
            }
        }
        return null;
    }
}
