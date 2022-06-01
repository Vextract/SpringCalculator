package root.repository;

import root.loggers.Log;

import java.util.List;

public interface Repository {

    List<Log> getErrorsLog();
}
