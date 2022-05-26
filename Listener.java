import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Listener {

    private Map<Controller, List<Method>> methods = new HashMap<>();
    private Controller[] controllers;
    private AbstractLogger logger;

    public Listener(Controller[] controllers, AbstractLogger logger) {
        this.controllers = controllers;
        this.logger = logger;
        for (Controller controller: controllers) {
            methods.put(controller, List.of(controller.getClass().getDeclaredMethods()));
        }
        run();
    }

    public void run(){
        info();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String str = reader.readLine();
                if (str.equalsIgnoreCase("info")) {
                    info();
                    continue;
                }

                if (str.equalsIgnoreCase("log")) {
                    System.out.println("Введите название репозитория");
                    String str2 = reader.readLine();
                    for (Controller controller: controllers) {
                        if (controller instanceof ControllerImpl2) {
                            if (str2.equalsIgnoreCase("postgresql")) {
                                ((ControllerImpl2) controller)
                                        .printOutLogs(
                                                new RepositoryMySqlImpl(
                                                        Connections.getSQLConnection("postgresql")));
                            } else if (str2.equalsIgnoreCase("mysql")) {
                                ((ControllerImpl2) controller)
                                        .printOutLogs(
                                                new RepositoryMySqlImpl(
                                                        Connections.getSQLConnection("mysql")));
                            } else if (str2.equalsIgnoreCase("mongo")) {
                                ((ControllerImpl2) controller)
                                        .printOutLogs(
                                                new RepositoryMongoImpl(
                                                        Connections.MONGO_DATABASE));
                            } else {
                                System.out.println("Такой репозиторий отсутствует");
                            }
                        }
                    }
                    continue;
                }

                if (str.equalsIgnoreCase("end")) {
                    break;
                }

                String[] inputData = str.split(" ", 2);
                String[] classAndMethod = inputData[0].split("/");

                try {
                    for (Controller controller: methods.keySet()) {
                        if (controller.getClass().getSimpleName().equals(classAndMethod[0])) {
                            for (Method method:methods.get(controller)) {
                                if (method.getName().equals(classAndMethod[1])) {
                                    try {
                                        method.setAccessible(true);
                                        for (Object obj:controllers) {
                                            if (obj.getClass().getSimpleName()
                                                    .equals(controller.getClass().getSimpleName())) {

                                                String[] args = inputData[1].trim().split(" ");

                                                Response response = (Response) method.invoke(obj, (Object) args);
                                                handleResponse(response);
                                            }
                                        }
                                    } catch (IllegalAccessException | InvocationTargetException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    logger.error(new LogEntry(new NumberFormatException()));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void info() {
        System.out.println("Список доступных методов:\n");
        for (Map.Entry<Controller, List<Method>> entry: methods.entrySet()) {
            System.out.println("Методы " + entry.getKey().getClass().getSimpleName() + ":");
            for (Method method: entry.getValue()) {
                System.out.println("\t" + method.getName() + Arrays.toString(method.getParameters()));
            }
            System.out.println();
        }
    }

    public void handleResponse(Response response) {
        if (response.getException() == null) {
            System.out.println("Ответ: " + response.getResult());
        } else{
            if (response.getException() instanceof NotEnoughArgumentsException) {
                System.out.println("Недостаточно аргументов");
            } else if (response.getException() instanceof NumberFormatException) {
                System.out.println("Неправильный формат аргументов");
            } else if (response.getException() instanceof UnsupportedOperationExceptionCustom) {
                System.out.println("Операция " +
                        ((UnsupportedOperationExceptionCustom) response.getException()).getOperation() +
                        " не поддерживается");
            }
        }
    }
}
