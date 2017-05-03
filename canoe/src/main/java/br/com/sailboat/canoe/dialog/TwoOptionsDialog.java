package br.com.sailboat.canoe.dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import br.com.sailboat.canoe.base.BaseDialogFragment;
import br.com.sailboat.canoe.helper.StringHelper;

public class TwoOptionsDialog extends BaseDialogFragment {

    private String positiveMsg;
    private String negativeMsg;
    private PositiveCallback positiveCallback;
    private PositiveNegativeCallback positiveAndNegativeCallback;

    @Override
    protected void bindButtons(AlertDialog.Builder alert) {
        checkAndBindPositiveCallbacks(alert);
        checkAndBindPositiveNegativeCallbacks(alert);
    }

    private void checkAndBindPositiveCallbacks(AlertDialog.Builder alert) {
        if (getPositiveCallback() != null) {

            alert.setPositiveButton(getPositiveMsg(), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    getPositiveCallback().onClickPositiveOption();
                }
            });

            alert.setNegativeButton(getNegativeMsg(), null);
        }
    }

    private void checkAndBindPositiveNegativeCallbacks(AlertDialog.Builder alert) {
        if (getPositiveAndNegativeCallback() != null) {

            alert.setPositiveButton(getPositiveMsg(), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    getPositiveAndNegativeCallback().onClickPositiveOption();
                }
            });

            alert.setNegativeButton(getNegativeMsg(), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    getPositiveAndNegativeCallback().onClickNegativeOption();
                }
            });
        }
    }

    public String getPositiveMsg() {
        if (StringHelper.isNullOrEmpty(positiveMsg)) {
            positiveMsg = getString(android.R.string.ok);
        }

        return positiveMsg;
    }

    public void setPositiveMsg(String positiveMsg) {
        this.positiveMsg = positiveMsg;
    }

    public String getNegativeMsg() {
        if (StringHelper.isNullOrEmpty(negativeMsg)) {
            negativeMsg = getString(android.R.string.cancel);
        }

        return negativeMsg;
    }

    public void setNegativeMsg(String negativeMsg) {
        this.negativeMsg = negativeMsg;
    }

    public PositiveCallback getPositiveCallback() {
        return positiveCallback;
    }

    public void setPositiveCallback(PositiveCallback positiveCallback) {
        this.positiveCallback = positiveCallback;
    }

    public PositiveNegativeCallback getPositiveAndNegativeCallback() {
        return positiveAndNegativeCallback;
    }

    public void setPositiveAndNegativeCallback(PositiveNegativeCallback positiveAndNegativeCallback) {
        this.positiveAndNegativeCallback = positiveAndNegativeCallback;
    }

    public interface PositiveCallback {
        void onClickPositiveOption();
    }


    public interface PositiveNegativeCallback {
        void onClickPositiveOption();
        void onClickNegativeOption();
    }


}
