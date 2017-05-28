package br.com.sailboat.canoe.helper;

import android.view.View;

public class ViewHelper {

    public static void scaleUp(View view) {
        view.setScaleX(1);
        view.setScaleY(1);
    }

    public static void scaleDown(View view) {
        view.setScaleX(0);
        view.setScaleY(0);
    }

}
