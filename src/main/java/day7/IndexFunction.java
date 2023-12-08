package day7;

import java.util.function.Function;

public class IndexFunction implements Function<String, Integer> {

    private final Function<Integer, Integer> lambda;
    private int count = 0;


    IndexFunction(Function<Integer, Integer> lambda) {
        this.lambda = lambda;
    }

    @Override
    public Integer apply(String t) {
        return lambda.apply(count++);
    }
}
