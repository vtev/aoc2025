import java.io.IOException;
import java.util.ArrayList;

public class Day6 {
    public long silver() throws IOException {
        var content = Util.readFile("06", "input");
        var problems = new ArrayList<ArrayList<String>>();
        for (var i = 0; i < content.size(); i++) {
            var elements = content.get(i).trim().split(" +");
            for (var j = 0; j < elements.length; j++) {
                if (i == 0) {
                    var column = new ArrayList<String>();
                    column.add(elements[j]);
                    problems.add(column);
                } else {
                    problems.get(j).add(elements[j]);
                }
            }
        }
        var result = 0L;
        for (var problem : problems) {
            if (problem.get(problem.size() - 1).equals("+")) {
                var problemResult = 0L;
                for (var i = 0; i < problem.size() - 1; i++) {
                    problemResult += Long.parseLong(problem.get(i));
                }
                result += problemResult;
            }
            else if (problem.get(problem.size() - 1).equals("*")) {
                var problemResult = 1L;
                for (var i = 0; i < problem.size() - 1; i++) {
                    problemResult *= Long.parseLong(problem.get(i));
                }
                result += problemResult;
            }
        }
        return result;
    }

    // If using IntelliJ IDEA or other tool which removes trailing whitespaces from lines of text,
    // disable this feature before running.
    // Make sure input data is intact and trailing spaces are kept at the end of the lines.
    public long gold() throws IOException {
        var content = Util.readFile("06", "input");
        var elements = new ArrayList<String[]>();
        for (var line : content) {
            elements.add(line.split(""));
        }
        var numberOfLines = elements.size();
        var lineLength = elements.stream()
                .map(line -> line.length)
                .reduce(Integer::max)
                .get();
        var columns = new ArrayList<ArrayList<String>>();
        for (var i = 0; i < lineLength; i++) {
            var column = new ArrayList<String>();
            for (var j = 0; j < numberOfLines; j++) {
                column.add(elements.get(j)[i]);
            }
            columns.add(column);
        }
        var numbers = columns.stream()
                .map(column -> {
                    var number = "";
                    for (var i = 0; i < column.size() - 1; i++) {
                        number += column.get(i);
                    }
                    return number.trim();
                })
                .toList();
        var operations = new ArrayList<String>();
        for (var column : columns) {
            var operation = column.get(column.size() - 1);
            if (!operation.isBlank()) operations.add(operation);
        }
        var numberArrays = new ArrayList<ArrayList<Long>>();
        numberArrays.add(new ArrayList<>());
        for (var number : numbers) {
            if (number.isEmpty()) numberArrays.add(new ArrayList<>());
            else numberArrays.get(numberArrays.size() - 1).add(Long.parseLong(number));
        }
        var result = 0L;
        for (var i = 0; i < numberArrays.size(); i++) {
            if (operations.get(i).equals("+")) {
                var operationsResult = 0L;
                for (var j = 0; j < numberArrays.get(i).size(); j++) {
                    operationsResult += numberArrays.get(i).get(j);
                }
                result += operationsResult;
            }
            else if (operations.get(i).equals("*")) {
                var operationsResult = 1L;
                for (var j = 0; j < numberArrays.get(i).size(); j++) {
                    operationsResult *= numberArrays.get(i).get(j);
                }
                result += operationsResult;
            }
        }
        return result;
    }
}
