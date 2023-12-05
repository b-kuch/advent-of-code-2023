package day5;

import java.util.Arrays;

public class Section {

    private final String name;
    private final RedirectionMap redirectionMap;

    public Section(String name, String redirections) {
        this.name = name;
        this.redirectionMap = new RedirectionMap();
        Arrays.stream(redirections.split("\\n"))
                .skip(1)
                .map(red -> red.split(" "))
                .map(red -> new Redirection(Long.parseLong(red[0]), Long.parseLong(red[1]), Long.parseLong(red[2])))
                .forEach(redirectionMap::add);
    }

    public String getName() {
        return name;
    }

    public long redirect(long key) {
        return redirectionMap.redirect(key);
    }
}
