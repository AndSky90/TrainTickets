package com.i550.traintickets.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;


@Entity
public class City {

    private String countryTitle;
    private GpsPoint point;
    private String districtTitle;           // "Чистопольский район", название района
    @PrimaryKey
    private int cityId;                   //" : 4454, //идентификатор города
    private String cityTitle;                //: "Чистополь", //название города
    private String regionTitle;              //: "Республика Татарстан", //название региона
    private ArrayList<Station> stations = new ArrayList<>();                 //: [...] //массив станций

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

    public void setCountryTitle(String countryTitle) {
        this.countryTitle = countryTitle;
    }

    public void setPoint(GpsPoint point) {
        this.point = point;
    }

    public void setDistrictTitle(String districtTitle) {
        this.districtTitle = districtTitle;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setRegionTitle(String regionTitle) {
        this.regionTitle = regionTitle;
    }

    public void addStation(Station s) {
        stations.add(s);
    }

    public String getCountryTitle() {
        return countryTitle;
    }

    public GpsPoint getPoint() {
        return point;
    }

    public String getDistrictTitle() {
        return districtTitle;
    }

    public int getCityId() {
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

    public ArrayList<Station> getAllStations() {
        return stations;
    }

    public int getAmountOfStations() {
        return stations.size();
    }
}
