package br.com.sailboat.canoe.dialog;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.helper.StringHelper;

public class ErrorDialog {

    public static void showDialog(FragmentManager manager, Context ctx, Exception e) {
        String msg = getErrorMessage(ctx, e);
        MessageDialog.showMessage(manager, msg, null);
    }

    private static String getErrorMessage(Context ctx, Exception e) {

        if (e != null && StringHelper.isNotEmpty(e.getMessage())) {
            return e.getMessage();

        } else if (ctx != null) {
            return ctx.getString(R.string.msg_error);

        } else {
            return "An error occurred while performing the operation";
        }

    }

}
