package root.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import root.loggers.Log;
import root.loggers.LogMapper;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RepositoryMySqlImpl implements Repository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RepositoryMySqlImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public LogResponse getErrorsLog() {
        List<Log> logs =  jdbcTemplate.query("SELECT * FROM logs", new LogMapper());
        return new LogResponse("Successful", logs);
    }

    @Override
    public LogResponse getErrorsLogByFilter(DateFilter[] dateFilters) {
        List<Log> logs =  getErrorsLog().getErrorsList();
        DateFilter from = null;
        DateFilter to = null;
        String validatorMessage;
        if (!(validatorMessage = FiltersValidator.validate(dateFilters)).equals("Successful")) {
            return new LogResponse(validatorMessage, new ArrayList<>());
        }

        // Разбираем и используем фильтры
        if (dateFilters.length == 2) {

                from = Arrays.stream(dateFilters)
                        .filter(dateFilter ->
                            dateFilter.getFromOrTo()
                                    .equalsIgnoreCase("from"))
                        .findAny()
                        .get();

                to = Arrays.stream(dateFilters)
                        .filter(dateFilter ->
                            dateFilter.getFromOrTo()
                                .equalsIgnoreCase("to"))
                        .findAny()
                        .get();

            DateFilter finalFrom = from;
            DateFilter finalTo = to;
            logs = logs.stream()
                    .filter(log -> !log.getDate().before(finalFrom.getDate())
                    && !log.getDate().after(finalTo.getDate()))
                    .collect(Collectors.toList());
            return new LogResponse(validatorMessage + " From " + from.getDate()
                    + " to " + to.getDate(),
                    logs);
        } else {
            if (dateFilters[0].getFromOrTo().equalsIgnoreCase("from")) {
                from = dateFilters[0];
                DateFilter finalFrom = from;
                logs = logs.stream()
                        .filter(log -> !log.getDate().before(finalFrom.getDate()))
                        .collect(Collectors.toList());
                return new LogResponse(validatorMessage + " From " + from.getDate(),
                        logs);
            } else {
                to = dateFilters[0];
                DateFilter finalTo = to;
                logs = logs.stream()
                        .filter(log -> !log.getDate().after(finalTo.getDate()))
                        .collect(Collectors.toList());
                return new LogResponse(validatorMessage + " To " + to.getDate(),
                        logs);
            }
        }
    }
}
