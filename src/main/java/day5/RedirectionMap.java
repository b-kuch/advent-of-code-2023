package day5;

public class RedirectionMap {
    private final RangeMap<Long> rangeMap;

    public RedirectionMap() {
        rangeMap = new RangeMap<>();
    }

    public long redirect(long key) {
        var range = rangeMap.getEntry(key);
        if (range == null) {
            return key;
        }
        var destinationOffset = key - range.keyRange().start();
        return range.value() + destinationOffset;
    }

    public void add(Redirection redirection) {
        rangeMap.put(redirection.sourceStart(), redirection.sourceStart() + redirection.length() - 1, redirection.destinationStart());
    }
}
