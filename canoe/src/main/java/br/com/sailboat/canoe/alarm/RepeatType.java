package br.com.sailboat.canoe.alarm;

import br.com.sailboat.canoe.R;

public class RepeatType {

    public static final int NOT_REPEAT = 0;
    public static final int DAY = 1;
    public static final int WEEK = 2;
    public static final int MONTH = 3;
    public static final int YEAR = 4;
    public static final int SECOND = 5;
    public static final int MINUTE = 6;
    public static final int HOUR = 7;
    public static final int CUSTOM = 8;

    public static int getDescription(int repeatType) {
        switch (repeatType) {
            case NOT_REPEAT: {
                return R.string.not_repeat;
            }
            case DAY: {
                return R.string.every_day;
            }
            case WEEK: {
                return R.string.every_week;
            }
            case MONTH: {
                return R.string.every_month;
            }
            case YEAR: {
                return R.string.every_year;
            }
            case SECOND: {
                return R.string.every_second;
            }
            case MINUTE: {
                return R.string.every_minute;
            }
            case HOUR: {
                return R.string.every_hour;
            }
            case CUSTOM: {
                return R.string.custom;
            }
            default: {
                return R.string.default_string;
            }
        }
    }


}
