package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import root.customExceptions.NotEnoughArgumentsException;
import root.customExceptions.UnsupportedOperationExceptionCustom;
import root.loggers.AbstractLogger;
import root.loggers.LogEntry;
import root.main.CalculationPackage;
import root.main.Response;
import root.model.Model;
import root.view.View;

@RequestMapping("calculation_api")
@RestController
public class SpringController implements Controller {

    private Model model;
    private View view;
    private final AbstractLogger logger;

    @Autowired
    public SpringController(Model model, View view, AbstractLogger logger) {
        this.model = model;
        this.view = view;
        this.logger = logger;
    }

    @RequestMapping("/process")
    @GetMapping
    public Response processIncomingInformation(@RequestBody CalculationPackage calculationPackage) {

        double number1 = calculationPackage.getA();
        double number2 = calculationPackage.getB();
        String operation = calculationPackage.getSign();

        /*try {
            number1 = Double.parseDouble(args[0]);
            number2 = Double.parseDouble(args[1]);
            operation = args[2];
        } catch (NumberFormatException e) {
            try {
                return new Response(e);
            } finally {
                logger.error(new LogEntry(e));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            try {
                return new Response(new NotEnoughArgumentsException());
            } finally {
                logger.error(new LogEntry(new NotEnoughArgumentsException()));
            }
        }*/

        Response response;

        try {
            final String operationString = number1 + " " + operation + " " + number2;
            final double answer = model.processNumbers(number1, number2, operation);
            response = new Response(operationString, answer);
        } catch (UnsupportedOperationException e) {
            try {
                response = new Response(new UnsupportedOperationExceptionCustom(operation));
                return response;
            } finally {
                logger.error(new LogEntry(new UnsupportedOperationExceptionCustom(operation)));
            }
        }
        try {
            return response;
        } finally {
            logger.log(response);
        }
    }
}
