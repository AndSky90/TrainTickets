package com.i550.traintickets.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class City {
    private String countryTitle;
    private Map<String, Long> point;
    private String districtTitle;           // "Чистопольский район", название района
    private String cityId;                   //" : 4454, //идентификатор города
    private String cityTitle;                //: "Чистополь", //название города
    private String regionTitle;              //: "Республика Татарстан", //название региона
    private ArrayList<Station> stations;                 //: [...] //массив станций

    public String getCountryTitle() {
        return countryTitle;
    }

    public Map<String, Long> getPoint() {
        return point;
    }

    public String getDistrictTitle() {
        return districtTitle;
    }

    public String getCityId() {
        return cityId;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public String getRegionTitle() {
        return regionTitle;
    }

    public Station getStation(int i) {
        return stations.get(i);
    }

    public int getAmountOfStations() {
        return stations.size();
    }
}
