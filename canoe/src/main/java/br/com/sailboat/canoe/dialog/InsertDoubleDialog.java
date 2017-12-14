package br.com.sailboat.canoe.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.helper.DecimalHelper;
import br.com.sailboat.canoe.helper.InputFilterDecimalDigits;
import br.com.sailboat.canoe.helper.StringHelper;
import br.com.sailboat.canoe.helper.UIHelper;

public class InsertDoubleDialog extends DialogFragment {

    public static final double DEFAULT_VALUE = 0.0;

    private double value;
    private String title;
    private String message;
    private EditText etDialogTextInput;
    private InputFilterDecimalDigits inputFilter;
    private InsertDoubleDialog.Callback callback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        bindTitle(dialog);
        bindMessage(dialog);
        bindListeners(dialog);
        bindCustomView(savedInstanceState, dialog);

        AlertDialog alert = dialog.create();

        alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        return alert;
    }

    private void bindMessage(AlertDialog.Builder dialog) {
        if (StringHelper.isNotEmpty(getMessage())) {
            dialog.setMessage(getMessage());
        }
    }

    public void build(FragmentManager fragmentManager, InsertDoubleDialog.Callback callback) {
        setCallback(callback);
        show(fragmentManager, InsertDoubleDialog.class.getName());
    }

    private void bindCustomView(Bundle savedInstanceState, AlertDialog.Builder dialog) {
        LayoutInflater i = getActivity().getLayoutInflater();

        View view = i.inflate(R.layout.dlg_input_number_decimal, null, false);
        etDialogTextInput = (EditText) view.findViewById(R.id.dlg_input_number_decimal__et__input);

        bindInputFilter();

        if (savedInstanceState == null) {
            etDialogTextInput.setText(DecimalHelper.formatValue(getValue(), 2));
            UIHelper.openKeyboard(getActivity(), etDialogTextInput);
        }

        dialog.setView(view);
    }

    private void bindListeners(final AlertDialog.Builder dialog) {
        dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                UIHelper.hideKeyboard(getActivity());
                String txt = etDialogTextInput.getText().toString();
                if (StringHelper.isNotEmpty(txt)) {
                    getCallback().onClickOk(Double.valueOf(txt.trim()));
                } else {
                    getCallback().onClickOk(DEFAULT_VALUE);
                }

            }
        });
        dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                UIHelper.hideKeyboard(getActivity());
            }
        });
    }

    private void bindTitle(AlertDialog.Builder dialog) {
        if (StringHelper.isNotEmpty(getTitle())) {
            dialog.setTitle(getTitle());
        }
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    private void bindInputFilter() {
        if (inputFilter != null) {
            etDialogTextInput.setFilters(new InputFilter[] {inputFilter});
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public InputFilterDecimalDigits getInputFilter() {
        return inputFilter;
    }

    public void setInputFilter(InputFilterDecimalDigits inputFilter) {
        this.inputFilter = inputFilter;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public interface Callback {
        void onClickOk(double input);
    }

}
