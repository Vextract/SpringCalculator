
import java.io.*;

public class Controller {

    private Model model;
    private View view;
    private final AbstractLogger logger;

    public Controller(Model model, View view, AbstractLogger logger) {
        this.model = model;
        this.view = view;
        this.logger = logger;
    }

    public void processIncomingInformation(double number1, double number2, String operation) {

        int successfulOrNot = model.processNumbers(number1, number2, operation);
        if (successfulOrNot == -1) {
            return;
        }
        view.showResult(number1,number2, operation, model.getResult());
        logger.log(number1,number2, operation, model.getResult());

        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // Выводим первый запрос для пользователя и условия выхода
            view.showInterface();

            String str = reader.readLine();
            if (str.equalsIgnoreCase("end")) {

                // При вводе слова "end" выключаем калькулятор
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


                // Вызываем у модели метод вычисления, возвращает 1 в случае успешной операции
                int successfulOrNot = model.processNumbers(number1, number2, operation);

                if (successfulOrNot == 1) {
                    break;
                } else {

                    // В случае отсутствия запрошенной операции выводим сообщение для пользователя
                    view.noSuchOperationMessage();
                }
            }

            // Выводим результат для пользователя

        }*/
    }
}
