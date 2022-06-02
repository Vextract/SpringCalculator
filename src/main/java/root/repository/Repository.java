package root.repository;

public interface Repository {

    LogResponse getErrorsLog();
    LogResponse getErrorsLogByFilter(DateFilter[] dateFilters);
}
