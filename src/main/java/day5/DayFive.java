package day5;

import lombok.extern.slf4j.Slf4j;
import util.Files;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Slf4j
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
        return almanac.seeds().stream()
                .map(almanac::getLocation)
                .min(Comparator.naturalOrder()).get();
    }

    public static void main(String[] aaa) {
        System.out.println(solvePartTwo("in5"));
    }
    static long solvePartTwo(String fileName) {
        var almanac = loadSections(fileName);

        var seedRanges = getSeedRanges(almanac.seeds());
        log.info(String.valueOf(seedRanges.size()));

        var min = 99999999999L;
        long idx = 0;
        for (var seedRange :
            seedRanges) {
            log.info("Seed range {} size: {}", idx, seedRange.size());
            for (long seed = seedRange.start(); seed <= seedRange.end() ; seed++) {
                if (almanac.getLocation(seed) < min) {
                    min = almanac.getLocation(seed);
                }
            }
            idx++;
        }
        return min;
    }

    private static List<Range> getSeedRanges(List<Long> seedRanges) {
        var ranges = new ArrayList<Range>();
        for (int i = 0; i < seedRanges.size(); i+=2) {
            var frstSeed = seedRanges.get(i);
            var rangeLength = seedRanges.get(i+1);
            ranges.add(new Range(frstSeed, frstSeed + rangeLength - 1));
        }
        return ranges;
    }
}
