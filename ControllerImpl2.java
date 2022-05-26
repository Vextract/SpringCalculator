import java.util.List;

public class ControllerImpl2 implements Controller {

    public void printOutLogs(Repository repository) {
        List<Log> list = repository.getErrorsLog();
        for (Log log:list) {
            System.out.println(log.toString());
        }
    }
}
