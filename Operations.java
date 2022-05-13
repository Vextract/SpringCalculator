
import java.util.logging.Logger;

public class Operations {


    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        AbstractLogger logger = new MyLogger(Logger.getLogger(ControllerImpl.class.getName()));

        ControllerImpl controller1 = new ControllerImpl(model, view, logger);
        ControllerImpl2 controller2 = new ControllerImpl2(model, view, logger);

        Listener listener = new Listener(new Controller[]{controller1, controller2});

    }
}
