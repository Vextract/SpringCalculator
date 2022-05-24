import java.sql.*;
import java.text.SimpleDateFormat;

public class RepositoryMySqlImpl implements Repository {

    private static final String URL = "jdbc:mysql://localhost:3306/loggerdb?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    private SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");

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
    public void showLog() {
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
    }
}
