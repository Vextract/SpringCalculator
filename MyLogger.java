import java.text.SimpleDateFormat;
import java.util.Date;

public class MyLogger implements AbstractLogger {
    private FakeLog log;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd - HH:mm:ss");

    public MyLogger() {
        this.log = new FakeLog();
    }

    public FakeLog getLog() {
        return log;
    }

    @Override
    public void log(double number1, double number2, String operation, double result) {
        log.getLogEntries().add(format.format(new Date()) +
                "\nПроизведена операция: " + number1 + " " + operation + " " + number2 + " = " + result + "\n");
    }
}
