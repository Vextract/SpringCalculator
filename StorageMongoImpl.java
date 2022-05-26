import com.mongodb.*;

import java.io.PrintWriter;
import java.io.StringWriter;


public class StorageMongoImpl implements Storage {

    private DB database;

    public StorageMongoImpl(DB database) {
        this.database = database;
    }

    public void error(LogEntry logEntry) {
        StringWriter sw = new StringWriter();
        logEntry.getException().printStackTrace(new PrintWriter(sw));
        String cause = sw.toString();
        DBCollection collection = database.getCollection("Errors");
        DBObject error = new BasicDBObject("date", logEntry.getCreated())
                .append("loggerName", logEntry.getLoggerName())
                .append("level", logEntry.getLevel())
                .append("message", logEntry.getMessage())
                .append("cause", cause);

        collection.insert(error);
    }

    public void log(Response response) {
        DBCollection collection = database.getCollection("Logs");
        DBObject log = new BasicDBObject("operation", response.getOperation())
                .append("answer", response.getResult());
        collection.insert(log);
    }
}
