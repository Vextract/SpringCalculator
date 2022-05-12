import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Operations {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Будет работать, пока пользователь не введёт "end"
        boolean turnedOn = true;

        while (turnedOn) {
            //Запрос на ввод чисел
            System.out.println("Пожалуйста введите два числа через пробел.");
            System.out.println("Если не хотите продолжать работу с калькулятором, напишите \"end\"");

            String str = reader.readLine();
            if (str.equalsIgnoreCase("end")) {
                turnedOn = false;
                reader.close();
                continue;
            }


            int number1 = 0;
            int number2 = 0;

            // Проверка на то что введены 2 числа.
            try {
                String[] buffer = str.split("\s+");
                number1 = Integer.parseInt(buffer[0]);
                number2 = Integer.parseInt(buffer[1]);
            } catch (Exception e) {
                System.out.println("Неправильный ввод.");
                continue;
            }


            //Запрос на ввод операции
            System.out.println("Введите название операции из списка:\n" +
                        "\t\"сложение\"\n" +
                        "\t\"вычитание\"\n" +
                        "\t\"умножение\"\n" +
                        "\t\"деление\"\n");

            String operation = reader.readLine();

            double result = 0;
            if (operation.equalsIgnoreCase("сложение")) {
                result = number1 + number2;
            } else if (operation.equalsIgnoreCase("вычитание")) {
                result = number1 - number2;
            } else if (operation.equalsIgnoreCase("умножение")) {
                result = number1 * number2;
            } else if (operation.equalsIgnoreCase("деление")) {
                result = (double) number1 / (double) number2;
            }

            System.out.printf("Ответ: %.3f\n", result);
        }
    }
}
