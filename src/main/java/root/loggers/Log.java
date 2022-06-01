package root.loggers;

import java.util.Date;

public class Log {

    private Date date;
    private String capturedBy;
    private String level;
    private String shortMessage;
    private String stacktrace;

    public Log(Date date, String capturedBy, String level, String shortMessage, String stacktrace) {
        this.date = date;
        this.capturedBy = capturedBy;
        this.level = level;
        this.shortMessage = shortMessage;
        this.stacktrace = stacktrace;
    }

    @Override
    public String toString() {
        return "root.loggers.Log{" +
                "date=" + date +
                ", capturedBy='" + capturedBy + '\'' +
                ", level='" + level + '\'' +
                ", shortMessage='" + shortMessage + '\'' +
                ", stacktrace='\n" + stacktrace + '\'' +
                '}' + "\n";
    }
}
