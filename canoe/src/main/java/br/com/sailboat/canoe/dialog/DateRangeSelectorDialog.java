package br.com.sailboat.canoe.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.base.BaseDialogFragment;
import br.com.sailboat.canoe.helper.DateHelper;

public class DateRangeSelectorDialog extends BaseDialogFragment {

    private Calendar initialDate;
    private Calendar finalDate;

    private TextView tvInitialDate;
    private TextView tvFinalDate;

    private DateRangeSelectorDialog.Callback callback;

    public static void show(FragmentManager manager, DateRangeSelectorDialog.Callback callback) {
        show(manager, null, null, callback);
    }

    public static void show(FragmentManager manager, Calendar initialDate, Calendar finalDate, DateRangeSelectorDialog.Callback callback) {
        DateRangeSelectorDialog dialog = new DateRangeSelectorDialog();
        dialog.callback = callback;
        dialog.setInitialDate(initialDate);
        dialog.setFinalDate(finalDate);
        dialog.show(manager, DateRangeSelectorDialog.class.getName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDates();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.dlg_date_range_selector, null);
        initViews(view);
        updateViews();

        return construirDialog(view);
    }

    private void initViews(View view) {
        tvInitialDate = (TextView) view.findViewById(R.id.dlg_date_range_selector__tv__initial);
        tvFinalDate = (TextView) view.findViewById(R.id.dlg_date_range_selector__tv__final);

        view.findViewById(R.id.dlg_date_range_selector__ll__initial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateSelectorDialog.show(getFragmentManager(), getInitialDate(), new DateSelectorDialog.Callback() {
                    @Override
                    public void onDateSet(int year, int month, int day) {
                        Calendar newInitialDate = Calendar.getInstance();
                        newInitialDate.set(Calendar.YEAR, year);
                        newInitialDate.set(Calendar.MONTH, month);
                        newInitialDate.set(Calendar.DAY_OF_MONTH, day);
                        DateHelper.setInitialTimeToCalendar(newInitialDate);

                        if (DateHelper.isAfterToday(newInitialDate)) {
                            MessageDialog.showMessage(getFragmentManager(), "A data não pode ser maior que hoje", "Ops...");
                            return;
                        }

                        if (newInitialDate.after(finalDate)) {
                            MessageDialog.showMessage(getFragmentManager(), "A data inicial não pode ser maior que a data final", "Ops...");
                            return;
                        }

                        getInitialDate().set(Calendar.YEAR, year);
                        getInitialDate().set(Calendar.MONTH, month);
                        getInitialDate().set(Calendar.DAY_OF_MONTH, day);

                        updateViews();
                    }
                });
            }
        });

        view.findViewById(R.id.dlg_date_range_selector__ll__final).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateSelectorDialog.show(getFragmentManager(), getFinalDate(), new DateSelectorDialog.Callback() {
                    @Override
                    public void onDateSet(int year, int month, int day) {
                        Calendar newFinalDate = Calendar.getInstance();
                        newFinalDate.set(Calendar.YEAR, year);
                        newFinalDate.set(Calendar.MONTH, month);
                        newFinalDate.set(Calendar.DAY_OF_MONTH, day);
                        DateHelper.setFinalTimeToCalendar(newFinalDate);

                        if (DateHelper.isAfterToday(newFinalDate)) {
                            MessageDialog.showMessage(getFragmentManager(), "A data não pode ser maior que hoje", "Ops...");
                            return;
                        }

                        if (newFinalDate.before(initialDate)) {
                            MessageDialog.showMessage(getFragmentManager(), "A data final não pode ser menor que a data inicial", "Ops...");
                            return;
                        }

                        getFinalDate().set(Calendar.YEAR, year);
                        getFinalDate().set(Calendar.MONTH, month);
                        getFinalDate().set(Calendar.DAY_OF_MONTH, day);

                        updateViews();
                    }
                });
            }
        });
    }

    private void updateViews() {
        tvInitialDate.setText(DateHelper.getShortDate(getActivity(), getInitialDate()));
        tvFinalDate.setText(DateHelper.getShortDate(getActivity(), getFinalDate()));
    }

    private Dialog construirDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onClickOk(getInitialDate(), getFinalDate());
            }
        });

        builder.setNegativeButton(R.string.cancel, null);

        return builder.create();
    }

    private void initDates() {
        if (initialDate == null) {
            initialDate = Calendar.getInstance();
        }
        DateHelper.setInitialTimeToCalendar(initialDate);

        if (finalDate == null) {
            finalDate = Calendar.getInstance();
        }
        DateHelper.setFinalTimeToCalendar(finalDate);
    }

    public Calendar getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Calendar initialDate) {
        this.initialDate = initialDate;
    }

    public Calendar getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Calendar finalDate) {
        this.finalDate = finalDate;
    }


    public interface Callback {
        void onClickOk(Calendar initialDate, Calendar finalDate);
    }

}
