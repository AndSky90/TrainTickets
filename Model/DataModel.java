package com.i550.traintickets.Model;

import android.arch.lifecycle.ViewModel;

public class DataModel extends ViewModel {
    private Order mOrder = new Order();
    private StationsDataBase db = new StationsDataBase();

    public Order getOrder() {
        return mOrder;
    }

    public void setOrder(Order order) {
        mOrder = order;
    }

    public void setDb(StationsDataBase db) {
        this.db = db;
    }

    public StationsDataBase getDb() {
        return db;

    }
}
