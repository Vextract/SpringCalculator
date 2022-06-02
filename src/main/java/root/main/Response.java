package root.main;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

    private double result;

    private Exception exception;

    private String operation;

    public Response(@JsonProperty String operation,
                    @JsonProperty double result) {
        this.operation = operation;
        this.result = result;
    }

    public Response(Exception exception) {
        this.exception = exception;
    }

    public double getResult() {
        return result;
    }

    public String getOperation() {
        return operation;
    }

    public Exception getException() {
        return exception;
    }
}
