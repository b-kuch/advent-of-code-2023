package day5;

import util.Files;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DayFive {
    static Almanac loadSections(String fileName) {
        var data = Files.readFile(fileName);
        var sectionStrings = data.split("\\n\\n");
        var namedSections = Arrays.stream(sectionStrings).map(sec -> sec.split(":")).toList();

        var seedsString = namedSections.get(0)[1];
        var seeds = Arrays.stream(seedsString.split(" ")).skip(1).map(Long::parseLong).toList();

        var parsedSections = namedSections.stream().skip(1).map(sec -> new Section(sec[0], sec[1])).toList();
        return new Almanac(seeds, parsedSections);
    }

    record Almanac(List<Long> seeds, java.util.List<Section> sections) {
        public long getLocation(Long seedNumber) {
            for (var section:
                 sections) {
                seedNumber = section.redirect(seedNumber);
            }
            return seedNumber;
        }
    }

    static long solvePartOne(String fileName) {
        var almanac = loadSections(fileName);
        return almanac.seeds().stream().map(almanac::getLocation).min(Comparator.naturalOrder()).get();
    }
}
