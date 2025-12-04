import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4 {
    public int silver() throws IOException {
        var content = Util.readFile("04", "input");
        var table = content.stream()
                .map(line -> Arrays.asList(line.split("")))
                .toList();
        var counter = 0;
        for (var row = 0; row < table.size(); row++) {
            for (var column = 0; column < table.get(0).size(); column++) {
                var grid = getGrid(table, row, column);
                if (canLift(grid)) counter++;
            }
        }
        return counter;
    }

    public int gold() throws IOException {
        var content = Util.readFile("04", "input");
        var table = content.stream()
                .map(line -> Arrays.asList(line.split("")))
                .toList();
        var counter = 0;
        var markedForRemoval = new ArrayList<List<Integer>>();
        var atLeastOneRollRemoved = true;
        while (atLeastOneRollRemoved) {
            atLeastOneRollRemoved = false;
            for (var row = 0; row < table.size(); row++) {
                for (var column = 0; column < table.get(0).size(); column++) {
                    var grid = getGrid(table, row, column);
                    if (canLift(grid)) {
                        counter++;
                        markedForRemoval.add(List.of(row, column));
                        atLeastOneRollRemoved = true;
                    }
                }
            }
            for (var coordinate : markedForRemoval) {
                table.get(coordinate.get(0)).set(coordinate.get(1), "x");
            }
            markedForRemoval.clear();
        }
        return counter;
    }

    private boolean canLift(List<List<String>> grid) {
        if (!grid.get(1).get(1).equals("@")) return false;

        var counter = -1;
        for (var row : grid) {
            for (var column : row) {
                if (column.equals("@")) counter++;
            }
        }
        return counter < 4;
    }

    private List<List<String>> getGrid(List<List<String>> table, int row, int column) {
        var grid = new ArrayList<List<String>>();
        grid.add(new ArrayList<>());
        grid.add(new ArrayList<>());
        grid.add(new ArrayList<>());
        grid.get(0).add(getSingleElement(table, row - 1, column - 1));
        grid.get(0).add(getSingleElement(table, row - 1, column));
        grid.get(0).add(getSingleElement(table, row - 1, column + 1));
        grid.get(1).add(getSingleElement(table, row, column - 1));
        grid.get(1).add(getSingleElement(table, row, column));
        grid.get(1).add(getSingleElement(table, row, column + 1));
        grid.get(2).add(getSingleElement(table, row + 1, column - 1));
        grid.get(2).add(getSingleElement(table, row + 1, column));
        grid.get(2).add(getSingleElement(table, row + 1, column + 1));
        return grid;
    }

    private String getSingleElement(List<List<String>> table, int row, int column) {
        var element = "";
        try {
            element = table.get(row).get(column);
        } catch (ArrayIndexOutOfBoundsException e) {
            element = "x";
        }
        return element;
    }
}
