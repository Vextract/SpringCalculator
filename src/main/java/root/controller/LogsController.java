package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import root.repository.DateFilter;
import root.repository.FiltersValidator;
import root.repository.LogResponse;
import root.repository.Repository;

@RequestMapping("repositorySql")
@RestController()
public class LogsController {

    private Repository repository;
    private FiltersValidator validator;

    public LogsController(Repository repository, FiltersValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Autowired


    @RequestMapping("/logs")
    @GetMapping
    public LogResponse getErrorsLog() {
        return new LogResponse("Successful.", repository.getErrorsLog());
    }

    @RequestMapping("/filteredLogs")
    @PostMapping
    public LogResponse getErrorsLogByFilter(@RequestBody DateFilter[] dateFilters) {
        return validator.validateAndGetFromDB(dateFilters);
    }
}
