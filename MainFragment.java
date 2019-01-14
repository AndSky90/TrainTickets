package com.i550.traintickets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.i550.traintickets.Model.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainFragment extends Fragment {

    private Order order = new Order();
    private static final String DATE_PICKER = "DatePicker";

    private static final int REQ_CODE_DATE = 2019;
    private static DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
    Button datePickerButton, fromButton, toButton;
    ImageButton swapButton;
    private FragmentClickListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        final FragmentManager fm = getFragmentManager();

        datePickerButton = v.findViewById(R.id.date_picker_button);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dateDialog = DatePickerFragment.newInstance(new Date());
                dateDialog.setTargetFragment(MainFragment.this, REQ_CODE_DATE);
                dateDialog.show(fm, DATE_PICKER);
            }
        });


        fromButton = v.findViewById(R.id.from_button);
        fromButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentClick(R.id.from_button);
            }
        });

        toButton = v.findViewById(R.id.to_button);
        toButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentClick(R.id.to_button);
            }
        });

        swapButton = v.findViewById(R.id.swap_button);
        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentClick(R.id.swap_button);
            }
        });


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == REQ_CODE_DATE) {
            Date date = (Date) intent.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            order.setDate(date);
            datePickerButton.setText(df.format(order.getDate()));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentClickListener) {
            mListener = (FragmentClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "not a valid context");
        }
    }
}

