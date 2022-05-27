package storage;

import loggers.LogEntry;
import main.Response;

public interface Storage {

    void log(Response response);

    void error(LogEntry logEntry);
}
