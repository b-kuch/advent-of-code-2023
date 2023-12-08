package day7;

import util.Files;

public class DaySeven {
    static long partOne(String fileName) {
        var bids = Files.readFileByLines(fileName, DaySeven::parseBid)
                .stream()
                .sorted(ByCardComparator.withPartOneRules())
                .toList();

        var result = 0L;
        var i = 0;
        for (var bid :
                bids) {
            result += getBidResult(bid, ++i);
        }
        return result;
    }

    private static long getBidResult(Bid bid, int i) {
        return (long) bid.value() * i;
    }

    private static Bid parseBid(String bid) {
        var splitBid = bid.split(" ");
        return new Bid(splitBid[0], Integer.parseInt(splitBid[1]));
    }
}
