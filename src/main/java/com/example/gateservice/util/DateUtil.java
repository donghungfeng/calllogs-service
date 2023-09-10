package com.example.gateservice.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final SimpleDateFormat dateFormatHour = new SimpleDateFormat("yyyyMMddhhmmss");

    public static Long getDate(Long dateTime) {
        return dateTime / 1000000;
    }

    public static Long getCurrenDate() {
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        return Long.parseLong(dateFormat.format(new Date()));
    }

    public static Long getMinusDate(Long i) {return Long.parseLong(LocalDate.now().minusDays(i).format(formatter));}
    public static Long getCurrenDateHour(){
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        return Long.parseLong(dateFormatHour.format(new Date()));}
}
