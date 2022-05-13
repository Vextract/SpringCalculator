import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger implements AbstractLogger {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd - HH:mm:ss");
    private Logger logger;

    public MyLogger(Logger logger) {
        this.logger = logger;
    }


    @Override
    public void error(Exception e) {
        logger.log(Level.SEVERE, e.getMessage());
    }
}
