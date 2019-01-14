package com.i550.traintickets.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.i550.traintickets.R;

import java.util.ArrayList;

public class StationsAdapter extends BaseExpandableListAdapter {

    private ArrayList<City> mCities;
    private Context mContext;

    public StationsAdapter (Context context,ArrayList<City> cities){
        mContext = context;
        mCities = cities;
    }


    @Override
    public int getGroupCount() {
        return mCities.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mCities.get(i).getAmountOfStations();
    }

    @Override
    public Object getGroup(int i) {
        return mCities.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mCities.get(i).getStation(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(android.R.layout.simple_expandable_list_item_1 , null);
        }

        /*if (b){
            //Изменяем что-нибудь, если текущая Group раскрыта
        }
        else{
            //Изменяем что-нибудь, если текущая Group скрыта
        }*/

        TextView text = view.findViewById(android.R.id.text1);
        City c = (City)getGroup(i);
        text.setText(c.getCountryTitle() + ", " + c.getCityTitle());

        return view;

    }

    @Override
    public View getChildView(int c, int s, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_station, null);
        }
        final Station station = (Station) getChild(c,s);

        TextView countryTitle = view.findViewById(R.id.country_title);
        countryTitle.setText(station.getCountryTitle());
        TextView regionTitle =  view.findViewById(R.id.region_title);
        regionTitle.setText(station.getRegionTitle());
        TextView districtTitle =  view.findViewById(R.id.district_title);
        districtTitle.setText(station.getDistrictTitle());
        TextView longitude = view.findViewById(R.id.longitude_title);
        longitude.setText(station.getPoint().getLongitude());
        TextView latitude = view.findViewById(R.id.latitude_title);
        latitude.setText(station.getPoint().getLatitude());

        Button button = view.findViewById(R.id.choose_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,station.getCityId(),Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
