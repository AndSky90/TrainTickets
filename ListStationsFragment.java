package com.i550.traintickets;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.i550.traintickets.Model.City;
import com.i550.traintickets.Model.DataModel;
import com.i550.traintickets.Model.StationsAdapter;

import java.util.ArrayList;

public class ListStationsFragment extends Fragment {
    ArrayList<City> cities;
    DataModel vm;
    public final static String TAG = "LIST";

    public static ListStationsFragment newInstance(int mode) {
        Bundle b = new Bundle();
        b.putInt("mode", mode);
        ListStationsFragment fragment = new ListStationsFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm = ViewModelProviders.of(getActivity()).get(DataModel.class);

        if (getArguments() != null) {
            int mode = getArguments().getInt("mode");
            if (mode == 0)
                cities = vm.getDb().getCitiesFrom();
            else cities = vm.getDb().getCitiesTo();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_stations, container, false);

        ExpandableListView listView = v.findViewById(R.id.stations_list_view);
        StationsAdapter adapter = new StationsAdapter(getActivity(), cities);
        listView.setAdapter(adapter);

        return v;


    }


}
