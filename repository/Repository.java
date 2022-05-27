package repository;

import loggers.Log;

import java.util.List;

public interface Repository {

    List<Log> getErrorsLog();
}
