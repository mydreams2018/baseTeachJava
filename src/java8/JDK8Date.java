package java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class JDK8Date {
    DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
            .appendText(ChronoField.DAY_OF_MONTH)
            .appendLiteral("-")
            .appendText(ChronoField.MONTH_OF_YEAR)
            .appendLiteral("-")
            .appendText(ChronoField.YEAR)
            .parseCaseInsensitive()
            .toFormatter(Locale.ITALIAN);

    public static void main(String[] args) {
        zone();
    }
//日期
    public static void localDate(){
        LocalDate date = LocalDate.of(2021, 12, 8);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
        LocalDate today = LocalDate.now();
        System.out.println(date.get(ChronoField.YEAR));
        System.out.println(date.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(date.get(ChronoField.DAY_OF_MONTH));
        LocalDate dateFormat = LocalDate.parse("2014-03-18",DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(dateFormat);
    }
    //时间
    public static void localTime(){
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        LocalTime timeFormat = LocalTime.parse("13:45:20",DateTimeFormatter.ISO_LOCAL_TIME);
        System.out.println(timeFormat);
    }
//日期时间
    public static void localDateTime(){
        LocalDate date = LocalDate.of(2021, 12, 8);
        LocalTime time = LocalTime.of(13, 45, 20);
        LocalDateTime dt1 = LocalDateTime.of(2014, 3, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        LocalDate localDate = dt5.toLocalDate();
        System.out.println(dt1.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
   /* 如果你已经有一个LocalDate对象，想要创建它的一个修改版，最直接也最简单的方法是使
    用withAttribute方法。withAttribute方法会创建对象的一个副本，并按照需要修改它的属
    性。注意，下面的这段代码中所有的方法都返回一个修改了属性的对象。它们都不会修改原来的
    对象
    */
    public static void updateDate(){
        //直接修改为X值
        LocalDate date1 = LocalDate.of(2014, 3, 18);//2014-03-18
        LocalDate date2 = date1.withYear(2011);//2011-03-18
        LocalDate date3 = date2.withDayOfMonth(25);//2011-03-25
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);//2011-09-25
        //增加数值
        LocalDate date5 = LocalDate.of(2014, 3, 18);//2014-03-18
        LocalDate date6 = date5.plusWeeks(1);//2014-03-25
        LocalDate date7 = date6.minusYears(3);//2011-03-25
        LocalDate date8 = date7.plus(6, ChronoUnit.MONTHS);//2011-09-25
    }
  /*
    使用TemporalAdjuster我们可以进行更加复杂的日期操作
   dayOfWeekInMonth 创建一个新的日期，它的值为同一个月中每一周的第几天
    firstDayOfMonth 创建一个新的日期，它的值为当月的第一天
    firstDayOfNextMonth 创建一个新的日期，它的值为下月的第一天
    firstDayOfNextYear 创建一个新的日期，它的值为明年的第一天
    firstDayOfYear 创建一个新的日期，它的值为当年的第一天
    firstInMonth 创建一个新的日期，它的值为同一个月中，第一个符合星期几要求的值
    lastDayOfMonth 创建一个新的日期，它的值为当月的最后一天
    lastDayOfNextMonth 创建一个新的日期，它的值为下月的最后一天
    lastDayOfNextYear 创建一个新的日期，它的值为明年的最后一天
    lastDayOfYear 创建一个新的日期，它的值为今年的最后一天
    lastInMonth 创建一个新的日期，它的值为同一个月中，最后一个符合星期几要求的值
    next/previous创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星期几要求的日期
    nextOrSame/previousOrSame
    创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星
    期几要求的日期，如果该日期已经符合要求，直接返回该对象
    */
    public static void temporalAdjuster(){
        LocalDate date1 = LocalDate.of(2014, 3, 18);//2014-03-18
        LocalDate date2 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));//2014-03-23
        LocalDate date3 = date2.with(TemporalAdjusters.lastDayOfMonth());//2014-03-31
        LocalDate with = date3.with(new NextWorkingDay());//2014-04-01
        System.out.println(with);
    }
//格式化
    public static void format(){
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);//20140318
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);//2014-03-18
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        String formattedDate = date1.format(formatter);
        System.out.println(formattedDate);// 2014/03/18
        LocalDate date2 = LocalDate.parse(formattedDate,formatter);
        System.out.println(date2);// 2014-03-18
    }
   /*  ZoneId.of("Europe/Rome"); 地区ID都为“{区域}/{城市}”的格式，这些地区集合的设定都由英特网编号分配机构（IANA）
    的时区数据库提供。
    Asia/Shanghai
    */
    public static void zone(){
        ZoneId romeZone = ZoneId.systemDefault();
        System.out.println(romeZone);
        LocalDate date = LocalDate.of(2014, 3, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        LocalDateTime dateTime = LocalDateTime.of(2014, 3, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);
        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);

//        通过ZoneId，你还可以将LocalDateTime转换为Instant：
        LocalDateTime dateTime2= LocalDateTime.of(2014, 5, 18, 13, 45);
    /*    另一种比较通用的表达时区的方式是利用当前时区和UTC/格林尼治的固定偏差。比如，基
        258 第 12 章 新的日期和时间 API
        于这个理论，你可以说“纽约落后于伦敦5小时”。这种情况下，你可以使用ZoneOffset类，它
        是ZoneId的一个子类，表示的是当前时间和伦敦格林尼治子午线时间的差异：*/
        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");
        Instant instantFromDateTime = dateTime2.toInstant(newYorkOffset);
//        你也可以通过反向的方式得到LocalDateTime对象：
        LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, romeZone);
        LocalDateTime dateTime3 = LocalDateTime.of(2014,9, 18, 13, 45);
        OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dateTime3, newYorkOffset);
        System.out.println(dateTimeInNewYork);
    }
}
