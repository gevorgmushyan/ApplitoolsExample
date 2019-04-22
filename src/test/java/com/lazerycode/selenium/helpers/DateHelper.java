package com.lazerycode.selenium.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DateHelper {

    public static Map<Integer, String > months = new HashMap<>();

    static {
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");
    }
    public static String getPreviousYearFor(String format) {
        Calendar prevYear = Calendar.getInstance();
        prevYear.add(Calendar.YEAR, -1);
        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(prevYear.getTime());
    }

    public static String getNextYear(String format) {
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(nextYear.getTime());
    }

    public static String getWithAddedYear(String format, int years) {
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, years);
        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(nextYear.getTime());
    }

    public static String getCurrentYear(String format) {
        Calendar year = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(year.getTime());
    }

    public static String getNextMonth(String format) {
        Calendar year = Calendar.getInstance();
        year.add(Calendar.MONTH, 1);
        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(year.getTime());
    }

    public static String getNextDay(String format) {
        Calendar year = Calendar.getInstance();
        year.add(Calendar.DAY_OF_MONTH, 1);
        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(year.getTime());
    }

    /**
     *
     * @param month should be integer 1 - 12
     * @return the proper month Name in english. The first Symbol is in Upper case.
     */
    public static String getMonthName(Integer month) {
        if(month < 1 || month > 12 )
            throw new IllegalArgumentException("The argument month should be integer 1 to 12 included. ");
        return months.get(month);
    }

    /**
     * @return the proper month Name in english. The first Symbol is in Upper case.
     */
    public static String getCurrentMonthName() {
        Calendar cal = Calendar.getInstance();
        return months.get(cal.get(Calendar.MONTH) + 1);
    }

    public static void main(String[] args) {
        System.out.println(getWithAddedYear("MMyy", 4));
        System.out.println(getCurrentMonthName());
    }
}
