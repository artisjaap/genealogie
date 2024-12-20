package be.genealogie.applicatie;

import be.genealogie.domein.utils.LocalDateUtils;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.Arrays;

public enum Sterrenbeeld {
    RAM(MonthDay.of(Month.MARCH, 21), MonthDay.of(Month.APRIL, 19)),
    STIER(MonthDay.of(Month.APRIL, 20), MonthDay.of(Month.MAY, 20)),
    TWEELINGEN(MonthDay.of(Month.MAY, 21), MonthDay.of(Month.JUNE, 20)),
    KREEFT(MonthDay.of(Month.JUNE, 21), MonthDay.of(Month.JULY, 22)),
    LEEUW(MonthDay.of(Month.JULY, 23), MonthDay.of(Month.AUGUST, 22)),
    MAAGD(MonthDay.of(Month.AUGUST, 23), MonthDay.of(Month.SEPTEMBER, 22)),
    WEEGSCHAAL(MonthDay.of(Month.SEPTEMBER, 23), MonthDay.of(Month.OCTOBER, 22)),
    SCHORPIOEN(MonthDay.of(Month.OCTOBER, 23), MonthDay.of(Month.NOVEMBER, 21)),
    BOOGSCHUTTER(MonthDay.of(Month.NOVEMBER, 22), MonthDay.of(Month.DECEMBER, 21)),
    STEENBOK(MonthDay.of(Month.DECEMBER, 22), MonthDay.of(Month.JANUARY, 19)),
    WATERMAN(MonthDay.of(Month.JANUARY, 20), MonthDay.of(Month.FEBRUARY, 18)),
    VISSEN(MonthDay.of(Month.FEBRUARY, 18), MonthDay.of(Month.MARCH, 20)),
    ;

    final MonthDay van;
    final MonthDay tot;

    Sterrenbeeld(MonthDay van, MonthDay tem){
        this.van = van;
        this.tot = tem;
    }

    public static Sterrenbeeld voor(LocalDate date){
        MonthDay monthDay = MonthDay.of(date.getMonth(), date.getDayOfMonth());
        return Arrays.stream(Sterrenbeeld.values())
                .filter(s -> LocalDateUtils.isBetween(monthDay, s.van, s.tot))
                .findFirst()
                .orElse(STEENBOK);
    }
}
