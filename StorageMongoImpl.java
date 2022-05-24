import com.mongodb.*;

import java.text.SimpleDateFormat;

public class StorageMongoImpl implements Storage {

    private MongoClient mongoClient;
    private DB database;
    private DBCollection collection;

    private SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");

    public StorageMongoImpl() {
        this.mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        this.database = mongoClient.getDB("DataLog");
    }

    public void error(LogEntry logEntry) {
        this.collection = database.getCollection("Errors");
        DBObject error = new BasicDBObject("date", date.format(logEntry.getCreated()))
                .append("time", time.format(logEntry.getCreated()))
                .append("level", logEntry.getLevel())
                .append("message", logEntry.getMessage());
        collection.insert(error);
    }

    public void log(Response response) {
        this.collection = database.getCollection("Logs");
        DBObject log = new BasicDBObject("operation", response.getOperation())
                .append("answer", response.getResult());
        collection.insert(log);
    }
}
