package root.repository;

import root.loggers.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryMySqlImpl implements Repository {

    private Connection connection;

    public RepositoryMySqlImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Log> getErrorsLog() {
        List<Log> logs = new ArrayList<>();
        try {
            String query = "SELECT * FROM logs";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Date date = rs.getDate("Dated");
                String loggerName = rs.getString("Logger");
                String level = rs.getString("Level");
                String message = rs.getString("Message");
                String cause = rs.getString("Cause");

                Log log = new Log(date, loggerName, level, message, cause);

                logs.add(log);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return logs;
    }
}
