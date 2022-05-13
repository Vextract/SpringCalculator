import java.lang.annotation.Repeatable;

public class ControllerImpl implements Controller {

    private Model model;
    private View view;
    private final AbstractLogger logger;

    public ControllerImpl(Model model, View view, AbstractLogger logger) {
        this.model = model;
        this.view = view;
        this.logger = logger;
    }

    public Response processIncomingInformation(String[] args) {

        double number1;
        double number2;
        String operation;
        try {
            number1 = Double.parseDouble(args[0]);
            number2 = Double.parseDouble(args[1]);
            operation = args[2];
        } catch (NumberFormatException e) {
            try {
                return new Response(e);
            } finally {
                logger.error(e);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            try {
                return new Response(new NotEnoughArgumentsException());
            } finally {
                logger.error(e);
            }
        }
        Response response;
        try {
            response = new Response(model.processNumbers(number1, number2, operation));
        } catch (UnsupportedOperationException e) {
            try {
                response = new Response(new UnsupportedOperationExceptionCustom(args[2]));
                return response;
            } finally {
                logger.error(e);
            }
        }
        return response;
    }

    public void emptyMethod1(int one) {

    }

    public void emptyMethod2(String[] array) {

    }
}