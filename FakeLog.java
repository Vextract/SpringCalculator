import java.util.ArrayList;
import java.util.List;

public class FakeLog {
    private List<String> logEntries = new ArrayList<>();

    public List<String> getLogEntries() {
        return logEntries;
    }

    public void printLogEntries() {
        for (String entry:logEntries) {
            System.out.println(entry);
        }
    }
}
