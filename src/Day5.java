import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day5 {
    public int silver() throws IOException {
        var content = Util.readFile("05", "input");
        var ranges = new ArrayList<ArrayList<Long>>();
        var ids = new ArrayList<Long>();
        var isRange = true;
        for (var line : content) {
            if (line.isBlank()) {
                isRange = false;
            }
            else if (isRange) {
                var range = line.split("-");
                ranges.add(new ArrayList<>(List.of(
                        Long.parseLong(range[0]),
                        Long.parseLong(range[1])
                )));
            }
            else ids.add(Long.parseLong(line));
        }
        var counter = 0;
        for (var id : ids) {
            for (var range : ranges) {
                if (id >= range.get(0) && id <= range.get(1)) {
                    counter++;
                    break;
                }
            }
        }
        return counter;
    }

    public Long gold() throws IOException {
        var content = Util.readFile("05", "input");
        var ranges = new ArrayList<ArrayList<Long>>();
        for (var line : content) {
            if (line.isBlank()) {
                break;
            }
                var range = line.split("-");
                ranges.add(new ArrayList<>(List.of(
                        Long.parseLong(range[0]),
                        Long.parseLong(range[1])
                )));
        }

        var thereAreOverlaps = true;
        while (thereAreOverlaps) {
            var i = 0;
            thereAreOverlaps = false;
            while (i < ranges.size()) {
                var j = i + 1;
                while (j < ranges.size()) {
                    if (ranges.get(i).get(0) <= ranges.get(j).get(1) && ranges.get(j).get(0) <= ranges.get(i).get(1)) {
                        thereAreOverlaps = true;
                        ranges.get(i).set(0, Math.min(ranges.get(i).get(0), ranges.get(j).get(0)));
                        ranges.get(i).set(1, Math.max(ranges.get(i).get(1), ranges.get(j).get(1)));
                        ranges.remove(j);
                    } else {
                        j++;
                    }
                }
                i++;
            }
        }
        return ranges.stream()
                .map(range -> range.get(1) - range.get(0) + 1)
                .reduce(Long::sum)
                .get();
    }
}
