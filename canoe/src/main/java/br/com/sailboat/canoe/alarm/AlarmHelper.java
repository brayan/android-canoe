package br.com.sailboat.canoe.alarm;

import java.util.Calendar;

import br.com.sailboat.canoe.helper.DateHelper;

public class AlarmHelper {

    public static void incrementToNextValidDate(Calendar calendar, int repeatType) {
        do {
            incrementToNext(calendar, repeatType);
        } while (DateHelper.isBeforeNow(calendar));
    }

    public static void incrementToNext(Calendar calendar, int repeatType) {
        switch (repeatType) {
            case RepeatType.SECOND: {
                calendar.add(Calendar.SECOND, 1);
                return;
            }
            case RepeatType.MINUTE: {
                calendar.add(Calendar.MINUTE, 1);
                return;
            }
            case RepeatType.HOUR: {
                calendar.add(Calendar.HOUR_OF_DAY, 1);
                return;
            }
            case RepeatType.DAY: {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                return;
            }
            case RepeatType.WEEK: {
                calendar.add(Calendar.WEEK_OF_MONTH, 1);
                return;
            }
            case RepeatType.MONTH: {
                calendar.add(Calendar.MONTH, 1);
                return;
            }
            case RepeatType.YEAR: {
                calendar.add(Calendar.YEAR, 1);
                return;
            }
        }
    }

}
