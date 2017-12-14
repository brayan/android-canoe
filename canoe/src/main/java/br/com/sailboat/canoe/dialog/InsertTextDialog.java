package br.com.sailboat.canoe.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.base.BaseDialogFragment;
import br.com.sailboat.canoe.helper.StringHelper;

public class InsertTextDialog extends BaseDialogFragment {

    private InsertTextDialog.Callback callback;

    private String text;

    private EditText etInput;


    public static void show(FragmentManager manager, String text, InsertTextDialog.Callback callback) {
        InsertTextDialog dialog = new InsertTextDialog();
        dialog.setText(text);
        dialog.setCallback(callback);

        dialog.show(manager, InsertTextDialog.class.getCanonicalName());
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.dlg_input_text, null);
        initViews(view);
        return buildDialog(view);
    }

    private void initViews(View view) {
        etInput = (EditText) view.findViewById(R.id.dlg_input_text__et__input);
        if (StringHelper.isNotEmpty(getText())) {
            etInput.setText(getText());
        }
    }

    private Dialog buildDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                callback.onClickOk(etInput.getText().toString());
            }
        });

        builder.setNegativeButton(android.R.string.cancel, null);

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public interface Callback {
        void onClickOk(String text);
    }

}
