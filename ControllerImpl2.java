public class ControllerImpl2 implements Controller{

    private Model model;
    private View view;
    private final AbstractLogger logger;

    public ControllerImpl2(Model model, View view, AbstractLogger logger) {
        this.model = model;
        this.view = view;
        this.logger = logger;
    }

    public void emptyMethod1(int one) {

    }

    public void emptyMethod2(String[] array) {

    }
}
