package storage;

import loggers.LogEntry;
import main.Response;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;

public class StorageSqlImpl implements Storage {

    private Connection connection;

    public StorageSqlImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void error(LogEntry logEntry) {
        StringWriter sw = new StringWriter();
        logEntry.getException().printStackTrace(new PrintWriter(sw));
        String cause = sw.toString();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO logs VALUES(?,?,?,?,?)");

            preparedStatement.setDate(1, new Date(logEntry.getCreated().getTime()));
            preparedStatement.setString(2, logEntry.getLoggerName());
            preparedStatement.setString(3, logEntry.getLevel());
            preparedStatement.setString(4, logEntry.getMessage());
            preparedStatement.setString(5, cause);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void log(Response response) {

    }
}
