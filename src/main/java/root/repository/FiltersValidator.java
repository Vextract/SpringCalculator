package root.repository;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class FiltersValidator {

    public static String validate(DateFilter[] dateFilters) {
        if (dateFilters.length > 0) {
            if (dateFilters.length > 2) {
                return "Должно быть не больше двух фильтров.";
            }

            if (dateFilters[0].getFromOrTo().equalsIgnoreCase("from")
                    && dateFilters[1].getFromOrTo().equalsIgnoreCase("from") ||
                    dateFilters[0].getFromOrTo().equalsIgnoreCase("to")
                            && dateFilters[1].getFromOrTo().equalsIgnoreCase("to")
            ) {
                return "Оба фильтра не могут быть from/to";
            }

            if (dateFilters[0].getDate().before(new Date(100, Calendar.JANUARY, 1)) ||
                    dateFilters[1].getDate().before(new Date(100, Calendar.JANUARY, 1))) {
                return "Даты фильтров не должны быть раньше 01.01.2000.";
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
                return "From позднее даты To или наоборот.";
            }

            return "Successful";

        }
        return "Должен быть передан как минимум один фильтр.";
    }
}
