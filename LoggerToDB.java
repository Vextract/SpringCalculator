import java.sql.*;
import java.io.*;
import java.util.*;

public class LoggerToDB implements AbstractLogger {

    private Repository repository;
    private Storage storage;
    private String loggerName;

    public LoggerToDB(Repository repository, Storage storage) {
        this.loggerName = "LoggerToDB";
        this.repository = repository;
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

    public void showLog() {
        if (repository != null) {
            repository.showLog();
        } else System.out.println("Repository не проиницилизирован");
    }

    @Override
    public String getLoggerName() {
        return "LoggerToMongo";
    }
}
