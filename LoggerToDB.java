import java.sql.*;
import java.io.*;
import java.util.*;

public class LoggerToDB implements AbstractLogger {

    private Storage storage;

    public LoggerToDB(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void error(LogEntry logEntry) {
        if (storage != null) {
            storage.error(logEntry);
        } else System.out.println("Storage не проиницилизирован");
    }

    @Override
    public void log(Response response) {
        if (storage != null) {
            storage.log(response);
        } else System.out.println("Storage не проиницилизирован");
    }
}
