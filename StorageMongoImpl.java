import com.mongodb.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StorageMongoImpl implements Storage {

    private DB database;

    public StorageMongoImpl(DB database) {
        this.database = database;
    }

    public void error(LogEntry logEntry) {
        DBCollection collection = database.getCollection("Errors");
        DBObject error = new BasicDBObject("date", logEntry.getCreated())
                .append("level", logEntry.getLevel())
                .append("message", logEntry.getMessage());
        collection.insert(error);
    }

    public void log(Response response) {
        DBCollection collection = database.getCollection("Logs");
        DBObject log = new BasicDBObject("operation", response.getOperation())
                .append("answer", response.getResult());
        collection.insert(log);
    }
}
