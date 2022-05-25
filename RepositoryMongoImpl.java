import com.mongodb.*;
import org.bson.Document;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RepositoryMongoImpl implements Repository {

    private DB database;

    public RepositoryMongoImpl(DB database) {
        this.database = database;
    }

    @Override
    public List<LogEntry> getErrorsLog() {
        DBCollection collection = database.getCollection("Errors");
        List<LogEntry> log = new ArrayList<>();
        try (DBCursor results = collection.find()) {
            for (DBObject result: results) {
                LogEntry logEntry = new LogEntry(null, null, null);
                logEntry.setCreated(new Date(((java.util.Date) result.get("date")).getTime()));
                logEntry.setLoggerName("LoggerToDB");
                logEntry.setLevel((String) result.get("level"));
                logEntry.setMessage((String) result.get("message"));

                log.add(logEntry);
            }
        }
        return log;
    }
}
