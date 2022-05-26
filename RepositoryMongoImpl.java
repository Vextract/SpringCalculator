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
    public List<Log> getErrorsLog() {
        DBCollection collection = database.getCollection("Errors");
        List<Log> logs = new ArrayList<>();
        try (DBCursor results = collection.find()) {
            for (DBObject result: results) {
                Log log = new Log(
                        new Date(((java.util.Date) result.get("date")).getTime()),
                        (String) result.get("loggerName"),
                        (String) result.get("level"),
                        (String) result.get("message"),
                        (String) result.get("cause")
                );

                logs.add(log);
            }
        }
        return logs;
    }
}
