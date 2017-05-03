package br.com.sailboat.canoe.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class DecimalHelper {

    public static double roundValue(double value, int decimals) {
        BigDecimal newValue = new BigDecimal(String.valueOf(value));
        newValue = newValue.setScale(decimals, BigDecimal.ROUND_HALF_UP);

        return newValue.doubleValue();
    }

    public static String formatValue(double value, int decimals) {
        if (value % 1 == 0) {
            long newValue = (long) roundValue(value, decimals);
            return String.valueOf(newValue);
        } else {
            return String.valueOf(roundValue(value, decimals));
        }
    }

    public static String formatCurrency(double value) {
        BigDecimal newValue = new BigDecimal(String.valueOf(value));

        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setRoundingMode(RoundingMode.HALF_UP);

        return format.format(newValue);
    }


}
