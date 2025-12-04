import java.io.IOException;

public class Day3 {
    public int silver() throws IOException {
        var content = Util.readFile("03", "input");
        var sum = 0;
        for (var battery : content) {
            var joltages = battery.split("");
            var firstJoltage = 0;
            var maxJoltagePosition = 0;
            var secondJoltage = 0;
            for (var i = 0; i < joltages.length - 1; i++) {
                var joltage = Integer.parseInt(joltages[i]);
                if (firstJoltage < joltage) {
                    firstJoltage = joltage;
                    maxJoltagePosition = i;
                }
            }
            for (var i = maxJoltagePosition + 1; i < joltages.length; i++) {
                var joltage = Integer.parseInt(joltages[i]);
                if (secondJoltage < joltage) secondJoltage = joltage;
            }
            var maxJoltage = Integer.parseInt(String.valueOf(firstJoltage) + secondJoltage);
            sum += maxJoltage;
        }
        return sum;
    }

    public Long gold() throws IOException {
        var content = Util.readFile("03", "input");
        var sum = 0L;
        for (var battery : content) {
            var joltages = battery.split("");
            var singleMaxJoltage = 0;
            var joltagePositionFloor = 0;
            var maxJoltage = new StringBuilder();
            for (var j = 11; j >= 0; j--) {
                singleMaxJoltage = 0;
                for (var i = joltagePositionFloor; i < joltages.length - j; i++) {
                    var joltage = Integer.parseInt(joltages[i]);
                    if (singleMaxJoltage < joltage) {
                        singleMaxJoltage = joltage;
                        joltagePositionFloor = i + 1;
                    }
                }
                maxJoltage.append(singleMaxJoltage);
            }
            sum += Long.parseLong(maxJoltage.toString());
        }
        return sum;
    }
}
