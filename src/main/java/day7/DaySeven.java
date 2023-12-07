package day7;

import util.Files;

public class DaySeven {
    static int partOne(String fileName) {
        var bids = Files.readFileByLines(fileName, DaySeven::parseBid)
                .stream()
                .sorted(new ByCardComparator());
        return 0;
    }

    private static Bid parseBid(String bid) {
        var splitBid = bid.split(" ");
        return new Bid(splitBid[0], Integer.parseInt(splitBid[1]));
    }
}
