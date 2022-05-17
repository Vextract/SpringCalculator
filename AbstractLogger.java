public interface AbstractLogger {

    void error(LogEntry logEntry);

    void log(double answer);

    String getLoggerName();
}
