
import java.util.logging.Logger;

public class Operations {


    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        AbstractLogger logger = new LoggerToDB(LoggerToDB.class.getSimpleName());

        ControllerImpl controller1 = new ControllerImpl(model, view, logger);
        ControllerImpl2 controller2 = new ControllerImpl2(model, view, logger);

        Listener listener = new Listener(new Controller[]{controller1, controller2}, logger);

        // ControllerImpl/processIncomingInformation 5 5 *
    }
}
