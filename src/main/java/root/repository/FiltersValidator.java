package root.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.loggers.Log;

import java.util.*;

@Component
public class FiltersValidator {

    private Repository repository;

    @Autowired
    public FiltersValidator(Repository repository) {
        this.repository = repository;
    }

    public LogResponse validateAndGetFromDB(DateFilter[] dateFilters) {
        if (dateFilters.length > 0) {
            if (dateFilters.length > 2) {
                return new LogResponse("Должно быть не больше двух фильтров.", null);
            }

            if (dateFilters.length == 2) {
                if (dateFilters[0].getFromOrTo().equalsIgnoreCase("from")
                        && dateFilters[1].getFromOrTo().equalsIgnoreCase("from") ||
                        dateFilters[0].getFromOrTo().equalsIgnoreCase("to")
                                && dateFilters[1].getFromOrTo().equalsIgnoreCase("to")
                ) {
                    return new LogResponse("Оба фильтра не могут быть from/to", null);
                }

                if (dateFilters[0].getDate().before(new Date(100, Calendar.JANUARY, 1)) ||
                        dateFilters[1].getDate().before(new Date(100, Calendar.JANUARY, 1))) {
                    return new LogResponse("Даты фильтров не должны быть раньше 01.01.2000.", null);
                }

                DateFilter from = Arrays.stream(dateFilters)
                        .filter(dateFilter ->
                                dateFilter.getFromOrTo()
                                        .equalsIgnoreCase("from"))
                        .findAny()
                        .get();

                DateFilter to = Arrays.stream(dateFilters)
                        .filter(dateFilter ->
                                dateFilter.getFromOrTo()
                                        .equalsIgnoreCase("to"))
                        .findAny()
                        .get();

                // Если неправильно прописана последовательность дат - пустой список.
                if (from.getDate().after(to.getDate())) {
                    return new LogResponse("From позднее даты To или наоборот.", null);
                }

                return new LogResponse("Successful", repository.getErrorsLogByTwoFilters(from.getDate(), to.getDate()));
            } else {
                if (dateFilters[0].getDate().before(new Date(100, Calendar.JANUARY, 1))) {
                    return new LogResponse("Даты фильтров не должны быть раньше 01.01.2000.", null);
                }

                if (dateFilters[0].getFromOrTo().equalsIgnoreCase("from")) {
                    return new LogResponse("Successful", repository.getErrorsLogFromDate(dateFilters[0].getDate()));
                } else {
                    return new LogResponse("Successful", repository.getErrorsLogToDate(dateFilters[0].getDate()));
                }
            }
        }
        return new LogResponse("Должен быть передан как минимум один фильтр.", null);
    }
}
