package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import root.loggers.LogService;
import root.repository.DateFilter;
import root.repository.FiltersValidator;
import root.repository.LogResponse;
import root.repository.Repository;

@RequestMapping("repositorySql")
@RestController()
public class LogsController {

    private Repository repository;
    private LogService logService;

    @Autowired
    public LogsController(Repository repository, LogService logService) {
        this.repository = repository;
        this.logService = logService;
    }

    @RequestMapping("/logs")
    @GetMapping
    public LogResponse getErrorsLog() {
        return new LogResponse("Successful.", repository.getErrorsLog());
    }

    @RequestMapping("/filteredLogs")
    @PostMapping
    public LogResponse getErrorsLogByFilter(@RequestBody DateFilter[] dateFilters) {
        return logService.validateAndGetFromDB(dateFilters);
    }
}
