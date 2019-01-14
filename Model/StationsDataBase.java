package com.i550.traintickets.Model;

import java.util.ArrayList;

public class StationsDataBase {
    private ArrayList<City> citiesFrom = new ArrayList<>();
    private ArrayList<City> citiesTo = new ArrayList<>();

    public ArrayList<City> getCitiesFrom() {
        return citiesFrom;
    }

    public ArrayList<City> getCitiesTo() {
        return citiesTo;
    }
}
