package br.com.sailboat.canoe.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class UIHelper {

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();

        if (view != null) {
            getInputManager(activity).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void setActivityToHideKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public static void openKeyboard(Activity activity, EditText editText) {
        InputMethodManager manager = getInputManager(activity);
        manager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void blockScreenOrientation(Activity activity) {
        Configuration config = activity.getResources().getConfiguration();

        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    public static void unblockScreenOrientation(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    private static InputMethodManager getInputManager(Activity activity) {
        return (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

}
