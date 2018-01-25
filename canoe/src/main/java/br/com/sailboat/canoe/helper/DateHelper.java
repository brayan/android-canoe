package br.com.sailboat.canoe.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.sailboat.canoe.R;

public class DateHelper {

    private static final String DATABASE_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Calendar parseStringWithDatabaseFormatToCalendar(String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATABASE_DATE_TIME_FORMAT);
        calendar.setTime(sdf.parse(date));

        return calendar;
    }

    public static String parseStringWithDatabaseFormatToSimpleDate(Context ctx, String date) throws ParseException {
        Calendar calendar = parseStringWithDatabaseFormatToCalendar(date);
        return getShortDate(ctx, calendar);
    }

    public static String parseCalendarWithDatabaseFormatToString(Calendar calendar) {
        return new SimpleDateFormat(DATABASE_DATE_TIME_FORMAT).format(calendar.getTime());
    }

    public static String formatTimeWithAndroidFormat(Context ctx, Calendar calendar) {
        String format = getAndroidHourFormat(ctx);
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    public static String getMonthAndDayShort(Context ctx, Calendar calendar) {
        String pattern = ctx.getString(R.string.pattern_month_day_short);
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }

    public static String getMonthAndDayLong(Context ctx, Calendar calendar) {
        String pattern = ctx.getString(R.string.pattern_month_day_long);
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }

    public static String getShortDate(Context ctx, Calendar calendar) {
        return DateFormat.getDateFormat(ctx).format(calendar.getTime());
    }

    public static String getSimpleDayName(Context ctx, Calendar calendar) {

        if (isToday(calendar)) {
            return ctx.getString(R.string.today);

        } else if (isTomorrow(calendar)) {
            return ctx.getString(R.string.tomorrow);

        } else {
            return getFullDateName(ctx, calendar);
        }
    }

    public static String getDateName(Context ctx) {
        if (DateFormat.is24HourFormat(ctx)) {
            return "HH:mm";
        } else {
            return "hh:mm a";
        }
    }

    public static String getFullDateName(Context ctx, Calendar calendar) {
        if (isToday(calendar)) {
            return ctx.getString(R.string.today) + ", " + DateFormat.getLongDateFormat(ctx).format(calendar.getTime());

        } else if (isTomorrow(calendar)) {
            return ctx.getString(R.string.tomorrow) + ", " + DateFormat.getLongDateFormat(ctx).format(calendar.getTime());

        } else {
            return getDayName(calendar) + ", " + DateFormat.getLongDateFormat(ctx).format(calendar.getTime());
        }
    }

    public static boolean isCurrentYear(Calendar calendar) {
        Calendar today = Calendar.getInstance();
        return (today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR));
    }

    @NonNull
    private static String getDayName(Calendar calendar) {
        String day = new SimpleDateFormat("EEEE").format(calendar.getTime());
        return StringHelper.upperCaseFirstLetter(day);
    }


    public static String getAndroidHourFormat(Context ctx) {
        if (DateFormat.is24HourFormat(ctx)) {
            return "HH:mm";
        } else {
            return "hh:mm a";
        }
    }

    public static boolean isYesterday(Calendar calendar) {
        return calendar.after(getFinalCalendarForBeforeYesterday())
                && calendar.before(getInitialCalendarForToday());
    }

    public static boolean isToday(Calendar calendar) {
        return calendar.after(getFinalCalendarForYesterday())
                && calendar.before(getInitialCalendarForTomorrow());
    }

    public static boolean isNotToday(Calendar calendar) {
        return !isToday(calendar);
    }

    public static boolean isTomorrow(Calendar calendar) {
        return calendar.after(getFinalCalendarForToday())
                && calendar.before(getInitialCalendarForAfterTomorrow());
    }

    public static boolean isAfterTomorrow(Calendar calendar) {
        return calendar.after(getFinalCalendarForTomorrow());
    }

    public static boolean isAfterToday(Calendar calendar) {
        return calendar.after(getFinalCalendarForToday());
    }

    public static boolean isAfterYesterday(Calendar calendar) {
        return calendar.after(getFinalCalendarForYesterday());
    }

    public static boolean isBeforeToday(Calendar calendar) {
        return calendar.before(getInitialCalendarForToday());
    }

    public static boolean isBeforeYesterday(Calendar calendar) {
        return calendar.before(getInitialCalendarForYesterday());
    }

    public static boolean isBeforeNow(Calendar calendar) {
        return calendar.before(Calendar.getInstance());
    }

    public static Calendar getInitialDateTime() {
        Calendar currentTime = Calendar.getInstance();

        currentTime.set(Calendar.SECOND, 0);
        currentTime.set(Calendar.MILLISECOND, 0);

        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        if (minute >= 0 && minute < 30) {
            currentTime.set(Calendar.MINUTE, 30);

        } else if (minute >= 30 && minute <= 59) {

            currentTime.set(Calendar.MINUTE, 0);

            if (hour == 23) {
                currentTime.set(Calendar.HOUR_OF_DAY, 0);
                currentTime.add(Calendar.DAY_OF_MONTH, 1);
            } else {
                hour++;
                currentTime.set(Calendar.HOUR_OF_DAY, hour);
            }

        }

        return currentTime;
    }

    public static Calendar getInitialCalendarForToday() {
        Calendar today = Calendar.getInstance();
        setInitialTimeToCalendar(today);

        return today;
    }

    public static Calendar getFinalCalendarForToday() {
        Calendar today = Calendar.getInstance();
        setFinalTimeToCalendar(today);

        return today;
    }

    public static Calendar getFinalCalendarForYesterday() {
        Calendar yesterday = getFinalCalendarForToday();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);

        return yesterday;
    }

    public static Calendar getFinalCalendarForBeforeYesterday() {
        Calendar yesterday = getFinalCalendarForYesterday();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);

        return yesterday;
    }

    public static Calendar getFinalCalendarForTomorrow() {
        Calendar tomorrow = getFinalCalendarForToday();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);

        return tomorrow;
    }

    public static Calendar getInitialCalendarForTomorrow() {
        Calendar tomorrow = getInitialCalendarForToday();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);

        return tomorrow;
    }

    public static Calendar getInitialCalendarForAfterTomorrow() {
        Calendar afterTomorrow = getInitialCalendarForToday();
        afterTomorrow.add(Calendar.DAY_OF_MONTH, 2);

        return afterTomorrow;
    }

    public static Calendar getInitialCalendarForYesterday() {
        Calendar yesterday = getInitialCalendarForToday();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);

        return yesterday;
    }

    public static void setInitialTimeToCalendar(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static void setFinalTimeToCalendar(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
    }

}