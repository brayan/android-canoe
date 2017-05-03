package br.com.sailboat.canoe.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import br.com.sailboat.canoe.helper.StringHelper;

public class BaseDialogFragment extends DialogFragment {

    private boolean firstSession = true;
    private String message;
    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isFirstSession()) {
            onResumeFirstSession();
            setFirstSession(false);
        } else {
            onResumeAfterRestart();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        bindMessage(alert);
        bindTitle(alert);
        bindButtons(alert);

        return alert.create();
    }

    protected void bindButtons(AlertDialog.Builder alert) {
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    protected void bindMessage(AlertDialog.Builder alert) {
        if (StringHelper.isNotEmpty(getMessage())) {
            alert.setMessage(getMessage());
        }
    }

    protected void bindTitle(AlertDialog.Builder alert) {
        if (StringHelper.isNotEmpty(getTitle())) {
            alert.setTitle(getTitle());
        }
    }

    protected void onResumeFirstSession() {
    }

    protected void onResumeAfterRestart() {
    }

    public boolean isFirstSession() {
        return firstSession;
    }

    public void setFirstSession(boolean firstSession) {
        this.firstSession = firstSession;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
