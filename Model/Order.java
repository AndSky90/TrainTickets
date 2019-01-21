package com.i550.traintickets.Model;

import android.support.annotation.NonNull;

import java.util.Date;

public class Order {
    private Date date;
    private Station stationFrom;
    private Station stationTo;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Station getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(Station stationFrom) {
        this.stationFrom = stationFrom;
    }

    public Station getStationTo() {
        return stationTo;
    }

    public void setStationTo(Station stationTo) {
        this.stationTo = stationTo;
    }

    @NonNull
    @Override
    public String toString() {
        return date.toString() + " from " + stationFrom.getStationTitle() + " to " + stationTo.getStationTitle() ;
    }
}
