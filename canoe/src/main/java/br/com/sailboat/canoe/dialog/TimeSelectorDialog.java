package br.com.sailboat.canoe.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

import br.com.sailboat.canoe.base.BaseDialogFragment;

public class TimeSelectorDialog extends BaseDialogFragment implements TimePickerDialog.OnTimeSetListener {

    private Calendar calendar;
    private Callback callback;


    public static void show(FragmentManager fragmentManager, TimeSelectorDialog.Callback callback) {
        show(fragmentManager, null, callback);
    }

    public static void show(FragmentManager fragmentManager, Calendar currentTime, TimeSelectorDialog.Callback callback) {
        TimeSelectorDialog dialog = new TimeSelectorDialog();
        dialog.setCalendar(currentTime);
        dialog.setCallback(callback);

        dialog.show(fragmentManager, TimeSelectorDialog.class.getName());
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initTime();

        TimePickerDialog dialog = new TimePickerDialog(getActivity(), this, getHour(), getMinute(),
                DateFormat.is24HourFormat(getActivity()));

        return dialog;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (view.isShown()) {
            callback.onTimeSet(hourOfDay, minute);
        }
    }

    private void initTime() {
        if (calendar == null) {
            this.calendar = Calendar.getInstance();
        }
    }

    private int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }

    private int getHour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }


    public interface Callback {
        void onTimeSet(int hourOfDay, int minute);
    }


}
