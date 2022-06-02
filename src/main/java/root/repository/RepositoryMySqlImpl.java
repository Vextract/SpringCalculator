package root.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import root.loggers.Log;
import root.loggers.LogMapper;

import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("repositorySql")
@RestController()
public class RepositoryMySqlImpl implements Repository {

    private final JdbcTemplate jdbcTemplate;

    public RepositoryMySqlImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping("/logs")
    @GetMapping
    @Override
    public LogResponse getErrorsLog() {
        List<Log> logs =  jdbcTemplate.query("SELECT * FROM logs", new LogMapper());
        return new LogResponse("Successful", logs);
    }

    @RequestMapping("/filteredLogs")
    @PostMapping
    @Override
    public LogResponse getErrorsLogByFilter(@RequestBody DateFilter[] dateFilters) {
        List<Log> logs =  getErrorsLog().getErrorsList();
        if (dateFilters.length > 0) {

            // Больше двух фильтров - возвращаем пустой список.
            if (dateFilters.length > 2)
                return new LogResponse("Должно быть не больше двух фильтров.",
                        new ArrayList<>());

            DateFilter from = null;
            DateFilter to = null;

            // Разбираем используем фильтры
            if (dateFilters.length == 2) {

                // Если оба фильтра одинакового типа (from/to) - пустой список.
                if (dateFilters[0].getFromOrTo().equalsIgnoreCase("from")
                        && dateFilters[1].getFromOrTo().equalsIgnoreCase("from") ||
                        dateFilters[0].getFromOrTo().equalsIgnoreCase("to")
                                && dateFilters[1].getFromOrTo().equalsIgnoreCase("to")
                ) {
                    return new LogResponse("Оба фильтра не могут быть from/to",
                            new ArrayList<>());
                }

                // Если даты фильтров раньше 01.01.2000 - пустой список.
                if (dateFilters[0].getDate().before(new Date(100, Calendar.JANUARY, 1)) ||
                dateFilters[1].getDate().before(new Date(100, Calendar.JANUARY, 1))) {
                    return new LogResponse("Даты фильтров не должны быть раньше 01.01.2000.",
                    new ArrayList<>());
                }

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

                 // Если неправильно прописана последовательность дат - пустой список.
                if (from.getDate().after(to.getDate())) {
                    return new LogResponse("From позднее даты To или наоборот.",
                            new ArrayList<>());
                }

                DateFilter finalFrom = from;
                DateFilter finalTo = to;
                logs = logs.stream()
                        .filter(log -> !log.getDate().before(finalFrom.getDate())
                        && !log.getDate().after(finalTo.getDate()))
                        .collect(Collectors.toList());
                return new LogResponse("Successful. From " + from.getDate()
                        + " to " + to.getDate(),
                        logs);
            } else {
                if (dateFilters[0].getFromOrTo().equalsIgnoreCase("from")) {
                    from = dateFilters[0];
                    DateFilter finalFrom = from;
                    logs = logs.stream()
                            .filter(log -> !log.getDate().before(finalFrom.getDate()))
                            .collect(Collectors.toList());
                    return new LogResponse("Successful. From " + from.getDate(),
                            logs);
                } else {
                    to = dateFilters[0];
                    DateFilter finalTo = to;
                    logs = logs.stream()
                            .filter(log -> !log.getDate().after(finalTo.getDate()))
                            .collect(Collectors.toList());
                    return new LogResponse("Successful. To " + to.getDate(),
                            logs);
                }
            }
        }

        // Нет фильтров - возвращаем пустой список.
        return new LogResponse("Должен быть передан как минимум один фильтр.",
                new ArrayList<>());
    }
}
