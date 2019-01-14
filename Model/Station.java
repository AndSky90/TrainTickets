package com.i550.traintickets.Model;

import java.util.Map;

public class Station {
    private String countryTitle; //" : "Россия", //название страны (денормализация данных, дубль из города)
    private GpsPoint point; // координаты
    private String districtTitle; //" : "Чистопольский район", //название района
    private Integer cityId; //" : 4454, //идентификатор города
    private String cityTitle; //" : "город Чистополь", //название города
    private String regionTitle; //" : "Республика Татарстан", //название региона
    private Integer stationId; //" : 9362, //идентификатор станции
    private String stationTitle; //" : "Чистополь" //полное название станции

    public String getCountryTitle() {
        return countryTitle;
    }

    public GpsPoint getPoint() {
        return point;
    }

    public String getDistrictTitle() {
        return districtTitle;
    }

    public Integer getCityId() {
        return cityId;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public String getRegionTitle() {
        return regionTitle;
    }

    public Integer getStationId() {
        return stationId;
    }

    public String getStationTitle() {
        return stationTitle;
    }
}
