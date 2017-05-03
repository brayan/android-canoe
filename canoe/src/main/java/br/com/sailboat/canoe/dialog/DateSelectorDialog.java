package br.com.sailboat.canoe.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.DatePicker;

import java.util.Calendar;

import br.com.sailboat.canoe.base.BaseDialogFragment;

public class DateSelectorDialog extends BaseDialogFragment implements DatePickerDialog.OnDateSetListener {

    private Calendar calendar;
    private Callback callback;


    public static void show(FragmentManager manager, DateSelectorDialog.Callback callback) {
        show(manager, null, callback);
    }

    public static void show(FragmentManager manager, Calendar calendar, DateSelectorDialog.Callback callback) {
        DateSelectorDialog dialog = new DateSelectorDialog();
        dialog.setCallback(callback);
        dialog.setCalendar(calendar);
        dialog.show(manager, "DATE_SELECTOR");
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initCalendar();
        return new DatePickerDialog(getActivity(), this, getYear(), getMonth(), getDay());
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if (view.isShown()) {
            callback.onDateSet(year, monthOfYear, dayOfMonth);
        }
    }

    private void initCalendar() {
        if (calendar == null) {
            this.calendar = Calendar.getInstance();
        }
    }

    private int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    private int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    private int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    public interface Callback {
        void onDateSet(int year, int month, int day);
    }


}
