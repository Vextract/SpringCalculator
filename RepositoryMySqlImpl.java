import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryMySqlImpl implements Repository {

    private String url;
    private String username;
    private String password;

    private Connection connection;

    public RepositoryMySqlImpl(String databaseName) {
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
    public List<LogEntry> getErrorsLog() {
        try {
            String query = "SELECT * FROM logs";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String date = rs.getString("Date");
                String time = rs.getString("Time");
                String loggerName = rs.getString("LoggerName");
                String level = rs.getString("Level");
                String message = rs.getString("Message");

                System.out.format("%s, %s, %s, %s, %s\n", date, time, loggerName, level, message);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }
}
