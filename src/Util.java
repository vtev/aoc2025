import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Util {
    public static ArrayList<String> readFile(String day, String stage) throws IOException {
        var br = new BufferedReader(new FileReader("input/%s/%s.txt".formatted(day, stage)));
        var content = new ArrayList<String>();
        var line = br.readLine();
        while (line != null) {
            content.add(line);
            line = br.readLine();
        }
        return content;
    }
}
