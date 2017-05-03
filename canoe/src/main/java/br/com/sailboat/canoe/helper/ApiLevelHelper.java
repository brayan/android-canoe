package br.com.sailboat.canoe.helper;

import android.os.Build;

public class ApiLevelHelper {

    public static boolean isAtLeast(int apiLevel) {
        return Build.VERSION.SDK_INT >= apiLevel;
    }

    public static boolean isLowerThan(int apiLevel) {
        return Build.VERSION.SDK_INT < apiLevel;
    }

}
