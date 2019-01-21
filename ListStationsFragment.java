package com.i550.traintickets;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import android.widget.TextView;

import com.i550.traintickets.Model.City;
import com.i550.traintickets.Model.DataModel;
import com.i550.traintickets.Model.Order;
import com.i550.traintickets.Model.Station;
import com.i550.traintickets.Model.StationsAdapter;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ListStationsFragment extends Fragment {
    private ArrayList<City> cities;
    private ArrayList<City> filterCities;
    private DataModel vm;
    private StationsAdapter adapter;
    public final static String TAG = "LIST";
    private String title;
    private int mode;

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
            mode = getArguments().getInt("mode");
            if (mode == 0) {
                cities = vm.getDb().getCitiesFrom();
                title = "Where from";
            } else {
                cities = vm.getDb().getCitiesTo();
                title = "Where to";
            }
        }
        filterCities = (ArrayList<City>) cities.clone();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_stations, container, false);
        setHasOptionsMenu(true);
        TextView header = v.findViewById(R.id.header_stations_list);
        header.setText(title);
        ExpandableListView listView = v.findViewById(R.id.stations_list_view);
        adapter = new StationsAdapter(getActivity(), filterCities, mode);
        listView.setAdapter(adapter);
        adapter.setButtonClickListener((Station station) -> {

            Order order = vm.getOrder();
            if (mode == 0) order.setStationFrom(station);
            else order.setStationTo(station);
            vm.setOrder(order);
            Intent intent = new Intent();
            intent.putExtra(MainActivity.STATION, station.getStationTitle());
            getTargetFragment().onActivityResult(mode, RESULT_OK, intent );
            getFragmentManager().popBackStack();
        });


        return v;


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.notifyDataSetChanged();
                searchView.setIconified(true);
                menuItem.collapseActionView();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                filterCities.clear();                           //==== новый список
                for (City c : cities) {                                                      //для каждого города
                    City newCity = new City();                                              //==== новый город
                    ArrayList<Station> allStations = c.getAllStations();
                    for (Station s : allStations) {                                           //для каждой станции
                        if (s.getStationTitle().toLowerCase().contains(query.toLowerCase())) //если в названии есть совпадение
                        {
                            newCity.setCityTitle(c.getCityTitle());
                            newCity.setCountryTitle(c.getCountryTitle());
                            newCity.addStation(s);                                           // в новый город добавляем новую станцию
                        }
                    }
                    if (newCity.getAmountOfStations() != 0)
                        filterCities.add(newCity);        //если в городе есть станции, добавляем город в список
                }
                //Log.e(TAG, "New list " + filterCities.get(0));
                adapter.notifyDataSetChanged();
                return true;

            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
 /*       switch (item.getItemId()) {
            case R.id.menu_search:
                // Handle fragment menu item
                return true;
            default:
                // Not one of ours. Perform default menu processing
                }*/
                return super.onOptionsItemSelected(item);

    }
}
