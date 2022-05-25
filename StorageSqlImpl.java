import java.sql.*;

public class StorageSqlImpl implements Storage {

    private String url;
    private String username;
    private String password;

    private Connection connection;

    public StorageSqlImpl(String databaseName) {
        if (databaseName.equalsIgnoreCase("postgresql")) {
            url = Connections.URL_FOR_POSTGRESQL;
            username = Connections.USERNAME_FOR_POSTGRESQL;
            password = Connections.PASSWORD_FOR_POSTGRESQL;
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else if (databaseName.equalsIgnoreCase("mysql")) {
            url = Connections.URL_FOR_MYSQL;
            username = Connections.USERNAME_FOR_MYSQL;
            password = Connections.PASSWORD_FOR_MYSQL;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void error(LogEntry logEntry) {

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO logs VALUES(?,?,?,?)");

            preparedStatement.setDate(1, new Date(logEntry.getCreated().getTime()));
            preparedStatement.setString(2, logEntry.getLoggerName());
            preparedStatement.setString(3, logEntry.getLevel());
            preparedStatement.setString(4, logEntry.getMessage());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void log(Response response) {

    }
}
