package br.com.sailboat.canoe.helper;

import android.app.Activity;
import android.content.Context;
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

    private static InputMethodManager getInputManager(Activity activity) {
        return (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

}
