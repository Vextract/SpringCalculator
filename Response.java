public class Response {

    private double result;

    private Exception exception;

    public Response(double result) {
        this.result = result;
    }

    public Response(Exception exception) {
        this.exception = exception;
    }

    public double getResult() {
        return result;
    }

    public Exception getException() {
        return exception;
    }
}
