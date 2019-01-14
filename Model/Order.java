package com.i550.traintickets.Model;

import java.util.Date;

public class Order {
    private Date date;
    private int station_id_from;
    private int station_id_to;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStation_id_from() {
        return station_id_from;
    }

    public void setStation_id_from(int station_id_from) {
        this.station_id_from = station_id_from;
    }

    public int getStation_id_to() {
        return station_id_to;
    }

    public void setStation_id_to(int station_id_to) {
        this.station_id_to = station_id_to;
    }
}
