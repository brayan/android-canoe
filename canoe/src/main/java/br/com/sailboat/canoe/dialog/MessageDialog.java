package br.com.sailboat.canoe.dialog;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;

import br.com.sailboat.canoe.base.BaseDialogFragment;

public class MessageDialog extends BaseDialogFragment {

    public static void showMessage(FragmentManager manager, String message, String title) {
        MessageDialog dialog = new MessageDialog();
        dialog.setMessage(message);
        dialog.setTitle(title);
        dialog.show(manager, MessageDialog.class.getSimpleName());
    }

    @Override
    protected void bindButtons(AlertDialog.Builder alert) {
        alert.setPositiveButton(android.R.string.ok, null);
    }

}
