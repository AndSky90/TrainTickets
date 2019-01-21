package com.i550.traintickets;

import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.Toast;

import com.i550.traintickets.Model.DataModel;
import com.i550.traintickets.Model.Order;
import com.i550.traintickets.Model.Station;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainFragment extends Fragment {

    private static final String DATE_PICKER = "DatePicker";

    private static final int REQ_CODE_DATE = 2019;
    private static final int LIST_FROM = 0;
    private static final int LIST_TO = 1;

    private static DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
    Button datePickerButton, fromButton, toButton, orderButton;
    ImageButton swapButton;
    DataModel dm;
    Order order;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        final FragmentManager fm = getFragmentManager();
        dm = ViewModelProviders.of(getActivity()).get(DataModel.class);

        datePickerButton = v.findViewById(R.id.date_picker_button);
        fromButton = v.findViewById(R.id.from_button);
        toButton = v.findViewById(R.id.to_button);
        swapButton = v.findViewById(R.id.swap_button);
        orderButton = v.findViewById(R.id.order_button);
        orderButton.setEnabled(false);

        order = dm.getOrder();
        Station st = order.getStationFrom();
        if (st!=null) fromButton.setText(st.getStationTitle());
        st = order.getStationTo();
        if (st!=null) toButton.setText(st.getStationTitle());
        Date date = order.getDate();
        if (date!=null) datePickerButton.setText(df.format(date));
        isOrderEnable();
        super.onStart();

        datePickerButton.setOnClickListener((View view) -> {
            DialogFragment dateDialog = DatePickerFragment.newInstance(new Date());
            dateDialog.setTargetFragment(MainFragment.this, REQ_CODE_DATE);
            dateDialog.show(fm, DATE_PICKER);
        });

        fromButton.setOnClickListener((View view) -> {

                    ListStationsFragment fragmentReplace;
                    fragmentReplace = ListStationsFragment.newInstance(LIST_FROM);
                    fragmentReplace.setTargetFragment(MainFragment.this, LIST_FROM);
                    fm.beginTransaction()
                            .hide(MainFragment.this)
                            .add(R.id.fragment_container, fragmentReplace, fragmentReplace.TAG)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(fragmentReplace.TAG)
                            .commit();
                }
        );

        toButton.setOnClickListener((View view) -> {
                    ListStationsFragment fragmentReplace;
                    fragmentReplace = ListStationsFragment.newInstance(LIST_TO);
                    fragmentReplace.setTargetFragment(MainFragment.this, LIST_TO);
                    fm.beginTransaction()
                            .hide(MainFragment.this)
                            .add(R.id.fragment_container, fragmentReplace, fragmentReplace.TAG)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(fragmentReplace.TAG)
                            .commit();
                }
        );

        swapButton.setOnClickListener((View view) ->
                {
                    Order order = dm.getOrder();
                    Station from = order.getStationFrom();
                    Station to = order.getStationTo();
                    order.setStationFrom(to);
                    order.setStationTo(from);
                    CharSequence s = fromButton.getText();
                    fromButton.setText(toButton.getText());
                    toButton.setText(s);
                }
        );

        orderButton.setOnClickListener((View view) -> {
            Toast.makeText(getActivity(),dm.getOrder().toString(),Toast.LENGTH_LONG).show();
        });

        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQ_CODE_DATE: {
                Date date = (Date) intent.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
                Order order = dm.getOrder();
                order.setDate(date);
                dm.setOrder(order);
                datePickerButton.setText(df.format(order.getDate()));
                break;
            }
            case LIST_FROM: {

                fromButton.setText((String)intent.getSerializableExtra(MainActivity.STATION));
                break;
            }

            case LIST_TO: {
                toButton.setText((String)intent.getSerializableExtra(MainActivity.STATION));
                break;
            }
        }
        isOrderEnable();
    }

    private void isOrderEnable(){
        if (order.getStationTo()!=null && order.getStationFrom()!=null && order.getDate()!=null) orderButton.setEnabled(true);
    }
}

