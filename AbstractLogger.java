public interface AbstractLogger {

    void log(double number1, double number2, String operation, double result);

    FakeLog getLog();
}
