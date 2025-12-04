import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day2 {
    public Long silver() throws IOException {
        var content = Util.readFile("02", "input");
        var ranges = content.get(0).split(",");
        var sum = 0L;
        for (var range : ranges) {
            var rangeStart = Long.parseLong(range.split("-")[0]);
            var rangeEnd = Long.parseLong(range.split("-")[1]);
            for (var i = rangeStart; i < rangeEnd + 1; i++) {
                var number = String.valueOf(i);
                if (number.length() % 2 != 0) continue;
                if (number.substring(0, number.length() / 2)
                        .equals(number.substring(number.length() / 2))) {
                    sum += Long.parseLong(number);
                }
            }
        }
        return sum;
    }

    public Long gold() throws IOException {
        var content = Util.readFile("02", "input");
        var ranges = content.get(0).split(",");
        var numbers = new HashSet<Long>();
        for (var range : ranges) {
            var rangeStart = Long.parseLong(range.split("-")[0]);
            var rangeEnd = Long.parseLong(range.split("-")[1]);
            for (var i = rangeStart; i < rangeEnd + 1; i++) {
                var number = String.valueOf(i);
                var maxChunkSize = number.length() / 2;
                for (var j = 1; j < maxChunkSize + 1; j++) {
                    if (number.length() % j != 0) continue;
                    var chunks = new ArrayList<String>();
                    var n = 0;
                    while (n < number.length()) {
                        chunks.add(number.substring(n, n + j));
                        n += j;
                    }
                    var equal = true;
                    for (var m = 1; m < chunks.size(); m++) {
                        if (!chunks.get(m).equals(chunks.get(m - 1))) {
                            equal = false;
                            break;
                        }
                    }
                    if (equal) numbers.add(Long.parseLong(number));
                }
            }
        }
        return numbers.stream().reduce(Long::sum).get();
    }
}
