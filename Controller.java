import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    private Model model;
    private View view;
    private double result;
    private boolean turnedOn;
    private double number1;
    private double number2;
    private String operation;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public double getResult() {
        return result;
    }

    public double getNumber1() {
        return number1;
    }

    public double getNumber2() {
        return number2;
    }

    public String getOperation() {
        return operation;
    }

    public void setTurnedOn(boolean turnedOn) {
        this.turnedOn = turnedOn;
    }

    public void processIncomingInformation() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (turnedOn) {
            // Выводим первый запрос для пользователя и условия выхода
            view.showInterface();

            String str = reader.readLine();
            if (str.equalsIgnoreCase("end")) {
                // При вводе слова "end" выключаем калькулятор
                turnedOn = false;
                reader.close();
                continue;
            }

            // Достаём числа из строки с проверкой на то что введены 2 числа.
            try {
                String[] buffer = str.split("\s+");
                number1 = Double.parseDouble(buffer[0]);
                number2 = Double.parseDouble(buffer[1]);
            } catch (Exception e) {

                // В случае неправильного ввода, выводим сообщения для пользователя
                view.notifyAboutWrongInput();
                continue;
            }

            while (true) {
                // Запрашиваем у пользователя тип операции, которую надо произвести
                view.operationTypeRequest();

                operation = reader.readLine();

                // Вызываем у модели метод вычисления, возвращает 1 в случае успешной операции
                int successfulOrNot = model.processNumbers(number1, number2, operation);

                result = model.getResult();

                if (successfulOrNot == 1) {
                    break;
                } else {

                    // В случае отсутствия запрошенной операции выводим сообщение для пользователя
                    view.noSuchOperationMessage();
                }
            }


            // Выводим результат для пользователя
            view.showResult();
        }
    }
}
