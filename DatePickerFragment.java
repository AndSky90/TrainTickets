package com.i550.traintickets;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private static final int REQ_CODE_DATE = 2019;
    private static final String ARG_DATE = "date";
    public static final String EXTRA_DATE = "DatePickerFragment";

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        DatePickerFragment f = new DatePickerFragment();
        f.setArguments(args);
        return f;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {       // Use the current date as the default date in the picker
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);  // Create a new instance of DatePickerDialog and return it
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {           // Do something with the date chosen by the user
        if (getTargetFragment() == null) return;
        Intent intent = new Intent();
        Date date = new GregorianCalendar(year, month, day).getTime();
        intent.putExtra(EXTRA_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }
}