import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class StorageMySqlImpl implements Storage {

    private static final String URL = "jdbc:mysql://localhost:3306/loggerdb?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    private SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
    private SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void error(LogEntry logEntry) {

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO logs VALUES(?,?,?,?,?)");

            preparedStatement.setString(1, date.format(logEntry.getCreated()));
            preparedStatement.setString(2, time.format(logEntry.getCreated()));
            preparedStatement.setString(3, logEntry.getLoggerName());
            preparedStatement.setString(4, logEntry.getLevel());
            preparedStatement.setString(5, logEntry.getMessage());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void log(Response response) {

    }
}
