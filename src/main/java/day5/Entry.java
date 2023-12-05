package day5;


import java.util.Map;

public record Entry<T>(Range keyRange, T value) {
    public Entry(Map.Entry<Range, T> entry) {
        this(entry.getKey(), entry.getValue());
    }
}