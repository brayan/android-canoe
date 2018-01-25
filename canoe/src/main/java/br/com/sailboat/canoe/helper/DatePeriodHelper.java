package br.com.sailboat.canoe.helper;

import android.content.Context;

import org.joda.time.Period;
import org.joda.time.ReadablePartial;

import br.com.sailboat.canoe.R;

public class DatePeriodHelper {

    public static String getDatePeriod(Context ctx, ReadablePartial starting, ReadablePartial end) {
        StringBuilder sb = new StringBuilder();

        Period period = new Period(starting, end);

        appendYears(ctx, sb, period);
        appendMonths(ctx, sb, period);
        appendWeeks(ctx, sb, period);
        appendDays(ctx, sb, period);

        return sb.toString();
    }


    public static String getShortTimePeriod(Context ctx, ReadablePartial starting, ReadablePartial end) {
        Period period = new Period(starting, end);

        StringBuilder sb = new StringBuilder();

        if (period.getHours() < 10) {
            sb.append("0" + period.getHours());
        } else {
            sb.append(period.getHours());
        }

        sb.append(":");

        if (period.getMinutes() < 10) {
            sb.append("0" + period.getMinutes());
        } else {
            sb.append(period.getMinutes());
        }

        sb.append(":");

        if (period.getSeconds() < 10) {
            sb.append("0" + period.getSeconds());
        } else {
            sb.append(period.getSeconds());
        }

        return sb.toString();
    }

    private static void appendYears(Context ctx, StringBuilder sb, Period period) {
        if (period.getYears() > 0) {
            if (period.getYears() == 1) {
                sb.append("1 " + ctx.getString(R.string.year));
            } else {
                sb.append(period.getYears() + " " + ctx.getString(R.string.years));
            }
        }
    }

    private static void appendMonths(Context ctx, StringBuilder sb, Period period) {
        if (period.getMonths() > 0) {

            if (StringHelper.isNotEmpty(sb.toString())) {
                if (period.getWeeks() > 0 || period.getDays() > 0) {
                    sb.append(", ");
                } else {
                    sb.append(" " + ctx.getString(R.string.and) + " ");
                }
            }

            if (period.getMonths() == 1) {
                sb.append("1 " + ctx.getString(R.string.month));
            } else {
                sb.append(period.getMonths() + " " + ctx.getString(R.string.months));
            }
        }
    }

    private static void appendWeeks(Context ctx, StringBuilder sb, Period period) {
        if (period.getWeeks() > 0) {

            if (StringHelper.isNotEmpty(sb.toString())) {

                if (period.getDays() > 0 || period.getHours() > 0 ||
                        period.getMinutes() > 0) {
                    sb.append(", ");
                } else {
                    sb.append(" " + ctx.getString(R.string.and) + " ");
                }

            }

            if (period.getWeeks() == 1) {
                sb.append("1 " + ctx.getString(R.string.week));
            } else {
                sb.append(period.getWeeks() + " " + ctx.getString(R.string.weeks));
            }
        }
    }

    private static void appendDays(Context ctx, StringBuilder sb, Period period) {
        if (period.getDays() > 0) {

            if (StringHelper.isNotEmpty(sb.toString())) {
                sb.append(" " + ctx.getString(R.string.and) + " ");
            }

            if (period.getDays() == 1) {
                sb.append("1 " + ctx.getString(R.string.day));
            } else {
                sb.append(period.getDays() + " " + ctx.getString(R.string.days));
            }
        }
    }

    private static void appendHours(Context ctx, StringBuilder sb, Period period) {
        if (period.getHours() > 0) {

            if (StringHelper.isNotEmpty(sb.toString())) {

                if (period.getMinutes() > 0 || period.getSeconds() > 0) {
                    sb.append(", ");
                } else {
                    sb.append(" " + ctx.getString(R.string.and) + " ");
                }

            }

            if (period.getHours() == 1) {
                sb.append("1 " + ctx.getString(R.string.hour));
            } else {
                sb.append(period.getHours() + " " + ctx.getString(R.string.hours));
            }
        }
    }

    private static void appendMinutes(Context ctx, StringBuilder sb, Period period) {
        if (period.getMinutes() > 0) {

            if (StringHelper.isNotEmpty(sb.toString())) {

                if (period.getSeconds() > 0) {
                    sb.append(", ");
                } else {
                    sb.append(" " + ctx.getString(R.string.and) + " ");
                }

            }

            if (period.getMinutes() == 1) {
                sb.append("1 " + ctx.getString(R.string.minute));
            } else {
                sb.append(period.getMinutes() + " " + ctx.getString(R.string.minutes));
            }
        }
    }

    private static void appendSeconds(Context ctx, StringBuilder sb, Period period) {
        if (period.getSeconds() > 0) {

            if (StringHelper.isNotEmpty(sb.toString())) {
                sb.append(" " + ctx.getString(R.string.and) + " ");
            }

            if (period.getSeconds() == 1) {
                sb.append("1 " + ctx.getString(R.string.second));
            } else {
                sb.append(period.getSeconds() + " " + ctx.getString(R.string.seconds));
            }
        }
    }

}
