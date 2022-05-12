public class Model {

    private double result;

    public double getResult() {
        return result;
    }

    public int processNumbers(double firstNumber, double secondNumber, String sign) {

        // Определяем операцию по переданному знаку, при отсутствии таковой возвращаем -1
        switch (sign) {
            case "+": {
                result = add(firstNumber, secondNumber);
                return 1;
            }
            case "-": {
                result = subtract(firstNumber, secondNumber);
                return 1;
            }
            case "*": {
                result = multiply(firstNumber, secondNumber);
                return 1;
            }
            case "/": {
                result = divide(firstNumber, secondNumber);
                return 1;
            }
            default:
                return -1;
        }
    }

    private double add(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }

    private double subtract(double firstNumber, double secondNumber) {
        return firstNumber - secondNumber;
    }

    private double multiply(double firstNumber, double secondNumber) {
        return firstNumber * secondNumber;
    }

    private double divide(double firstNumber, double secondNumber) {
        return firstNumber / secondNumber;
    }

}
