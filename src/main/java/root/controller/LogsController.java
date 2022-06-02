package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import root.repository.DateFilter;
import root.repository.LogResponse;
import root.repository.Repository;

@RequestMapping("repositorySql")
@RestController()
public class LogsController {

    private Repository repository;

    @Autowired
    public LogsController(Repository repository) {
        this.repository = repository;
    }

    @RequestMapping("/logs")
    @GetMapping
    public LogResponse getErrorsLog() {
        return repository.getErrorsLog();
    }

    @RequestMapping("/filteredLogs")
    @PostMapping
    public LogResponse getErrorsLogByFilter(@RequestBody DateFilter[] dateFilters) {
        return repository.getErrorsLogByFilter(dateFilters);
    }


}
