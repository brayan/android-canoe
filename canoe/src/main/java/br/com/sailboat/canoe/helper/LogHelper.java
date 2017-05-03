package br.com.sailboat.canoe.helper;

import android.util.Log;

public class LogHelper {

    public static void logException(Exception e) {

        if (e != null && e.getMessage() != null) {
            Log.e("LOG_EXCEPTION", e.getMessage(), e);

        } else if (e != null) {
            Log.e("LOG_EXCEPTION", "An error occurred while performing the operation", e);

        } else {
            Log.e("LOG_EXCEPTION", "An error occurred while performing the operation");
        }

    }

}
