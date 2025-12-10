import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day7 {
    public int silver() throws IOException {
        var content = Util.readFile("07", "input");
        var start = content.get(0).indexOf("S");
        var counter = new int[] {0};
        descend(new Position(1, start), content, new ArrayList<>(), counter);
        return counter[0];
    }

    private void descend(Position pos, List<String> content, List<Position> visited, int[] counter) {
        if (pos.y() >= content.size()) return;

        if (content.get(pos.y()).charAt(pos.x()) == '.' && !visited.contains(pos)) {
            visited.add(pos);
            descend(new Position(pos.y() + 1, pos.x()), content, visited, counter);
        } else if (content.get(pos.y()).charAt(pos.x()) == '^' && !visited.contains(pos)) {
            counter[0] += 1;
            var leftPos = new Position(pos.y(), pos.x() - 1);
            if (!visited.contains(leftPos)) {
                descend(leftPos, content, visited, counter);
            }
            var rightPos = new Position(pos.y(), pos.x() + 1);
            if (!visited.contains(rightPos)) {
                descend(rightPos, content, visited, counter);
            }
        }
    }

    private record Position(int y, int x) { }
}
