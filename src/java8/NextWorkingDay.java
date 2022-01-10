package java8;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
/*
如果当天的星期介于周一至周五之间，日期向后移动一天；
如果当天是周六或者周日，则返回下一个周一。 [跳过周六|周日]
 */
public class NextWorkingDay implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;
        if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;//星期五
        else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;//周六
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }
}