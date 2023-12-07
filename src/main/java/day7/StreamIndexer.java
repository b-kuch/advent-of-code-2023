package day7;

import java.util.function.Function;

public class StreamIndexer implements Function<String, Integer> {

    private final Function<Integer, Integer> lambda;
    private int count = 0;


    StreamIndexer(Function<Integer, Integer> xxx) {
        this.lambda = xxx;
    }

    StreamIndexer() {
        this(Function.identity());
    }

    @Override
    public Integer apply(String t) {
        return lambda.apply(count++);
    }
}
