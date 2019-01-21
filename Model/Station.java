package com.i550.traintickets.Model;

public class Station {
    private String countryTitle; //" : "Россия", //название страны (денормализация данных, дубль из города)
    private GpsPoint point; // координаты
    private String districtTitle; //" : "Чистопольский район", //название района
    private int cityId; //" : 4454, //идентификатор города
    private String cityTitle; //" : "город Чистополь", //название города
    private String regionTitle; //" : "Республика Татарстан", //название региона
    private int stationId; //" : 9362, //идентификатор станции
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

    public int getCityId() {
        return cityId;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public String getRegionTitle() {
        return regionTitle;
    }

    public int getStationId() {
        return stationId;
    }

    public String getStationTitle() {
        return stationTitle;
    }
}
