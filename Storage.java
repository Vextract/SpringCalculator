public interface Storage {

    void log(Response response);

    void error(LogEntry logEntry);
}
