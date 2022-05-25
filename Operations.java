import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mysql.cj.log.Log;

import java.util.List;

public class Operations {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();

        AbstractLogger logger = new LoggerToDB(new StorageSqlImpl("postgresql"));

        ControllerImpl controller1 = new ControllerImpl(model, view, logger);
        ControllerImpl2 controller2 = new ControllerImpl2(model, view, logger);

        Listener listener = new Listener(new Controller[]{controller1, controller2}, logger);

        // ControllerImpl/processIncomingInformation 5 5 *
    }
}
