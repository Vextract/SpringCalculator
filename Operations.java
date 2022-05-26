import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class Operations {

    public static void main(String[] args) throws SQLException {
        Model model = new Model();
        View view = new View();
        Connection sqlConnection = Connections.getSQLConnection("mysql");

        AbstractLogger logger = new LoggerToDB(new StorageSqlImpl(sqlConnection));

        ControllerImpl controller1 = new ControllerImpl(model, view, logger);
        ControllerImpl2 controller2 = new ControllerImpl2();

        Listener listener = new Listener(new Controller[]{controller1, controller2}, logger);

        // ControllerImpl/processIncomingInformation 5 5 *
        sqlConnection.close();
    }
}
