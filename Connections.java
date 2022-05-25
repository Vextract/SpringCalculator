import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class Connections {

    public static final String URL_FOR_MYSQL = "jdbc:mysql://localhost:3306/loggerdb?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    public static final String USERNAME_FOR_MYSQL = "root";
    public static final String PASSWORD_FOR_MYSQL = "root";

    public static final String URL_FOR_POSTGRESQL = "jdbc:postgresql://localhost:5432/loggerdb";
    public static final String USERNAME_FOR_POSTGRESQL = "postgres";
    public static final String PASSWORD_FOR_POSTGRESQL = "root";

    public static final DB MONGO_DATABASE = new MongoClient(new MongoClientURI("mongodb://localhost:27017"))
            .getDB("DataLog");
}
