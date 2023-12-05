package day5;

public record Range(long start, long end) {
    public boolean contains(long key) {
        return key >= start && key <= end;
    }

    public Long size() {
        return  end-start;
    }
}
