public interface AbstractLogger {

    void error(LogEntry logEntry);

    void log(Response response);
}
