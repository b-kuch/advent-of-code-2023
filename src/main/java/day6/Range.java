package day6;

public record Range(int start, int end) {
    public boolean contains(int key) {
        return key >= start && key <= end;
    }
}
