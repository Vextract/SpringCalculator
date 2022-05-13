
import java.io.IOException;

public class Operations {


    public static void main(String[] args) throws IOException {
        Model model = new Model();
        View view = new View();
        AbstractLogger logger = new MyLogger();

        Controller controller = new Controller(model, view, logger);

        controller.processIncomingInformation(152.0d,10.0d,"*");

        logger.getLog().printLogEntries();
    }
}
