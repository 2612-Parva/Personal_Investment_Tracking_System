package com.investrack.app.db;


import androidx.room.Embedded;
import androidx.room.TypeConverters;

import java.util.Date;

@TypeConverters(DateConverter.class)
public class WatchlistData {

    @Embedded
    private Scheme scheme;
    private Date date;

    public WatchlistData() {
    }

    public WatchlistData(Scheme scheme, Date date) {
        this.scheme = scheme;
        this.date = date;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
