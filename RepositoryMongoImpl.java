import com.mongodb.*;
import org.bson.Document;

import java.text.SimpleDateFormat;

public class RepositoryMongoImpl implements Repository {

    private MongoClient mongoClient;
    private DB database;
    private DBCollection collection;

    private SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");


    public RepositoryMongoImpl() {
        this.mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        this.database = mongoClient.getDB("DataLog");
        this.collection = database.getCollection("Errors");
    }

    @Override
    public void showLog() {
        try (DBCursor results = collection.find(new BasicDBObject("date", "24.05.2022"))) {
            for (DBObject result: results) {
                System.out.println(result);
            }
        }
    }
}
