import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;


public class LogEntry {

    private Date created;
    private String loggerName;
    private String level;
    private String message;

    public LogEntry(String loggerName, String level, Exception e) {
        java.util.Date date = new java.util.Date();
        this.created = new java.sql.Date(date.getTime());
        this.loggerName = loggerName;
        this.level = level;
        if (e instanceof NotEnoughArgumentsException) {
            this.message = "Недостаточно аргументов";
        } else if (e instanceof NumberFormatException) {
            this.message = "Неправильный формат аргументов";
        } else if (e instanceof UnsupportedOperationExceptionCustom) {
            this.message = "Операция " +
                    ((UnsupportedOperationExceptionCustom) e).getOperation() +
                    " не поддерживается";
        } else {
            this.message = "Неизвестная ошибка";
        }


    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
