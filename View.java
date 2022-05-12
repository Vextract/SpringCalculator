import java.io.IOException;

public class View {

    private Controller controller;
    private double result;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public double getResult() {
        return controller.getResult();
    }

    public void writeMessage(String str, Double result) {
        if (result == null) {
            System.out.println(str);
        } else {
            System.out.printf(str, result);
        }
    }

    public void showInterface() {
        writeMessage("Пожалуйста введите два числа через пробел.", null);
        writeMessage("При разделении дробного числа используйте точку", null);
        writeMessage("Если не хотите продолжать работу с калькулятором, напишите \"end\"", null);
    }

    public void notifyAboutWrongInput() {
        writeMessage("Неправильный ввод.", null);
    }

    public void operationTypeRequest() {
        writeMessage("Введите название операции из списка:\n" +
                "\t\"сложение\"  - Введите \"+\"\n" +
                "\t\"вычитание\" - Введите \"-\"\n" +
                "\t\"умножение\" - Введите \"*\"\n" +
                "\t\"деление\"   - Введите \"/\"\n", null);
    }

    public void showResult() {
        result = getResult();
        writeMessage("Ответ: " +
                controller.getNumber1() + " " +
                controller.getOperation() + " " +
                controller.getNumber2() +
                " = %.3f\n\n", result);
    }

    public void noSuchOperationMessage() {
        writeMessage("Такая операция не поддерживается, повторите запрос.", null);
    }
}
