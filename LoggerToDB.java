import java.sql.*;
import java.io.*;
import java.util.*;

public class LoggerToDB implements AbstractLogger {

    private String loggerName;

    private static final String URL = "jdbc:mysql://localhost:3306/loggerdb?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

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

    public LoggerToDB(String loggerName) {
        this.loggerName = loggerName;
    }

    @Override
    public void error(LogEntry logEntry) {

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO logs VALUES(?,?,?,?)");

            preparedStatement.setDate(1, logEntry.getCreated());
            preparedStatement.setString(2, logEntry.getLoggerName());
            preparedStatement.setString(3, logEntry.getLevel());
            preparedStatement.setString(4, logEntry.getMessage());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void log(double answer) {

    }

    public String getLoggerName() {
        return loggerName;
    }
}
