package be.genealogie.domein.utils;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Period;

public class LocalDateUtils {

    public static int berekenLeeftijd(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public static boolean isBetween(MonthDay target, MonthDay start, MonthDay end) {
        if (start.isBefore(end)) {
            return !target.isBefore(start) && !target.isAfter(end);
        } else {
            return !target.isBefore(start) || !target.isAfter(end);
        }
    }

}
