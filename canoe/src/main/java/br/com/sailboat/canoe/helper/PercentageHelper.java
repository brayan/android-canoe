package br.com.sailboat.canoe.helper;

import java.text.ParseException;
import java.util.Calendar;

public class PercentageHelper {

    public static double getPercentageProgress(Calendar startDate, Calendar endDate) {
        long now = System.currentTimeMillis();
        long start = startDate.getTimeInMillis();
        long end = endDate.getTimeInMillis();

        if (start > now) {
            return 0;
        }

        if (now > end || start == end) {
            return 100;
        }

        return DecimalHelper.roundValue(100 - (((double) (end - now) * 100 / (end - start))), 2);
    }


    public static double getPercentageProgressFromDatabase(String start, String end) {
        try {
            Calendar initialDate = DateHelper.parseStringWithDatabaseFormatToCalendar(start);
            Calendar deadline = DateHelper.parseStringWithDatabaseFormatToCalendar(end);

            return getPercentageProgress(initialDate, deadline);

        } catch (ParseException e) {
            LogHelper.logException(e);
            return 0;
        }
    }

    public static String getPercentageProgressFormatted(Calendar startDate, Calendar endDate) {
        return DecimalHelper.formatValue(getPercentageProgress(startDate, endDate), 2) + "%";
    }


}
