package br.com.sailboat.canoe.helper;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.dialog.TwoOptionsDialog;

public class DialogHelper {

    public static void showDeleteDialog(FragmentManager manager, Context ctx, TwoOptionsDialog.PositiveCallback callback) {
        TwoOptionsDialog dialog = new TwoOptionsDialog();
        dialog.setMessage(ctx.getString(R.string.are_you_sure));
        dialog.setPositiveMsg(ctx.getString(R.string.delete));
        dialog.setPositiveCallback(callback);

        dialog.show(manager, "DELETE_DIALOG");
    }

    public static void showYesOrNoDialog(FragmentManager manager, Context ctx, TwoOptionsDialog.PositiveCallback callback) {
        TwoOptionsDialog dialog = new TwoOptionsDialog();
        dialog.setMessage(ctx.getString(R.string.are_you_sure));
        dialog.setPositiveMsg(ctx.getString(android.R.string.yes));
        dialog.setNegativeMsg(ctx.getString(android.R.string.no));
        dialog.setPositiveCallback(callback);

        dialog.show(manager, "YES_OR_NO_DIALOG");
    }

}
