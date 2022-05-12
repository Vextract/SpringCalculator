import java.io.IOException;

public class Operations {

    public static void main(String[] args) throws IOException {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller();

        view.setController(controller);

        controller.setModel(model);
        controller.setView(view);
        controller.setTurnedOn(true);

        controller.processIncomingInformation();
    }
}
