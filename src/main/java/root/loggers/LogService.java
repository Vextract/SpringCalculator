package root.loggers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.repository.*;

import java.util.Arrays;

@Component
public class LogService {

    private Repository repository;
    private FiltersValidator validator;

    @Autowired
    public LogService(Repository repository, FiltersValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public LogService(Repository repository) {
        this.repository = repository;
    }

    public LogResponse validateAndGetFromDB(DateFilter[] dateFilters) {
        ValidationResponse resp = validator.validate(dateFilters);
        // Если ответ невалидный - отправляем пустой список с сообщением.
        if (!resp.isValid()) {
            return new LogResponse(resp.getMessage(), null);
        } else {
            // Определяем количество фильтров
            if (dateFilters.length == 2) {
                if (dateFilters[0].getFromOrTo().equalsIgnoreCase("from")) {
                    return new LogResponse("Successful",
                            repository.getErrorsLogByTwoFilters(dateFilters[0].getDate(), dateFilters[1].getDate()));
                } else {
                    return new LogResponse("Successful",
                            repository.getErrorsLogByTwoFilters(dateFilters[1].getDate(), dateFilters[0].getDate()));
                }
            } else {
                if (dateFilters[0].getFromOrTo().equalsIgnoreCase("from")) {
                    return new LogResponse("Successful", repository.getErrorsLogFromDate(dateFilters[0].getDate()));
                } else {
                    return new LogResponse("Successful", repository.getErrorsLogToDate(dateFilters[0].getDate()));
                }
            }
        }
    }
}
