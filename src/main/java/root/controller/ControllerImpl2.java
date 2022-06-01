package root.controller;

import org.springframework.stereotype.Component;
import root.loggers.Log;
import root.repository.Repository;

import java.util.List;

@Component
public class ControllerImpl2 implements Controller {

    public void printOutLogs(Repository repository) {
        List<Log> list = repository.getErrorsLog();
        for (Log log:list) {
            System.out.println(log.toString());
        }
    }
}
