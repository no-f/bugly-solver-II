package com.bugly.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author no_f
 * @since 2020-07-02
 */
public class TimeUtils {

    public static Date getFirstDay() {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getLastDay() {
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }

    public static Boolean compare(Date date) {
      return date.before(getLastDay()) && date.after(getFirstDay());
    }

}
