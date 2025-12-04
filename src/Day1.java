import java.io.IOException;

public class Day1 {
    public int silver() throws IOException {
        var content = Util.readFile("01", "input");
        var number = 50;
        var counter = 0;
        for (var line : content) {
            var direction = line.substring(0, 1);
            var step = Integer.parseInt(line.substring(1));
            if (direction.equals("R")) number = (number + step) % 100;
            else if (direction.equals("L")) number = (number - step) % 100;
            if (number < 0) number = 100 + number;
            if (number == 0) counter++;
        }
        return counter;
    }

    public int gold() throws IOException {
        var content = Util.readFile("01", "input");
        var number = 50;
        var counter = 0;
        for (var line : content) {
            var direction = line.substring(0, 1);
            var step = Integer.parseInt(line.substring(1));
            if (direction.equals("R")) {
                for (int i = 1; i <= step; i++) {
                    var currentSpin = (number + i) % 100;
                    if (currentSpin == 0) counter++;
                }
                number = (number + step) % 100;
            }
            else if (direction.equals("L")) {
                for (int i = 1; i <= step; i++) {
                    var currentSpin = (number - i) % 100;
                    if (currentSpin == 0) counter++;
                }
                number = (number - step) % 100;
                if (number < 0) number = 100 + number;
            }
        }
        return counter;
    }
}